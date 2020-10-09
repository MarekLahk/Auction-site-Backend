package com.ibay.backend.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ServiceParamChecks {


    private static final Map<String, String> auctionConversionMap = Map.ofEntries(
            Map.entry("id", "auctionID"),
            Map.entry("ownerid", "auctionOwner"),
            Map.entry("title", "title"),
            Map.entry("description", "description")
    );


    public static Map<String, String> convertAuctionRequestParams(Map<String, String> params) {

        Map<String, String> output = new HashMap<>();
        for (String param: params.keySet()) {
            if (auctionConversionMap.containsKey(param.toLowerCase())) {
                output.put(auctionConversionMap.get(param.toLowerCase()), params.get(param));
            }
        }
        return output;
    }

    public static Map<String, String> removeEmptyParams(Map<String, String> params) {
        Map<String, String> output = new HashMap<>();
        for (String param:params.keySet()) {
            if (!param.strip().equals("")) {
                output.put(param, params.get(param));
            }
        }
        return output;
    }

    public static Boolean isParamsEmpty(Map<String, String> params) {
        return params.isEmpty();
    }
}
