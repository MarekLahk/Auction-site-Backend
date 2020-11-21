package com.ibay.backend.theory.a_theory.question3;

public class Dog {

    int weight;
    String name;
    int age;

    public Dog(int weight, String name, int age) {
        this.weight = weight;
        this.name = name;
        this.age = age;
    }

    public int ageToDogYears() {
    return age*4;
    }
}
