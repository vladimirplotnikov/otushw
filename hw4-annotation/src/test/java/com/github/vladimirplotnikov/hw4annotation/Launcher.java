package com.github.vladimirplotnikov.hw4annotation;

import com.github.vladimirplotnikov.hw4annotation.annotation.MainTest;
import com.github.vladimirplotnikov.hw4annotation.annotation.TestProcessor;

import java.lang.reflect.InvocationTargetException;

public class Launcher {
        public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
            TestProcessor.test(MainTest.class);
        }
}
