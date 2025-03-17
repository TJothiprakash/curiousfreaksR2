package collections_practice;

public class MethodRef {
    public static void main(String[] args) {
        // Using lambda expression to call the static method
        Operation operation = MultipleMethodRef::add;
        int result = operation.operate(4, 5);
        System.out.println(result); // Outputs: 9
    }

    // Functional interface for method reference
    interface Operation {
        int operate(int a, int b);
    }

    // Static method to add two integers
    static int add(int a, int b) {
        return a + b;
    }
}
