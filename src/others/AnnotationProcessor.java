package others;


public class AnnotationProcessor {
    public static void main(String[] args) {
        // Get the class object
        Class<MyClass> clazz = MyClass.class;


    MyClass myClass = new MyClass();
    myClass.sayHello();
        System.out.println(clazz.isAnnotationPresent(Hello.class));
        // Check if the annotation is present
        if (clazz.isAnnotationPresent(Hello.class)) {
            // Get the annotation
            Hello annotation = clazz.getAnnotation(Hello.class);

            // Print annotation details
            System.out.println("Value: " + annotation.value());
            System.out.println("Language: " + annotation.language());
            System.out.println("Author: " + annotation.author());
            System.out.println("Date: " + annotation.date());
            System.out.println("Version: " + annotation.version());
            System.out.println("License: " + annotation.license());

            // Print contributors
            System.out.println("Contributors: " + String.join(", ", annotation.contributors()));

            // Print tags
            System.out.println("Tags: " + String.join(", ", annotation.tags()));

            // Print links
            System.out.println("Links: " + String.join(", ", annotation.links()));
        } else {
            System.out.println("No @Hello annotation found!");
        }
    }
}
