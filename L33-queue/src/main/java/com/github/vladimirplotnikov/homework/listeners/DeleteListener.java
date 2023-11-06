package com.github.vladimirplotnikov.homework.listeners;

import com.github.vladimirplotnikov.homework.*;

public class DeleteListener implements EventListener {
    @Override
    public void accept(Event event) {
        Writer.write("deleted.txt",event.getObject().toString());
    }
}
