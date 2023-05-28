package com.github.vladimirplotnikov;

public class Fruit {
    private final double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

    public void eat(){
        System.out.println("NomNomNom");
    }
}
