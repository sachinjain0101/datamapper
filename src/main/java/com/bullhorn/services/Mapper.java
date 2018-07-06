package com.bullhorn.services;

import com.bullhorn.app.Constants;
import com.bullhorn.app.OperaStatus;
import com.bullhorn.json.model.SourceAssignments;
import com.bullhorn.json.model.TargetAssignments;
import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationValidatedMessages;
import com.bullhorn.orm.timecurrent.dao.AssignmentProcessorDAO;
import com.bullhorn.orm.timecurrent.dao.ClientDAO;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import com.bullhorn.orm.timecurrent.model.Client;
import com.bullhorn.orm.timecurrent.model.MapVO;
import com.bullhorn.orm.timecurrent.model.TblIntegrationAssignmentProcessor;
import com.bullhorn.orm.timecurrent.model.TblIntegrationFrontOfficeSystem;
import com.bullhorn.utils.FuncEnum;
import com.bullhorn.utils.Functions;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

public class Mapper implements CancellableRunnable{

    private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);
    private static final String JAVASCRIPT_ENGINE_NAME = "nashorn";
    private static final String HASH = "#";

    private final MapDAO mapDAO;
    private final ClientDAO clientDAO;
    private final ValidatedMessagesDAO validatedMessagesDAO;
    private final MappedMessagesDAO mappedMessagesDAO;
    private final AssignmentProcessorDAO assignmentProcessorDAO;

    private HashMap<String, Client> clients;
    private long interval;
    public void setInterval(long interval) {
        this.interval = interval;
    }

    private AtomicBoolean processing = new AtomicBoolean();

    private TblIntegrationFrontOfficeSystem FOS;
    public void setFOS(TblIntegrationFrontOfficeSystem FOS) {
        this.FOS = FOS;
    }

    public Mapper(MapDAO mapDAO, ValidatedMessagesDAO validatedMessagesDAO
            , MappedMessagesDAO mappedMessagesDAO, AssignmentProcessorDAO assignmentProcessorDAO
            , ClientDAO clientDAO) {
        this.validatedMessagesDAO = validatedMessagesDAO;
        this.mappedMessagesDAO = mappedMessagesDAO;
        this.mapDAO = mapDAO;
        this.assignmentProcessorDAO = assignmentProcessorDAO;
        this.clientDAO = clientDAO;
    }

    private List<TblIntegrationValidatedMessages> validatedMessages;

    @Override
    public void run() {
        processing.set(true);
        LOGGER.debug("Running the Data Mapper");
        while (!Thread.interrupted() && processing.get()) {
            clients = clientDAO.getAllActiveClients(FOS.getRecordId());
            validatedMessages = validatedMessagesDAO.findAllValidated(clients);
            // Sorting the result to process in the right sequence
            validatedMessages.sort(Comparator.comparing(TblIntegrationValidatedMessages::getClient).thenComparing(TblIntegrationValidatedMessages::getSequenceNumber));

            for (TblIntegrationValidatedMessages msg : validatedMessages) {
                //TargetAssignments targetAssignments = null;
                List<TargetAssignments> targetAssignments = new ArrayList<>();
                TblIntegrationMappedMessages tblIntegrationMappedMessages = null;
                try {
                    LOGGER.debug("--- --- {} - {} - {}", msg.getClient(), msg.getSequenceNumber(), msg.getStatus());
                    targetAssignments = processMapping(createSourceMessage(msg));
                    if (createAssignmentProcessorRecord(msg.getClient(), msg.getIntegrationKey(), msg.getMessageId(), msg.getMapName(), targetAssignments.size()))
                        postData(targetAssignments);
                    tblIntegrationMappedMessages = createMappedMessagesRecord(msg, targetAssignments, null);
                    msg.setStatus(OperaStatus.VALIDATED.toString());
                } catch (Exception e) {
                    mappedMessagesDAO.save(createMappedMessagesRecord(msg, targetAssignments, ExceptionUtils.getStackTrace(e)));
                    msg.setStatus(OperaStatus.VALIDATED_WITH_MAPPING_ERROR.toString());
                    msg.setErrorDescription("Refer to MappedMessages table for description");
                }
                if (tblIntegrationMappedMessages != null)
                    mappedMessagesDAO.save(tblIntegrationMappedMessages);
                validatedMessagesDAO.save(msg);
            }
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                LOGGER.debug("Data Mapper interrupted : {}", e.getMessage());
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void cancel() {
        processing.set(false);
        LOGGER.debug("Stopping the Data Mapper : {}", processing.get());
    }

    private Boolean createAssignmentProcessorRecord(String client, String integrationKey, String messageId, String mapName, int noOfAssignments) throws Exception{
        TblIntegrationAssignmentProcessor ap = new TblIntegrationAssignmentProcessor();
        ap.setClient(client);
        ap.setIntegrationKey(integrationKey);
        ap.setMapName(mapName);
        ap.setMessageId(messageId);
        ap.setNoOfAssignments(noOfAssignments);
        ap.setFileName("Coming in from Opera");
        ap.setStatus("Ready");
        assignmentProcessorDAO.save(ap);
        return true;
    }

    private ResponseEntity<List<TargetAssignments>> postData(List<TargetAssignments> targetAssignments) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        URI uri = new URI(mapDAO.getIntegrationConfig().get(Constants.PNET_ASSIGNMENT_PROCESSOR_REST_URL));
        RestTemplate restTemplate = new RestTemplate();

        LOGGER.debug("Found {}", uri.toString());
        ObjectMapper mapper = new ObjectMapper();

        HttpEntity<Object> requestEntity = new HttpEntity<>(mapper.writeValueAsString(targetAssignments), headers);
        restTemplate.exchange(uri, HttpMethod.POST, requestEntity,String.class);

        return new ResponseEntity<>(targetAssignments, HttpStatus.OK);
    }

    private TblIntegrationMappedMessages createMappedMessagesRecord(TblIntegrationValidatedMessages m, List<TargetAssignments> targetAssignments, String errorDescription) {
        TblIntegrationMappedMessages tblIntegrationMappedMessages = new TblIntegrationMappedMessages();
        Gson gson = new Gson();
        tblIntegrationMappedMessages.setClient(m.getClient());
        tblIntegrationMappedMessages.setIntegrationKey(m.getIntegrationKey());
        tblIntegrationMappedMessages.setMessageId(m.getMessageId());
        tblIntegrationMappedMessages.setSequenceNumber(m.getSequenceNumber());
        if (errorDescription != null) {
            tblIntegrationMappedMessages.setStatus(OperaStatus.MAPPING_ERROR.toString());
            tblIntegrationMappedMessages.setErrorDescription(errorDescription);
            tblIntegrationMappedMessages.setNoOfAssignments(null);
        } else {
            tblIntegrationMappedMessages.setStatus(OperaStatus.MAPPED.toString());
            tblIntegrationMappedMessages.setErrorDescription(null);
            tblIntegrationMappedMessages.setNoOfAssignments(targetAssignments.size());
        }
        tblIntegrationMappedMessages.setMapName(m.getMapName());
        tblIntegrationMappedMessages.setMessage(m.getMessage());
        tblIntegrationMappedMessages.setMappedMessage(gson.toJson(targetAssignments));
        tblIntegrationMappedMessages.setFrontOfficeSystemRecordID(m.getFrontOfficeSystemRecordID());
        tblIntegrationMappedMessages.setClientRecordID(m.getClientRecordID());
        tblIntegrationMappedMessages.setServiceBusMessagesRecordID(m.getServiceBusMessagesRecordID());
        tblIntegrationMappedMessages.setValidatedMessagesRecordID(m.getRecordId());
        return tblIntegrationMappedMessages;
    }

    private SourceAssignments createSourceMessage(TblIntegrationValidatedMessages msg) {
        SourceAssignments sourceAssignments = new SourceAssignments();
        sourceAssignments.setClient(msg.getClient());
        sourceAssignments.setIntegrationKey(msg.getIntegrationKey());
        sourceAssignments.setMapName(msg.getMapName());
        sourceAssignments.setMessageId(msg.getMessageId());

        JsonParser parser = new JsonParser();
        JsonArray array = parser.parse(msg.getMessage()).getAsJsonArray();
        JsonObject[] data = new JsonObject[array.size()];
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
            Arrays.fill(data, array.get(i));
        }

        sourceAssignments.setData(data);

        return sourceAssignments;
    }

    // Entry Point from RestURl
    public List<TargetAssignments> processMapping(SourceAssignments srcAsses) throws Exception {
        LOGGER.debug("Processing DataMapping for {}", srcAsses.toString());
        LOGGER.debug("Received : {}", Arrays.toString(srcAsses.getData()));
        Gson gson = new Gson();
        List<TargetAssignments> targetAssignments = new ArrayList<>();

        try {
            // Get mapping from database
            LOGGER.debug("Map Name : {}", srcAsses.getMapName());
            List<MapVO> mapDefs = mapDAO.getMapDetail(srcAsses.getMapName());
            LOGGER.debug("Map : {}", mapDefs.size());

            // Get list of assignments and the corresponding JSON map from the recieved payload
            List<Map<String, Object>> assignmentMaps = listAssignmentsJsonMap(srcAsses.getData());

            // Process the mapping (Source to Target)
            Integer recID=1;
            for (Map<String, Object> assignmentMap : assignmentMaps) {
                TargetAssignments req = gson.fromJson(processMapDefs(mapDefs, assignmentMap, srcAsses, recID), TargetAssignments.class);
                targetAssignments.add(req);
                recID++;
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            throw e;
        }

        return targetAssignments;
    }

    private String processMapDefs(List<MapVO> mapDefs, Map<String, Object> assignmentMap, SourceAssignments sourceAssignments, Integer recID) throws Exception {
        // Initiate the JavaScript engine
        Map<String, String> outMap = new LinkedHashMap<>();
        try {
            ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName(JAVASCRIPT_ENGINE_NAME);

            for (Entry<String, Object> entry : assignmentMap.entrySet()) {
                jsEngine.put(entry.getKey(), entry.getValue().toString().replace("\"", ""));
            }

            for (MapVO m : mapDefs) {
                String val = "";
                // Execution of JS expression
                // LOGGER.debug("******* {} {}", m.getExpression());

                // Handler for the remaining attributes
                switch (m.getAttribute()) {
                    case "IntegrationKey":
                        val = sourceAssignments.getIntegrationKey();
                        break;
                    case "Client":
                        val = sourceAssignments.getClient();
                        break;
                    case "MessageID":
                        val = sourceAssignments.getMessageId();
                        break;
                    case "TransDateTime":
                        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        val = simpleDateFormat.format(new Date());
                        break;
                    case "JobID":
                        val = "";
                        break;
                    case "RecID":
                        val = String.valueOf(recID);
                        break;
                    default:
                        if (m.getExpression().contains(HASH)) {
                            String[] pieces = m.getExpression().split(HASH);
                            val = jsEngine.eval(pieces[1]).toString();

                            LOGGER.debug("{} - {} - {}",m.getExpression(),pieces,val);

                            switch (FuncEnum.valueOf(pieces[0])) {
                                case STATE_CODE_CONVERSION:
                                    val = Functions.getStateCode(val);
                                    break;
                                case UNIX_MILLISEC_CONVERSION:
                                    if(val!=null && val.matches("\\d+")) {
                                        DateTime dateTime = new DateTime(Long.valueOf(val));
                                        val = dateTime.toDateTime(DateTimeZone.UTC).toString();
                                    }else
                                        val="";
                                    break;
                            }
                        }
                        else if (m.getExpression() != null && !m.getExpression().isEmpty())
                            val = jsEngine.eval(m.getExpression()).toString();
                }

                LOGGER.debug("{} - {}", m.getAttribute(), val);
                outMap.put(m.getAttribute(), val);
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            throw e;
        }

        return new Gson().toJson(outMap);
    }

    private List<Map<String, Object>> listAssignmentsJsonMap(JsonObject[] jsonObj) {
        List<Map<String, Object>> assignmentList = new ArrayList<>();
        LOGGER.debug("No. of Assignments: " + jsonObj.length);

        for (JsonObject obj : jsonObj) {
            Set<String> keys = obj.keySet();
            Map<String, Object> kvMap = new HashMap<>();
            keys.forEach((k) -> {
                //LOGGER.debug("[K]: " + k + " || [V]: " + obj.get(k).toString());
                kvMap.put(k, obj.get(k));
            });
            assignmentList.add(kvMap);
        }

        return assignmentList;
    }

    private Integer genRandomInt() {
        int min = 1;
        int max = 2000000000;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
