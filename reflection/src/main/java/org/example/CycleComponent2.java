package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@CustomComponent
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@CycleComponent
public @interface CycleComponent2 {
}
