package com.github.vladimirplotnikov.bridge;

public class Rectangle extends Shape{
    public Rectangle(Color color) {
        super(color);
    }

    @Override
    public String draw() {
        return this.getClass().getSimpleName()+" drawn. " + color.fill();
    }
}