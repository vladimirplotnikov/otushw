package com.github.vladimirplotnikov.hw7autolog.autolog;

public class Launcher {
        public static void main(String[] args) {
            Processor.createTestLogging().calculation(1);
            Processor.createTestLogging().calculation(1, 2);
            Processor.createTestLogging().calculation(1, 2, "Third");
        }
}
