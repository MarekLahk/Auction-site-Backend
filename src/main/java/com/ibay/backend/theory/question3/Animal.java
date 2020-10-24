package com.ibay.backend.theory.question3;

public abstract class Animal {

    private String name;
    private Integer age;

    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    abstract public String makeNoise();

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
