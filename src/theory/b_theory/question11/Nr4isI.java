package com.ibay.backend.theory.b_theory.question11;

public class Nr4isI {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does I stand for in SOLID? Explain the principle.
    //todo B Give an example. Write actual or pseudo code.
    // Answer: I is for Interface segregation principle.
    // Principle states on splitting very large interfaces into smaller and specific interfaces.
    // Client will only know about the methods that he is interested of.
    // It also makes a system much easier to refactor, redeploy and change.
}

// this is an example with too many methods in one interface.
interface SportCar {
    void attend();
    void rallyCross();
    void drift();
    void dragRace();
    void timeAttack();
}

class RacingCar implements SportCar {

    @Override
    public void attend() {
        System.out.println("Sportcar is now attending the event.");
    }

    @Override
    public void rallyCross() {
        System.out.println("Sportcar is attending rallycross.");
    }

    @Override
    public void drift() {
        System.out.println("Sportcar is starting to drift.");
    }

    @Override
    public void dragRace() {

    }

    @Override
    public void timeAttack() {

    }
}

// this is how it should be using ISP
interface Car {
    void attend();
}

interface  DriftingCar extends Car {
    void drift();
    void dragRace();
    void timeAttack();
}

interface RallyCar extends Car {
    void rallyCross();
}

class AttendingCar implements RallyCar {

    @Override
    public void attend() {
        System.out.println("The car is attending an event.");
    }

    @Override
    public void rallyCross() {
        System.out.println("The car is attending now a rallycross.");
    }
}
