package com.bullhorn.services;

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
	public Mapper(MapDAO mapDAO, ValidatedMessagesDAO validatedMessagesDAO, MappedMessagesDAO mappedMessagesDAO){
		this.validatedMessagesDAO = validatedMessagesDAO;
		this.mappedMessagesDAO = mappedMessagesDAO;
		this.mapDAO = mapDAO;
	}

	private void logMessages(List<TblIntegrationValidatedMessages> msgs){
		LOGGER.info("*****************");
		msgs.forEach((m) -> {
					LOGGER.info("--- --- {} - {} - {}", m.getClient(), m.getSequenceNumber(), m.getProcessed());
				}
		);
	}

	private List<TblIntegrationValidatedMessages> validatedMessages;

	@Scheduled(fixedDelay = 5000, initialDelay = 3000)
	public void run() {
		LOGGER.info("Running the Data Mapper");
		validatedMessages = validatedMessagesDAO.findAllValidated();
		logMessages(validatedMessages);
		// Sorting the result to process in the right sequence
		validatedMessages.sort(Comparator.comparing(TblIntegrationValidatedMessages::getClient).thenComparing(TblIntegrationValidatedMessages::getSequenceNumber));
		//logMessages(validatedMessages);

		for(TblIntegrationValidatedMessages m: validatedMessages){
			LOGGER.info("--- --- {} - {} - {}", m.getClient(), m.getSequenceNumber(), m.getProcessed());
			SourceAssignments assignments = createSourceMessage(m);
			TargetAssignments targetAssignments = processMapping(assignments);
			LOGGER.info("*******************************");
			LOGGER.info("********************* {}",targetAssignments.toString());

			Gson gson = new Gson();

			TblIntegrationMappedMessages tblIntegrationMappedMessages = new TblIntegrationMappedMessages();

			tblIntegrationMappedMessages.setClient(m.getClient());
			tblIntegrationMappedMessages.setIntegrationKey(m.getIntegrationKey());
			tblIntegrationMappedMessages.setMessageId(m.getMessageId());
			tblIntegrationMappedMessages.setSequenceNumber(m.getSequenceNumber());
			tblIntegrationMappedMessages.setProcessed(null);
			tblIntegrationMappedMessages.setErrorDescription(null);
			tblIntegrationMappedMessages.setMapName(m.getMapName());
			tblIntegrationMappedMessages.setMessage(m.getMessage());
			tblIntegrationMappedMessages.setMappedMessage(gson.toJson(targetAssignments,TargetAssignments.class));
			tblIntegrationMappedMessages.setNoOfAssignments(targetAssignments.Assignments.size());
			tblIntegrationMappedMessages.setFrontOfficeSystemRecordID(m.getFrontOfficeSystemRecordID());
			tblIntegrationMappedMessages.setClientRecordID(m.getClientRecordID());
			tblIntegrationMappedMessages.setServiceBusMessagesRecordID(m.getServiceBusMessagesRecordID());
			tblIntegrationMappedMessages.setValidatedMessagesRecordID(m.getRecordId());

			mappedMessagesDAO.save(tblIntegrationMappedMessages);

			m.setProcessed(OperaStatus.VALIDATED.getValue());

			validatedMessagesDAO.save(m);

		}



		//List<TblIntegrationValidatedMessages> validMessages = getValidMessages();

		//validatedMessagesDAO.batchInsertValidatedMessages(validMessages);
		//serviceBusMessagesDAO.updateAllDownloaded(downloadedMessages);

		//LOGGER.info("********* DONE",validMessages.size());
	}


	public SourceAssignments createSourceMessage(TblIntegrationValidatedMessages msg){
		SourceAssignments sourceAssignments = new SourceAssignments();
		sourceAssignments.setClient(msg.getClient());
		sourceAssignments.setIntegrationKey(msg.getIntegrationKey());
		sourceAssignments.setMapName(msg.getMapName());
		sourceAssignments.setMessageId(msg.getMessageId());

		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(msg.getMessage()).getAsJsonArray();
		JsonObject[] data = new JsonObject[array.size()];
		for(int i=0; i<array.size();i++){
			System.out.println(array.get(i));
			Arrays.fill(data,array.get(i));
		}

		sourceAssignments.setData(data);

		return sourceAssignments;
	}
	
	// Entry Point from RestURl
	public TargetAssignments processMapping(SourceAssignments srcAsses) throws JsonSyntaxException {
		LOGGER.info("Processing DataMapping for {}", srcAsses.toString());
		LOGGER.info("Recieved : {}", srcAsses.getData().toString());
		Gson gson = new Gson();
		List<AssignmentRequest> processedAsses = new ArrayList<>();

		// Get mapping from database
		LOGGER.info("Map Name : {}", srcAsses.getMapName());
		List<MapVO> mapDefs = mapDAO.getMapDetail(srcAsses.getMapName());
		LOGGER.info("Map : {}", mapDefs.size());
		mapDefs.forEach((x) -> {
			LOGGER.debug("Fetched from DB - {}", x.getAttribute() + " SJ " + x.getExpression());
		});

		// Get list of assignments and the corresponding JSON map from the recieved payload
		List<Map<String, Object>> assignmentMaps = listAssignmentsJsonMap(srcAsses.getData());

		// Process the mapping (Source to Target)
		assignmentMaps.forEach((assignmentMap) -> {
			AssignmentRequest req = null;
			req = gson.fromJson(processMapDefs(mapDefs, assignmentMap), AssignmentRequest.class);
			processedAsses.add(req);
		});

		return new TargetAssignments(processedAsses);
	}

	private List<Map<String, Object>> listAssignmentsJsonMap(JsonObject[] jsonObj) {
		List<Map<String, Object>> assignmentList = new ArrayList<>();
		LOGGER.info("No. of Assignments: " + jsonObj.length);

		for (JsonObject obj : jsonObj) {
			Set<String> keys = obj.keySet();
			Map<String, Object> kvMap = new HashMap<>();
			keys.forEach((k) -> {
				LOGGER.info("[K]: " + k + " || [V]: " + obj.get(k).toString());
				kvMap.put(k, obj.get(k));
			});
			assignmentList.add(kvMap);
		}

		return assignmentList;
	}

	private String processMapDefs(List<MapVO> mapDefs, Map<String, Object> assignmentMap){
		// Initiate the JavaScript engine
		ScriptEngine jsEngine = new ScriptEngineManager().getEngineByName(JAVASCRIPT_ENGINE_NAME);

		for (Entry<String, Object> entry : assignmentMap.entrySet()) {
			jsEngine.put(entry.getKey(), entry.getValue().toString().replace("\"", ""));
		}

		Map<String, String> outMap = new LinkedHashMap<>();

		mapDefs.forEach((m) -> {
			Object obj = null;
			// Execution of JS expression
			try {
				obj = jsEngine.eval(m.getExpression());
			} catch (ScriptException e) {
				try {
					throw e;
				} catch (ScriptException e1) {
					e1.printStackTrace();
				}
			}
			String val = obj.toString();
			LOGGER.info("{} - {}", m.getAttribute(), val);
			outMap.put(m.getAttribute(), val);
		});

		return new Gson().toJson(outMap);
	}

}
