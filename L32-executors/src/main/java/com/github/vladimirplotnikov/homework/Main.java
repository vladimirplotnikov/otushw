package com.github.vladimirplotnikov.homework;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CountThread calculationThread = new CountThread(ThreadMode.Counter, "Поток 1");
        CountThread displayThread = new CountThread(ThreadMode.Printer, "Поток 2");

        calculationThread.start();
        displayThread.start();

    }
}

