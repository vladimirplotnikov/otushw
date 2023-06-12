package com.github.vladimirplotnikov.annotation;
import com.github.vladimirplotnikov.annotation.processor.CustomToString;

@CustomToString
public class ClassWithCustomString implements ClassWithCustomStringInterface{
    private int day;
    private int month;
    private String event;

    public static void main(String[] args) {
        ClassWithCustomString me = new ClassWithCustomString();
        me.day=10;
        System.out.println("Hello");
        System.out.println(me.toString());
        System.out.println("Good bye");
    }
    public void setAge(int age) {

        System.out.println("Age");
        this.day = age;
    }

    @Override
    public void toString(ClassWithCustomString myClass) {

    }
}
