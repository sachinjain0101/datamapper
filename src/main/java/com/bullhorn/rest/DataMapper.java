package com.bullhorn.rest;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullhorn.json.model.SourceAssignments;
import com.bullhorn.json.model.TargetAssignments;
import com.bullhorn.json.model.TargetMappings;
import com.bullhorn.persistence.timecurrent.dao.MapDAO;
import com.bullhorn.services.Mapper;
import com.google.gson.JsonSyntaxException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Base resource for Opera-DataMapper")
@RequestMapping("/maps")
public class DataMapper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataMapper.class);

    final MapDAO map;

    final Mapper mapper;

    @Autowired
    public DataMapper(MapDAO map, Mapper mapper) {
        this.map = map;
        this.mapper = mapper;
    }

    @ApiOperation(value="Test to see Data Mapper is working or not.")
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public static String test() {
		return "Opera-DataMapper is running...";
	}

	@ApiOperation(value="Gets the map information.")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public TargetMappings Get(@RequestParam(value="mapName") String mapName) {
		return new TargetMappings(map.getMapDetail(mapName));
	}
	
	@ApiOperation(value = "Processes the source JSON and gives out the destination JSON.")
	@RequestMapping(value = "/process",method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<TargetAssignments> Process(@RequestBody SourceAssignments srcAsses) {
		LOGGER.info("{}",srcAsses.toString());
		try {
			return new ResponseEntity<>(mapper.ProcessMapping(srcAsses),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new TargetAssignments(null),HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
