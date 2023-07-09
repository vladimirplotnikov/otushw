package com.github.vladimirplotnikov.decoratororder;

public class OrderAddition extends OrderDecorator{
    public OrderAddition(Order c) {
        super(c);
    }

    @Override
    public void setOrder(String name) {
        super.setOrder(getOrder()+','+name);
    }

    @Override
    public String getOrder() {
        return super.getOrder();
    }
}
