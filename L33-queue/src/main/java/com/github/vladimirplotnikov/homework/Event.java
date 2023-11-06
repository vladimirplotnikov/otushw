package com.github.vladimirplotnikov.homework;

public class Event {
    private final EventType type;
    private final Object object;

    public Event(EventType type, Object object) {
        this.type = type;
        this.object = object;
    }

    public EventType getType() {
        return type;
    }

    public Object getObject() {
        return object;
    }
}
