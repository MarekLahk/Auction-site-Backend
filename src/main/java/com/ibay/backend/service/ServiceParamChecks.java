package com.ibay.backend.service;

import java.util.HashMap;
import java.util.Map;

public class ServiceParamChecks {


    public static final Map<String, String> auctionConversionMap = Map.ofEntries(
            Map.entry("id", "auctionid"),
            Map.entry("ownerid", "auctionOwner"),
            Map.entry("title", "title"),
            Map.entry("description", "description"),
            Map.entry("category", "category"),
            Map.entry("limit", "limit"),
            Map.entry("offset", "offset")
    );

    public static final Map<String, String> userConversionMap = Map.ofEntries(
            Map.entry("username", "username"),
            Map.entry("email", "email")
    );

    public static final Map<String, String> bidConversionMap = Map.ofEntries(
            Map.entry("limit", "limit"),
            Map.entry("auctionid", "bidauctionid"),
            Map.entry("offset", "offset")
    );


    public static Map<String, String> convertRequestParams(Map<String, String> params, Map<String, String> conversionMap) {

        Map<String, String> output = new HashMap<>();
        for (String param: params.keySet()) {
            if (conversionMap.containsKey(param.toLowerCase())) {
                output.put(conversionMap.get(param.toLowerCase()), params.get(param));
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

    public static Integer getRequestLimit(Map<String, String> params) {
        return getRequestLimit(params, 20);
    }

    public static Integer getRequestLimit(Map<String, String> params, Integer maxResponse) {
        if (params.containsKey("limit")) {
            String limitString = params.get("limit");
            params.remove("limit");
            try {
                int limit = Integer.parseInt(limitString);
                return limit <= maxResponse ? limit: maxResponse;
            } catch (NumberFormatException e) {
                return maxResponse;
            }
        } return maxResponse;
    }

    public static Integer getRequestOffset(Map<String, String> params) {
        if (params.containsKey("offset")) {
            String offsetString = params.get("offset");
            params.remove("offset");
            try {
                int offset = Integer.parseInt(offsetString);
                if (offset <= 0) return 0;
                return offset;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

}
