package com.ibay.backend.theory.a_theory.question6.chocolate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Cake {

    private Long id;
    private String size;
    private String sweetness;
    private List<String> ingredients;
    private List<String> toppings;

    public Cake(@JsonProperty Long id,
                @JsonProperty String size,
                @JsonProperty String sweetness,
                @JsonProperty List<String> ingredients,
                @JsonProperty List<String > toppings) {
        this.id = id;
        this.size = size;
        this.sweetness = sweetness;
        this.ingredients = ingredients;
        this.toppings = toppings;
    }

    public Cake(){};
}
