package org.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.HashSet;
import java.util.Set;

public class AnnotationDetector {
    /**
     * 지정한 어노테이션이 존재하는지 확인해주는 메소드
     */
    public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> targetAnnotation){
        final Set<Class<? extends Annotation>> visited = new HashSet<>();
        for (Annotation annotation : element.getAnnotations()) {
            if (isAnnotationPresent(annotation.annotationType(), targetAnnotation, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 재귀적 호출을 통해서 자기 자신이 가지고 있는 코드가 targetAnnotation 인지 확인하는 메소드
     */
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
        if(hasAnnotation(CycleComponent2.class, Component.class)){
            System.out.println(CycleComponent2.class.getName() + " including @Component");
        }else{
            System.out.println(CycleComponent2.class.getName() + " not including @Component");
        }
    }
}
