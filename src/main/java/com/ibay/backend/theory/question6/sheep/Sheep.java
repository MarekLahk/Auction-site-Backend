package com.ibay.backend.theory.question6.sheep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Sheep {

    private Long id;
    private String name;
    private Integer age;
    private String color;

    public Sheep(@JsonProperty Long id,
                 @JsonProperty String name,
                 @JsonProperty Integer age,
                 @JsonProperty String color) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Sheep(){};

}
