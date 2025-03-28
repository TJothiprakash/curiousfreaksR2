package fun_facts.annotations;

import org.w3c.dom.ls.LSOutput;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation to indicate that a method should say "Hello, World!"
 */
@Target({ElementType.METHOD, ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Hi {
String value() default "Hi handsome!!! ";
   String [] arrayofSomething() default {"hello", "hi"};
}
