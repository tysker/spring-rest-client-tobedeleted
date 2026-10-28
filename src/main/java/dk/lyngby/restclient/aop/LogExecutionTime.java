package dk.lyngby.restclient.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)          // Tells Java this annotation can only be placed on methods
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
}
