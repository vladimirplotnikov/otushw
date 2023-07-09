package com.github.vladimirplotnikov.decoratororder;

public abstract class OrderDecorator implements Order{
    private final Order orderDecorated;
    public OrderDecorator (Order orderDecorated) {
        this.orderDecorated = orderDecorated;
    }
    @Override
    public void setOrder(String name) {
        orderDecorated.setOrder(name);
    }
    @Override
    public String getOrder() {
        return orderDecorated.getOrder();
    }
}
