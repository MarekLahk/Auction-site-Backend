package com.ibay.backend.theory.a_theory.question6.vineyard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Wine {
    private Long id;
    private String name;
    private String region;
    private String grape;
    private Integer year;

    public Wine(@JsonProperty Long id,
                @JsonProperty String name,
                @JsonProperty String region,
                @JsonProperty String grape,
                @JsonProperty Integer year) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.grape = grape;
        this.year = year;
    }
    public Wine(){};
}
