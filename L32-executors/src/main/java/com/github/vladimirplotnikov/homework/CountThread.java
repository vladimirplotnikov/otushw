package com.github.vladimirplotnikov.homework;


public class CountThread extends Thread {
    private static final int minValue = 1;
    private static final int maxValue = 10;
    private static final Object Sync = new Object();
    private static int count = 0;
    private final ThreadMode threadWorkMode;
    private static ThreadMode currentWorkMode = ThreadMode.Counter;
    private boolean directionCount = true;

    public CountThread(ThreadMode threadWorkMode, String threadName) {
        this.threadWorkMode = threadWorkMode;
        setName(threadName);
    }

    public void run() {
        Thread currentThread = Thread.currentThread();

        while (!currentThread.isInterrupted()) {
            synchronized (Sync) {
                //по-умолчанию currentWorkMode=Счётчик, потому второй поток уйдет ждать
                while (threadWorkMode != currentWorkMode) {
                    try {
                        Sync.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                if (currentWorkMode == ThreadMode.Counter) {
                    count();
                    currentWorkMode = ThreadMode.Printer;
                } else {
                    currentWorkMode = ThreadMode.Counter;
                }

                System.out.println("{"+Thread.currentThread().getName()+"}:"+ count);

                Sync.notifyAll();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void count() {
        if (count == maxValue) {
            directionCount = false;
        }
        if (count == minValue) {
            directionCount = true;
        }

        if (directionCount) {
            count++;
        } else {
            count--;
        }
    }

}

