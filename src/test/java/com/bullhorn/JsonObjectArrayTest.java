package com.bullhorn;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;

public class JsonObjectArrayTest {

    public static void main(String[] args){
        String msg = "[{\n" +
                "            \"EmployeeFirstName\": \"Sachin WhatUp\",\n" +
                "            \"EmployeeLastName\": \"Jain\",\n" +
                "            \"EmployeeID\": \"1234\",\n" +
                "            \"EmployeeSSN\": \"987654321\",\n" +
                "            \"Codes\": {\n" +
                "                \"X1\": \"Y1\",\n" +
                "                \"X2\": \"Y2\"\n" +
                "            }\n" +
                "        }, {\n" +
                "            \"EmployeeFirstName\": \"Shalina\",\n" +
                "            \"EmployeeLastName\": \"Jain\",\n" +
                "            \"EmployeeID\": \"\",\n" +
                "            \"EmployeeSSN\": \"98989898\",\n" +
                "            \"Codes\": {\n" +
                "                \"X1\": \"Y1\"\n" +
                "            }\n" +
                "        }]";

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        JsonArray array = parser.parse(msg).getAsJsonArray();
        JsonObject[] data = new JsonObject[array.size()];
        for(int i=0; i<array.size();i++){
            System.out.println(array.get(i));
            Arrays.fill(data,array.get(i));
        }

        System.out.println(Arrays.toString(data));

        //sourceAssignments.setData(parser.parse(msg.getMessage()).getAsJsonObject().getAsJsonArray());

    }

}
