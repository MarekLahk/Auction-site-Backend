package com.ibay.backend.theory.c_theory.question14.phones;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/v1/phone")
@RestController
@NoArgsConstructor
public class PhonesController {

    //todo for question 14 there are 4 assignments in total
    // Each person has to do only 1. So 2 person team has to do 2 different ones, 3 person - 3, 4 person - 4.
    // Make sure to commit under your user otherwise points won't count.
    // I didn't number these so you can pick your favorite

    //todo
    // You are creating a rest controller for lessons. Think of a phone shop.
    // You need to add necessary annotations and methods to this class.
    // This class should compile.
    // It should run successfully when moved to your application package.
    // Method body is not important and will not be graded.
    // Modifying other classes is unnecessary and will not be graded.

    //!!! Note Rearranged todos to show which method is for which todo !!!

    //todo A add necessary annotations on the class


    //todo B create a method to query phones (plural)
    //Assuming query phones means querying for all the phones.
    //todo I modify correct method to support searching by manufacturer while keeping original functionality
    //Ex. phone?manufacturer=nokia
    //todo J modify correct method to support searching by price range: priceFrom-priceTo while keeping original functionality
    //Ex. phone?priceFrom=10&priceTo=40
    //todo K modify correct method to order/sort chairs
    // * by latest released date first
    // * by earliest released date first
    // (you can assume that by default it searches most popular first)
    //Ex. phone?sort=latest, phone?sort=earliest

    @GetMapping
    public List<Phone> getPhones(@RequestParam Map<String, String> parameters) {return new ArrayList<>();}


    //todo C create a method to query single phone

    @GetMapping(path = "{name}")    // Assuming phones names can be used as a unique identifier
    public Phone getPhoneByName(@PathVariable String name) {
        return new Phone();
    }


    //todo D create a method to save a phone

    @PostMapping
    public Phone addNewPhone(@RequestBody Phone phone) {return new Phone();}


    //todo E create a method to update a phone

    @PutMapping
    public Phone updatePhone(@RequestBody Phone phone) {return new Phone();}


    //todo F create a method to delete a phone

    @DeleteMapping(path = "{name}")
    public void deletePhone(@PathVariable String name) {}


    //todo G assuming each phone has apps installed (one-to-many relation) create a method to query phone's apps

    @GetMapping(path = "apps/{phoneName}")
    public List<App> getPhoneApps(@PathVariable String phoneName) {return new ArrayList<>();}


    //todo H create a method to update phone's price (and nothing else)

    @PutMapping(path = "{name}")
    public Phone updatePhonePrice(@PathVariable String name, @RequestBody Double newPrice) {return new Phone();}

}
