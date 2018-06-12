package com.bullhorn.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullhorn.json.model.AssignmentRequest;
import com.bullhorn.json.model.SourceAssignments;
import com.bullhorn.json.model.TargetAssignments;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import com.bullhorn.orm.timecurrent.model.MapVO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

@Component
public class Mapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(Mapper.class);
	private static final String JAVASCRIPT_ENGINE_NAME = "nashorn";

	final MapDAO dao;

	@Autowired
	public Mapper(MapDAO dao){this.dao = dao;}

	// Entry Point
	public TargetAssignments ProcessMapping(SourceAssignments srcAsses) throws JsonSyntaxException {
		LOGGER.info("Processing DataMapping for {}", srcAsses.toString());
		LOGGER.info("Recieved : {}", srcAsses.getData().toString());
		Gson gson = new Gson();
		List<AssignmentRequest> processedAsses = new ArrayList<>();

		// Get mapping from database
		LOGGER.info("Map Name : {}", srcAsses.getMapName());
		List<MapVO> mapDefs = dao.getMapDetail(srcAsses.getMapName());
		LOGGER.info("Map : {}", mapDefs.size());
		mapDefs.forEach((x) -> {
			LOGGER.debug("Fetched from DB - {}", x.getAttribute() + " SJ " + x.getExpression());
		});

		// Get list of assignments and the corresponding JSON map from the recieved payload
		List<Map<String, Object>> assignmentMaps = Mapper.ListAssignmentsJsonMap(srcAsses.getData());

		// Process the mapping (Source to Target)
		assignmentMaps.forEach((assignmentMap) -> {
			AssignmentRequest req = null;
			req = gson.fromJson(Mapper.ProcessMapDefs(mapDefs, assignmentMap), AssignmentRequest.class);
			processedAsses.add(req);
		});

		return new TargetAssignments(processedAsses);
	}

	private static List<Map<String, Object>> ListAssignmentsJsonMap(JsonObject[] jsonObj) {
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

	private static String ProcessMapDefs(List<MapVO> mapDefs, Map<String, Object> assignmentMap){
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
