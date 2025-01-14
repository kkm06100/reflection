package org.example;

import org.reflections.Reflections;

import java.util.Set;

public class ReflectionUsage {
    public static void main(String[] args) {
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for(Class<?> clazz : classes){
            System.out.println("found class: " + clazz.getName());

            Object instance;
            try{
                instance = clazz.getDeclaredConstructor().newInstance();
                System.out.println("Created instance of: " + clazz.getName());
            }catch (Exception e){
                System.out.println("Couldn't instantiate: " + clazz.getName());
                continue;
            }
            if(clazz.isAnnotationPresent(At.class)){

            }

        }
    }
}
