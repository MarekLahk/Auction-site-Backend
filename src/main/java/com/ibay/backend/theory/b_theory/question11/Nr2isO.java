package com.ibay.backend.theory.b_theory.question11;


import java.util.List;

public class Nr2isO {

    //todo this is a contribution based question so make sure to keep commits separate
    //todo A What does O stand for in SOLID? Explain the principle.
    // Open-closed Principle, Objects or entities should be open for extension, but closed for modification. An object's
    // or entity's behaviour is allowed to be extended without modifying its source code.
    //todo B Give an example. Write actual or pseudo code.
}

// The wrong way:

class Rectangle {
    public int width;
    public int height;
}

class Circle {
    public int radius;
}

public int Area(List<Object> shapes) {
    int area = 0;
    for (Object shape : shapes) {
        if (shape == Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            area += rectangle.width * rectangle.height;
        } else {
            Circle circle = (Circle) shape;
            area += circle.radius * circle.radius * Math.PI;
        }
    }
    return area;
}

// Correct:

abstract class Shape {
    public abstract int Area();
}

class Rectangle extends Shape {
    public int width;
    public int height;
    @Override
    public int Area() {
        return width * height;
    }
}

class Circle extends Shape {
    public int radius;

    @Override
    public int Area() {
        return radius * radius * Math.PI;
    }
}

public int Area(List<Shape> shapes) {
    int area = 0;
    for (Shape i: shapes) {
        area += i.Area();
    }
    return area;
}
