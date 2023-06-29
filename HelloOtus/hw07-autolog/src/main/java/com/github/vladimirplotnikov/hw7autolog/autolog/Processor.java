package com.github.vladimirplotnikov.hw7autolog.autolog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Proxy;

public class Processor {

    private static final Map<String, Parameter[]> methodMap = new HashMap<>();

    public static TestLoggingInterface createTestLogging(){
        InvocationHandler handler = new TestLoggingHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(Processor.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }

    static class TestLoggingHandler implements InvocationHandler {
        private final TestLoggingInterface originalObject;

        public TestLoggingHandler(TestLoggingInterface originalObject) {
            this.originalObject = originalObject;
            if (methodMap.size() == 0) {
                Arrays.stream(originalObject.getClass().getMethods()).forEach(method -> {
                    if (method.isAnnotationPresent(Log.class)) {
                        methodMap.put(buildMethodString(method), method.getParameters());
                    }
                });
            }
        }

        private String buildMethodString(Method method) {
            return method.getName() + Arrays.stream(method.getParameterTypes()).map(Class::getName).toList();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Parameter[] parameters = methodMap.get(buildMethodString(method));
            if (parameters != null) {
                StringBuilder builder = new StringBuilder("executed method: " + method.getName());

                for (int i = 0; i < parameters.length; i++) {
                    builder.append(", ").append(parameters[i].getName()).append(": ").append(args[i]);
                }

                System.out.println(builder);
            }
            return method.invoke(originalObject, args);
        }
    }
}