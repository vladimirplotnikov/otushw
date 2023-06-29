package com.github.vladimirplotnikov.hw7autolog.autolog;

//класс-тест, в котором методы отмеченные аннотациями

class TestLogging implements TestLoggingInterface {

    @Log
    public void calculation(int param) {}
    @Log
    public void calculation(int param,int param2) {}
    @Log
    public void calculation(int param, int param2, String param3) {}

}