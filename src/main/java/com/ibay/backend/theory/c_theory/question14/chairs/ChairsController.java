package com.ibay.backend.theory.c_theory.question14.chairs;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/chair")
@RestController
@NoArgsConstructor
public class ChairsController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for chairs.
    // Think a backoffice system for furniture shop like Aatrium or some Kalamaja chair boutique.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //todo A add necessary annotations on the class

    //todo C create a method to query single chair
    @GetMapping
    public List<Chair> getChairs(@RequestParam Map<String, String> param) {
        return new ArrayList<>();
    }

    //todo D create a method to save a chair
    public Chair addNewChair(@RequestBody Chair chair) {
        return new Chair();
    }

    //todo E create a method to update a chair
    @PutMapping
    public Chair updateChair(@RequestBody Chair chair) {
        return new Chair();
    }

    //todo F create a method to delete a chair
    @DeleteMapping(path = "{name}")
    public void deleteChair(@PathVariable String name) {}

    //todo G assuming each chair has a designer (one-to-one relation) create a method to query chair's designer
    @GetMapping(path = "designer/{chairName}")
    public Designer getDesigner(@PathVariable String chairName) {
        return new Designer();
    }

    //todo H create a method to update chair's name (and nothing else)
    @PutMapping(path = "{name}")
    public Chair updateChairName(@PathVariable String name,@RequestBody String newName) {
        return new Chair();
    }

    //todo B create a method to query chairs (plural)
    //querying for all chairs
    //todo I modify correct method to support searching chairs by type while keeping original functionality
    //example chair?type=soft
    //todo J modify correct method to support searching chairs by whether chair is in stock while keeping original functionality
    //example chair?inStock=true
    //todo K modify correct method to order/sort chairs
    // * by lowest priced first
    // * by highest priced first
    // (you can assume that by default it searches most popular first)
    //example chair=sorted=lowestPrice
    //example chair=sorted=highestPrice

}
