package com.ibay.backend.theory.question3;

public class Dog extends Animal {


    public Dog(String name, Integer age) {
        super(name, age);
    }

    @Override
    public String makeNoise() {
        return "Bark, Bark!";
    }
}
