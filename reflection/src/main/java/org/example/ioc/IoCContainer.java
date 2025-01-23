package org.example.ioc;

import org.example.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class IoCContainer {
    private final Map<Class<?>,Object> registry = new HashMap<>();

    public <T> void register(Class<T> clazz, T instance){
        registry.put(clazz, instance);
    }

    public <T> T getBean(Class<T> clazz){
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            injectDependencies(instance);
            return instance;
        }catch (Exception e){
            throw new RuntimeException("failed  to create bean"+clazz.getName(), e);
        }

    }
    private void injectDependencies(Object instance) throws IllegalAccessException {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoWired.class)) {
                Class<?> dependencyType = field.getType();
                Object dependency = registry.get(dependencyType);
                if (dependency == null) {
                    throw new RuntimeException("No registered dependency for: " + dependencyType.getName());
                }
                field.setAccessible(true);
                field.set(instance, dependency);
            }
        }
    }

    public static void main(String[] args) {
        IoCContainer container = new IoCContainer();
        container.register(MessageService.class, new HelloMessageService());

        MyApplication app = container.getBean(MyApplication.class);
        app.run();
    }
}
