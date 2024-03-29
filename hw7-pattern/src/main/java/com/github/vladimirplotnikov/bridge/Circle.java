package com.github.vladimirplotnikov.bridge;

public class Circle extends Shape{
    public Circle(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return "Circle drawn. " + color.fill();
    }
}
