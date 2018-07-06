package com.bullhorn.rest;

import com.bullhorn.app.Constants;
import com.bullhorn.json.model.TargetAssignments;
import com.bullhorn.json.model.SourceAssignments;
import com.bullhorn.json.model.TargetMappings;
import com.bullhorn.orm.refreshWork.dao.MappedMessagesDAO;
import com.bullhorn.orm.refreshWork.dao.ValidatedMessagesDAO;
import com.bullhorn.orm.timecurrent.dao.AssignmentProcessorDAO;
import com.bullhorn.orm.timecurrent.dao.ClientDAO;
import com.bullhorn.orm.timecurrent.dao.MapDAO;
import com.bullhorn.services.Mapper;
import com.bullhorn.utils.FuncEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@Api(value = "Base resource for Opera-DataMapper")
@RequestMapping("/maps")
public class DataMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataMapper.class);

	private final ValidatedMessagesDAO validatedMessagesDAO;
	private final MapDAO mapDAO;
	private final MappedMessagesDAO mappedMessagesDAO;
	private final Mapper mapper;
	private final AssignmentProcessorDAO assignmentProcessorDAO;
	private final ClientDAO clientDAO;

	@Autowired
	public DataMapper(@Qualifier("mapDAO") MapDAO mapDAO
			, @Qualifier("validatedMessagesDAO") ValidatedMessagesDAO validatedMessagesDAO
			, @Qualifier("mappedMessagesDAO") MappedMessagesDAO mappedMessagesDAO
			, @Qualifier("assignmentProcessorDAO") AssignmentProcessorDAO assignmentProcessorDAO
			, @Qualifier("clientDAO") ClientDAO clientDAO) {
		this.mapDAO = mapDAO;
		this.validatedMessagesDAO = validatedMessagesDAO;
		this.mappedMessagesDAO = mappedMessagesDAO;
		this.assignmentProcessorDAO = assignmentProcessorDAO;
		this.clientDAO = clientDAO;
		this.mapper = new Mapper(this.mapDAO, this.validatedMessagesDAO, this.mappedMessagesDAO, this.assignmentProcessorDAO, this.clientDAO);
	}

	@ApiOperation(value = "Test to see Data Mapper is working or not.")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "Opera-DataMapper is running...";
	}

	@ApiOperation(value = "Gets the map information.")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public TargetMappings Get(@RequestParam(value = "mapName") String mapName) {
		return new TargetMappings(mapDAO.getMapDetail(mapName));
	}

	@ApiOperation(value = "Processes the source JSON and gives out the destination JSON.")
	@RequestMapping(value = "/process", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<?>> Process(@RequestBody SourceAssignments srcAsses) {
		LOGGER.debug("{}", srcAsses.toString());
		try {
			return new ResponseEntity<>(mapper.processMapping(srcAsses), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			List<String> errLst = new ArrayList<>();
			String msg = e.getMessage();
			if (msg.contains("No enum constant")) {
				msg += " / Valid Functions are " + FuncEnum.toList();
			}
			errLst.add(msg);
			return new ResponseEntity<>(errLst, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Gets the Data Mapper thread information.")
	@RequestMapping(value = "/threads", method = RequestMethod.GET)
	public List<String> threads() {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
		List<String> lst = new ArrayList<>();
		for (Thread t : threadArray) {
			lst.add(t.getName() + " : " + t.getState().toString());
		}
		return lst.stream().filter((s) -> s.startsWith(Constants.DATA_MAPPER)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Gets the list of Special Functions that can be used for mapping.")
	@RequestMapping(value = "/specialFunctions", method = RequestMethod.GET)
	public Map<String,String> specialFunctions() {
		return FuncEnum.valueMap();
	}
}

/*
    {
        "client": "SOME",
        "data": [{
            "EmployeeFirstName": "Sachin WhatUp",
            "EmployeeLastName": "Jain",
            "EmployeeID": "1234",
            "EmployeeSSN": "987654321",
            "Codes": {
                "X1": "Y1",
                "X2": "Y2"
            }
        }, {
            "EmployeeFirstName": "Shalina",
            "EmployeeLastName": "Jain",
            "EmployeeID": "",
            "EmployeeSSN": "98989898",
            "Codes": {
                "X1": "Y1"
            }
        }],
        "integrationKey": "12345",
        "mapName": "Test",
        "messageId": "67890"
    }
*/
