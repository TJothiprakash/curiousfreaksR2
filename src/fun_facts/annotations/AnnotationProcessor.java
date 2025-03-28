package fun_facts.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationProcessor {
    public static void main(String[] args) {
        AClass aClass = new AClass();

        try {
            // Get the method object
            Method method = AClass.class.getMethod("mehtod");

            // Check if @Hi is present
            if (method.isAnnotationPresent(Hi.class)) {
                Hi annotation = method.getAnnotation(Hi.class);
                System.out.println(annotation.value());// Prints: Hi handsome!!!
                System.out.println(Arrays.toString(annotation.arrayofSomething()));  // ✅ Correct way to print array
                System.out.println((annotation.arrayofSomething()));  // ✅ Correct way to print array
            }

            // Call the method
            aClass.mehtod();


            // Get class object
            Class<JP> clazz = JP.class;

            // Iterate over all fields
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Hi.class)) {
                    Hi annotation = field.getAnnotation(Hi.class);
                    System.out.println("Field: " + field.getName());
                    System.out.println("Value: " + annotation.value());
                    System.out.println("Array: " + Arrays.toString(annotation.arrayofSomething()));
                }
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
