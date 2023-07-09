package com.github.vladimirplotnikov.decoratororder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderImpl implements Order{
    private int OrderNo = 1;
    private String item;
    @Override
    public void setOrder(String name) {
        item = name;
    }

    @Override
    public String getOrder() {
        return item.toString();
    }
}
