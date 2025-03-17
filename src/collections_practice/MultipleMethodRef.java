package collections_practice;


public class MultipleMethodRef implements MultipleMethodRefInterface {
    public static void main(String[] args) {
        // Referring to different static methods using method references
        MethodRef.Operation addition = MultipleMethodRef::add;
        MethodRef.Operation subtraction = MultipleMethodRef::subtract;
        MethodRef.Operation multiplication = MultipleMethodRef::multiply;

        System.out.println("Addition: " + addition.operate(10, 5));      // Outputs: 15
        System.out.println("Subtraction: " + subtraction.operate(10, 5)); // Outputs: 5
        System.out.println("Multiplication: " + multiplication.operate(10, 5)); // Outputs: 50
    }

    // Functional interface for method reference

    static int add(int a, int b) {
        return a + b;
    }

    static int subtract(int a, int b) {
        return a - b;
    }

    static int multiply(int a, int b) {
        return a * b;
    }
}
