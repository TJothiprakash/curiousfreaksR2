package fun_facts;
import java.util.HashMap;
import java.util.Map;
public class TrialMaps {
}



// Define a custom interface
interface MyInterface {
    void execute(); // Abstract method to be implemented by methods we call
}

class KeywordMethodDispatcher {

    // Define 4 different methods
    public static void method1() {
        System.out.println("Method 1 called!");
    }

    public static void method2() {
        System.out.println("Method 2 called!");
    }

    public static void method3() {
        System.out.println("Method 3 called!");
    }

    public static void method4() {
        System.out.println("Method 4 called!");
    }

    public static void main(String[] args) {
        // Create a map where the key is a String (keyword) and the value is an instance of MyInterface
        Map<String, MyInterface> methodMap = new HashMap<>();

        // Populate the map with keywords and their corresponding method implementations
        methodMap.put("first", KeywordMethodDispatcher::method1);  // Method 1 mapped to "first"
        methodMap.put("second", KeywordMethodDispatcher::method2); // Method 2 mapped to "second"
        methodMap.put("third", KeywordMethodDispatcher::method3);  // Method 3 mapped to "third"
        methodMap.put("fourth", KeywordMethodDispatcher::method4); // Method 4 mapped to "fourth"

        // Example input (this could be dynamically set)
        String inputKeyword = "second";  // This will decide which method to call

        // Fetch and execute the method associated with the input keyword, or print "Invalid keyword" if not found
        MyInterface methodToExecute = methodMap.getOrDefault(inputKeyword, () -> System.out.println("Invalid keyword"));
        methodToExecute.execute(); // Calls the respective method based on the keyword
    }
}

