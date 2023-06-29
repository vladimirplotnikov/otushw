package com.github.vladimirplotnikov.hw4annotation.annotation;

import java.lang.reflect.*;
import java.util.Arrays;
import com.github.vladimirplotnikov.hw4annotation.annotation.*;
public class TestProcessor {
    private static int fail;
    private static int success;

    public static void test(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        Method[] methods = clazz.getDeclaredMethods();
        Method[] beforeMethods = getBeforeMethods(methods);
        Method[] afterMethods = getAfterMethods(methods);
        Method[] testMethods = getTestMethods(methods);

        for(Method testMethod : testMethods) {
            try {
            invoke(beforeMethods,clazz);
            Method[] curMethod = {testMethod};
            invoke(curMethod,clazz);
            success++;}
            catch (Exception e)
            {e.printStackTrace();
                fail++;
            }
            finally {
                invoke(afterMethods,clazz);
            }
        }
        System.out.println("Отработало успешно "+success+", упало="+fail);

    }


    private static Method[] getBeforeMethods(Method[] methods){
    return Arrays.stream(methods).filter(method -> method.isAnnotationPresent(Before.class)).toArray(Method[]::new);
        };
    private static Method[] getAfterMethods(Method[] methods){
        return Arrays.stream(methods).filter(method -> method.isAnnotationPresent(After.class)).toArray(Method[]::new);
    };
    private static Method[] getTestMethods(Method[] methods){
        return Arrays.stream(methods).filter(method -> method.isAnnotationPresent(Test.class)).toArray(Method[]::new);
    };

    private static void invoke(Method[] methods,Class<?> clazz) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        for (Method method:methods
        ) {
            String funClass = method.getDeclaringClass().getName();
            Class c = Class.forName(funClass);
            Object o = c.getDeclaredConstructor().newInstance();
            //method.setAccessible(true);
            method.invoke(o);
        }
    };
}