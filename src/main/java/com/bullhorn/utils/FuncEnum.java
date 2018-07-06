package com.bullhorn.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum FuncEnum {
    UNIX_MILLISEC_CONVERSION, STATE_CODE_CONVERSION;

    public static List<String> list = new ArrayList<>();
    public static Map<String,String> map = new LinkedHashMap<>();

    public static List<String> toList(){
        for(FuncEnum e:FuncEnum.values()){
            list.add(e.toString());
        }
        return list;
    }

    public static Map<String,String> valueMap(){
        map.put("USAGE","<Function Name>#<JavaScript Expression> (# is IMPORTANT)");
        map.put(UNIX_MILLISEC_CONVERSION.toString(),"Converts Unix Milli Seconds to UTC Timestamp. Sample: UNIX_MILLISEC_CONVERSION#dateBegin.trim()");
        map.put(STATE_CODE_CONVERSION.toString(),"Converts State Name to State Code. Sample: STATE_CODE_CONVERSION#jobOrderAddressState.trim()");
        return map;
    }
}
