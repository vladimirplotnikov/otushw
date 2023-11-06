package com.github.vladimirplotnikov.homework;

import java.util.ArrayList;
import java.util.List;

public class EventedList {
    private List<Object> list = new ArrayList<>();
    int size=0;
    private EventPublisher publisher;

    public void add(Object object) {
        list.add(object);
        size++;
        System.out.println("add"+object+list.toString());
        publishEvent(EventType.ADD, object);
    }

    public void remove(int i) {
        int value=(int)list.get(i);
        Object removed = list.remove(i);
        System.out.println("remove "+value+list.toString());
        size--;
        publishEvent(EventType.DELETE, removed);
    }



    public void setPublisher(EventPublisher publisher) {
        this.publisher = publisher;
    }

    private void publishEvent(EventType type, Object item) {
        if (publisher == null) {
            return;
        }

        publisher.publish(new Event(type, item));
    }

}
