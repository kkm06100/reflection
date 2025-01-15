package org.example;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUsage {
    /**
     *
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
    public static void main(String[] args) {
        Set<Class<? extends Annotation>> targets = Set.of(Component.class);

        Reflections reflections = new Reflections(
                new ConfigurationBuilder()
                        .forPackages("org.example")
                        .addScanners(new SubTypesScanner(false))
        );

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        System.out.println("Discovered classes: " + classes.size());

        final Set<Class<? extends Annotation>> visited = new HashSet<>();
        for (Class<?> clazz : classes) {
            if(isNotCorrectClass(clazz)){
                continue;
            }
            System.out.println("Found correct class: " + clazz.getName());
            for(Class<? extends Annotation> target:targets){
                boolean hasAnnotation = AnnotationDetector.hasAnnotation(clazz, target, visited);
                System.out.println(clazz.getName() + ((hasAnnotation) ? " has " : " hasn't ") + target.getName());
            }
        }
    }
}
