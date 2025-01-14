package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;

public class AnnotationDetector {
    public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> targetAnnotation){
        final Set<Class<? extends Annotation>> visited = new HashSet<>();
        for (Annotation annotation : element.getAnnotations()) {
            if (isAnnotationPresent(annotation.annotationType(), targetAnnotation, visited)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isAnnotationPresent(Class<? extends Annotation> annotationType, Class<? extends Annotation> targetAnnotation, Set<Class<? extends Annotation>> visited){
        visited.add(annotationType);
        if(annotationType.equals(targetAnnotation)){
            return true;
        }
        for(Annotation metaAnnotation : annotationType.getAnnotations()){
            if(visited.contains(annotationType)){
                if(isAnnotationPresent(metaAnnotation.annotationType(),targetAnnotation, visited)){
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        if(hasAnnotation(TestService.class, Component.class)){
            System.out.println("including @Component");
        }

        if(!hasAnnotation(FieldUsage.class, Component.class)){
            System.out.println("not including @Component");
        }
    }
}
