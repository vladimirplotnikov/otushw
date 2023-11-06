package com.github.vladimirplotnikov.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class EventPublisher {
    public volatile boolean finished;
    Map<EventType, List<EventListener>> subscribers = new ConcurrentHashMap<>();

    LinkedBlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>();

    public void publish(Event event) {
        eventQueue.add(event);
    }

    public void notifySubscibers(Event event) {
        if (subscribers.containsKey(event.getType())) {
            subscribers.get(event.getType()).forEach(subscriber -> subscriber.accept(event));
        }
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        Event event =eventQueue.poll(100, TimeUnit.MILLISECONDS);
                        if (event!=null) {
                        notifySubscibers(event);}
                        if (finished && eventQueue.isEmpty())
                            return;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (true);
            }
        }).start();
    }

    public void subscribe(EventType type, EventListener listener) {
        subscribers.computeIfAbsent(type, key -> new ArrayList<>()).add(listener);
    }


}
