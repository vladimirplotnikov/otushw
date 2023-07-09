package com.github.vladimirplotnikov.bridge;

public class Red implements Color{
    @Override
    public String fill() {
        return "Color is "+this.getClass().getSimpleName();
    }
}
