package com.github.vladimirplotnikov.decoratororder;

public class OrderMain {
    public static void main(String[] args) {
        Order order = new OrderImpl();
        order.setOrder("Пиццу");
        System.out.println(order.getOrder());
        order = new OrderAddition(order);
        order.setOrder("молочный коктейль");
        System.out.println(order.getOrder());
    }
}
