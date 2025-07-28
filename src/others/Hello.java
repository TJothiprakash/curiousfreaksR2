package others;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Hello {
    String value() default "Hello World!";
    String language() default "English";
    String author() default "Anonymous";
    String date() default "2022-01-01";
    String version() default "1.0";
    String license() default "MIT";
    String[] contributors() default {};
    String[] tags() default {};
    String[] links() default {};
}

