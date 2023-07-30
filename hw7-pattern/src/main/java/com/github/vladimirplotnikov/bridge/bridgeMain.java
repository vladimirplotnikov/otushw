package com.github.vladimirplotnikov.bridge;

public class bridgeMain {
    public static void main (String... s) {
        Shape circle = new Circle(new Red());
        System.out.println(circle.draw());
        Shape rectangle = new Rectangle(new Blue());
        System.out.println(rectangle.draw());
        Shape triangle = new Triangle(new Blue());
        System.out.println(triangle.draw());
    }
}
