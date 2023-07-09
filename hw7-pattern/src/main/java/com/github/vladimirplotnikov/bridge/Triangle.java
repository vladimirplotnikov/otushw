package com.github.vladimirplotnikov.bridge;

public class Triangle extends Shape{
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return this.getClass().getSimpleName()+" drawn. " + color.fill();
    }
}