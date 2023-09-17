package ru.otus.appcontainer;

import ru.otus.appcontainer.api.AppComponent;
import ru.otus.appcontainer.api.AppComponentsContainer;
import ru.otus.appcontainer.api.AppComponentsContainerConfig;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.*;

public class AppComponentsContainerImpl implements AppComponentsContainer {

    private final List<Object> appComponents = new ArrayList<>();
    private final Map<String, Object> appComponentsByName = new HashMap<>();

    public AppComponentsContainerImpl(Class<?> initialConfigClass) {
        processConfig(initialConfigClass);
    }

    private void processConfig(Class<?> configClass) {
        checkConfigClass(configClass);
        try {

            Object configObject = configClass.getConstructor().newInstance();
            Method[] methodArray = configClass.getDeclaredMethods();
            for (Method method : methodArray) {
                String componentName = method.getAnnotation(AppComponent.class).name();
                if (appComponentsByName.containsKey(componentName)) {
                    throw new RuntimeException("В контексте уже есть компонент с таким именем");
                }
                List<Object> parameters = (List<Object>) Arrays.stream(method.getParameterTypes()).map(type -> getAppComponent(type)).toList();
                Object classObject = method.invoke(configObject, parameters.toArray());
                appComponentsByName.put(method.getAnnotation(AppComponent.class).name(), classObject);
                appComponents.add(classObject);
            }
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception);
        }
    }

    private void checkConfigClass(Class<?> configClass) {
        if (!configClass.isAnnotationPresent(AppComponentsContainerConfig.class)) {
            throw new IllegalArgumentException(String.format("Given class is not config %s", configClass.getName()));
        }
    }

    @Override
    public <C> C getAppComponent(Class<C> componentClass) {
        List<Object> componentList = new ArrayList<>();
        for (Object appComponent : appComponents) {
            AnnotatedType[] annotatedInterfaces = appComponent.getClass().getAnnotatedInterfaces();
            for (AnnotatedType annotatedType : annotatedInterfaces) {
                if (annotatedType.getType().equals(componentClass)
                        || appComponent.getClass().isAssignableFrom(componentClass)
                ) {
                    componentList.add(appComponent);
                }
            }
        }
        if (componentList.size() != 1) {
            throw new RuntimeException("В контексте отсутствует искомый компонент");
        }
        return (C) componentList.get(0);
    }

    @Override
    public <C> C getAppComponent(String componentName) {
        return (C)appComponentsByName.get(componentName);
    }
}
