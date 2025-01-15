package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class AnnotationDetector {
    public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> targetAnnotation){

        for (Annotation annotation : element.getAnnotations()) {
            if (isAnnotationPresent(annotation.annotationType(), targetAnnotation)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isAnnotationPresent(Class<? extends Annotation> annotationType, Class<? extends Annotation> targetAnnotation){
        final Set<Class<? extends Annotation>> visited = new HashSet<>();
        Stack<Class<?extends Annotation>> stack = new Stack<>();
        visited.add(annotationType);
        if(annotationType.equals(targetAnnotation)){
            return true;
        }

        for(Annotation metaAnnotation : annotationType.getAnnotations()){
            if(!visited.contains(metaAnnotation.annotationType())){
                visited.add(metaAnnotation.annotationType());
                stack.push(metaAnnotation.annotationType());
            }
        }
        while(!stack.isEmpty()){
            Class<? extends Annotation> annotation = stack.pop();

            if(annotation.equals(targetAnnotation)){
                return true;
            }

            for(Annotation metaAnnotation : annotation.getAnnotations()){
                if(!visited.contains(metaAnnotation.annotationType())){
                    visited.add(metaAnnotation.annotationType());
                    stack.push(metaAnnotation.annotationType());
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // @CustomComponent -> @Component
        if(hasAnnotation(TestService.class, Component.class)){
            System.out.println(TestService.class.getName() + " including @Component");
        }else{
            System.out.println(TestService.class.getName() + " not including @Component");
        }

        // @Component X
        if(hasAnnotation(FieldUsage.class, Component.class)){
            System.out.println(FieldUsage.class.getName() + " including @Component");
        }else{
            System.out.println(FieldUsage.class.getName() + " not including @Component");
        }

        // @Annotation Cycle
        // non-stack overflow code
        if(hasAnnotation(ClassLoad.class, Component.class)){
            System.out.println(CycleComponent2.class.getName() + " including @Component");
        }else{
            System.out.println(CycleComponent2.class.getName() + " not including @Component");
        }
    }
}
