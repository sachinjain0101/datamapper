package com.bullhorn.services;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.bullhorn.app.OperaStatus;
import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.RefreshWorkDAOExt;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.refreshWork.model.TblIntegrationMappedMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationServiceBusMessages;
import com.bullhorn.orm.refreshWork.model.TblIntegrationValidatedMessages;
import com.google.gson.*;
import net.sourceforge.jtds.jdbc.DateTime;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bullhorn.json.model.AssignmentRequest;
import com.bullhorn.json.model.SourceAssignments;
import com.bullhorn.json.model.TargetAssignments;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import com.bullhorn.orm.timecurrent.model.MapVO;

@Component
public class Mapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);
    private static final String JAVASCRIPT_ENGINE_NAME = "nashorn";

    final MapDAO mapDAO;
    final ValidatedMessagesDAO validatedMessagesDAO;
    final MappedMessagesDAO mappedMessagesDAO;

    @Autowired
    public Mapper(MapDAO mapDAO, ValidatedMessagesDAO validatedMessagesDAO, MappedMessagesDAO mappedMessagesDAO) {
        this.validatedMessagesDAO = validatedMessagesDAO;
        this.mappedMessagesDAO = mappedMessagesDAO;
        this.mapDAO = mapDAO;
    }

    private List<TblIntegrationValidatedMessages> validatedMessages;

    @Scheduled(fixedDelay = 5000, initialDelay = 1000)
    public void run() {
        LOGGER.info("Running the Data Mapper");
        validatedMessages = validatedMessagesDAO.findAllValidated();
        // Sorting the result to process in the right sequence
        validatedMessages.sort(Comparator.comparing(TblIntegrationValidatedMessages::getClient).thenComparing(TblIntegrationValidatedMessages::getSequenceNumber));

        for (TblIntegrationValidatedMessages msg : validatedMessages) {
            TargetAssignments targetAssignments = null;
            TblIntegrationMappedMessages tblIntegrationMappedMessages = null;
            try {
                LOGGER.info("--- --- {} - {} - {}", msg.getClient(), msg.getSequenceNumber(), msg.getProcessed());
                targetAssignments = processMapping(createSourceMessage(msg));
                //LOGGER.info("*******************************");
                //LOGGER.info("********************* {}",targetAssignments.toString());
                tblIntegrationMappedMessages = createMappedMessagesRecord(msg, targetAssignments, null);
                msg.setProcessed(OperaStatus.VALIDATED.toString());
            } catch (Exception e) {
                mappedMessagesDAO.save(createMappedMessagesRecord(msg, targetAssignments, ExceptionUtils.getStackTrace(e)));
                msg.setProcessed(OperaStatus.VALIDATED_WITH_MAPPING_ERROR.toString());
                msg.setErrorDescription("Refer to MappedMessages table for description");
            }
            if (tblIntegrationMappedMessages != null)
                mappedMessagesDAO.save(tblIntegrationMappedMessages);
            validatedMessagesDAO.save(msg);
        }

    }

    public TblIntegrationMappedMessages createMappedMessagesRecord(TblIntegrationValidatedMessages m, TargetAssignments targetAssignments, String errorDescription) {
        TblIntegrationMappedMessages tblIntegrationMappedMessages = new TblIntegrationMappedMessages();
        Gson gson = new Gson();
        tblIntegrationMappedMessages.setClient(m.getClient());
        tblIntegrationMappedMessages.setIntegrationKey(m.getIntegrationKey());
        tblIntegrationMappedMessages.setMessageId(m.getMessageId());
        tblIntegrationMappedMessages.setSequenceNumber(m.getSequenceNumber());
        if (errorDescription != null) {
            tblIntegrationMappedMessages.setProcessed(OperaStatus.MAPPING_ERROR.toString());
            tblIntegrationMappedMessages.setErrorDescription(errorDescription);
            tblIntegrationMappedMessages.setNoOfAssignments(null);
        } else {
            tblIntegrationMappedMessages.setProcessed(OperaStatus.MAPPED.toString());
            tblIntegrationMappedMessages.setErrorDescription(null);
            tblIntegrationMappedMessages.setNoOfAssignments(targetAssignments.Assignments.size());
        }
        tblIntegrationMappedMessages.setMapName(m.getMapName());
        tblIntegrationMappedMessages.setMessage(m.getMessage());
        tblIntegrationMappedMessages.setMappedMessage(gson.toJson(targetAssignments, TargetAssignments.class));
        tblIntegrationMappedMessages.setFrontOfficeSystemRecordID(m.getFrontOfficeSystemRecordID());
        tblIntegrationMappedMessages.setClientRecordID(m.getClientRecordID());
        tblIntegrationMappedMessages.setServiceBusMessagesRecordID(m.getServiceBusMessagesRecordID());
        tblIntegrationMappedMessages.setValidatedMessagesRecordID(m.getRecordId());
        return tblIntegrationMappedMessages;
    }


    public SourceAssignments createSourceMessage(TblIntegrationValidatedMessages msg) {
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
    public TargetAssignments processMapping(SourceAssignments srcAsses) throws Exception {
        LOGGER.info("Processing DataMapping for {}", srcAsses.toString());
        LOGGER.info("Recieved : {}", srcAsses.getData().toString());
        Gson gson = new Gson();
        List<AssignmentRequest> processedAsses = new ArrayList<>();

        try {
            // Get mapping from database
            LOGGER.info("Map Name : {}", srcAsses.getMapName());
            List<MapVO> mapDefs = mapDAO.getMapDetail(srcAsses.getMapName());
            LOGGER.info("Map : {}", mapDefs.size());
            // mapDefs.forEach((x) -> {
            // 	LOGGER.debug("Fetched from DB - {}", x.getAttribute() + " SJ " + x.getExpression());
            // });

            // Get list of assignments and the corresponding JSON map from the recieved payload
            List<Map<String, Object>> assignmentMaps = listAssignmentsJsonMap(srcAsses.getData());

            // Process the mapping (Source to Target)
            for (Map<String, Object> assignmentMap : assignmentMaps) {
                AssignmentRequest req = null;
                req = gson.fromJson(processMapDefs(mapDefs, assignmentMap, srcAsses), AssignmentRequest.class);
                processedAsses.add(req);
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            throw e;
        }

        return new TargetAssignments(processedAsses);
    }

    private List<Map<String, Object>> listAssignmentsJsonMap(JsonObject[] jsonObj) {
        List<Map<String, Object>> assignmentList = new ArrayList<>();
        LOGGER.info("No. of Assignments: " + jsonObj.length);

        for (JsonObject obj : jsonObj) {
            Set<String> keys = obj.keySet();
            Map<String, Object> kvMap = new HashMap<>();
            keys.forEach((k) -> {
                //LOGGER.info("[K]: " + k + " || [V]: " + obj.get(k).toString());
                kvMap.put(k, obj.get(k));
            });
            assignmentList.add(kvMap);
        }

        return assignmentList;
    }

    private String processMapDefs(List<MapVO> mapDefs, Map<String, Object> assignmentMap, SourceAssignments srcAsses) throws Exception {
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
                // LOGGER.info("******* {} {}", m.getExpression());
                if (m.getExpression() != null && !m.getExpression().isEmpty())
                    val = jsEngine.eval(m.getExpression()).toString();

                String genID = generateLong().toString();

                if (m.getAttribute().equals("IntegrationKey"))
                    val = srcAsses.getIntegrationKey();
                else if (m.getAttribute().equals("Client"))
                    val = srcAsses.getClient();
                else if (m.getAttribute().equals("MessageID"))
                    val = srcAsses.getMessageId();
                else if (m.getAttribute().equals("Client"))
                    val = srcAsses.getClient();
                else if (m.getAttribute().equals("TransDateTime")) {
                    String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    val = simpleDateFormat.format(new Date());
                } else if (m.getAttribute().equals("JobID"))
                    val = genID;
                else if (m.getAttribute().equals("RecID"))
                    val = genID;

                //LOGGER.info("{} - {}", m.getAttribute(), val);
                outMap.put(m.getAttribute(), val);
            }
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            throw e;
        }

        return new Gson().toJson(outMap);
    }

    private Long generateLong() {
        long leftLimit = 1L;
        long rightLimit = 100000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }

}
