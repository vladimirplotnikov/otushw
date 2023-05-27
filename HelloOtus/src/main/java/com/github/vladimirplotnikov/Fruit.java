package com.github.vladimirplotnikov;

public class Fruit {
    private final double weight;
    private final int quantity;

    public Fruit(double weight, int quantity) {
        this.weight = weight;
        this.quantity = quantity;
    }
    public double getWeight() {
        return weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void eat(){
        System.out.println("NomNomNom");
    }
}
