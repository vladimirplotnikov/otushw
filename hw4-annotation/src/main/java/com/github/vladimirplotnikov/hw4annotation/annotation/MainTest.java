package com.github.vladimirplotnikov.hw4annotation.annotation;

import com.github.vladimirplotnikov.hw4annotation.annotation.After;
import com.github.vladimirplotnikov.hw4annotation.annotation.Before;
import com.github.vladimirplotnikov.hw4annotation.annotation.Test;

import java.lang.reflect.InvocationTargetException;

//класс-тест, в котором методы отмеченные аннотациями
public class MainTest {

    @Before
    public void setUp() {
        System.out.println("Сначала надо выпить...чаю.");
    }

    @Before
    public void setUpCoffee() {
        System.out.println("Сначала надо выпить...кофе.");
    }

    @Test
    public void thisTestNothing() {
        System.out.println("Самый успешный тест");
    }
    @Test
    public void failedTest() {
        int a = 1/0;
        System.out.println("Пока просто тест");
    }
    @After
    public void setAfter() {
        System.out.println("И только теперь можно идти спать");
    }
}