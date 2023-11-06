package com.github.vladimirplotnikov.homework;

import com.github.vladimirplotnikov.homework.listeners.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception{
        //
        Path source = Paths.get(Main.class.getResource("/").getPath());
        Path newFolder = Paths.get(source.toAbsolutePath() + "/resources/");
        try {
            Files.createDirectories(newFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(newFolder);
        //
        Random random = new Random();
        EventPublisher publisher = new EventPublisher();
        publisher.start();

        EventedList list = new EventedList();
        list.setPublisher(publisher);

        publisher.subscribe(EventType.ADD, new InsertListener());
        publisher.subscribe(EventType.DELETE, new DeleteListener());

        for (int i = 0; i <= 10; i ++) {
            if (list.size>1) {
                if (random.nextInt(1, 2)==1) {
                    list.add(random.nextInt(1, 5000));}
                {list.remove(random.nextInt(0, list.size - 1));}
            }
            {   list.add(random.nextInt(1, 5000));}
        }
        System.out.println("Done");

        publisher.finished=true;

    }
}