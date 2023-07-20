package com.github.vladimirplotnikov.json;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class OutLast implements Serializable
{

    private String last;
    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }


}