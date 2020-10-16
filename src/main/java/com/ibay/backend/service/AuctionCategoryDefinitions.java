package com.ibay.backend.service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuctionCategoryDefinitions {

    public static final Set<String> auctionCategories = new HashSet<>() {{
       add("all");
       add("computers");
       add("clothes");
       add("household");
       add("toys");
       add("automotive");
       add("kitchen");
       add("misc");
    }};
}
