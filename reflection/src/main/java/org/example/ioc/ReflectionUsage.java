package org.example.ioc;

import org.example.At;
import org.example.Component;
import org.example.ioc.AnnotationDetector;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.Set;

@At
public class ReflectionUsage {
    /**
     * @param clazz
     * @return is not class(boolean)
     */
    public static boolean isNotCorrectClass(Class<?> clazz){
        return clazz.isAnnotation()
                || clazz.isInterface()
                || clazz.isRecord()
                || clazz.isEnum()
                || Modifier.isAbstract(clazz.getModifiers());
    }

    public static void IoCProcessor(){
        Reflections reflection = new Reflections(
                new ConfigurationBuilder()
                        .forPackages("org.example")
                        .addScanners(new SubTypesScanner(false))
        );

        Set<Class<?>> classes = reflection.getSubTypesOf(Object.class);

        for(Class<?> clazz : classes){
            if(isNotCorrectClass(clazz)){
                continue;
            }
            boolean hasAnnotation = AnnotationDetector.hasAnnotation(clazz, Component.class);

        }
    }
    public static void main(String[] args) {
        Set<Class<? extends Annotation>> targets = Set.of(Component.class, At.class);

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackages("org.example")
                        .addScanners(new SubTypesScanner(false))
        );

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        System.out.println("Discovered classes: " + classes.size());

        for (Class<?> clazz : classes) {
            if(isNotCorrectClass(clazz)){
                continue;
            }
            System.out.println("Found correct class: " + clazz.getName());
            for(Class<? extends Annotation> target:targets){
                boolean hasAnnotation = AnnotationDetector.hasAnnotation(clazz, target);
                System.out.println(clazz.getName() + ((hasAnnotation) ? " has " : " hasn't ") + target.getName());
            }
        }
    }
}
