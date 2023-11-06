package com.github.vladimirplotnikov.homework.listeners;
import com.github.vladimirplotnikov.homework.*;

public class InsertListener implements EventListener {
    @Override
    public void accept(Event event) {
        Writer.write("inserted.txt",event.getObject().toString());
    }
}
