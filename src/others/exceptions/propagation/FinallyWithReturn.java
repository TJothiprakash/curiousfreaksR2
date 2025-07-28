package others.exceptions.propagation;
public class FinallyWithReturn {
    public static int test() {
        try {
            return 10; // ✅ But first, `finally` executes!
        } finally {
            System.out.println("Finally block executed!");
        }
    }

    public static void main(String[] args) {
        System.out.println(test()); // ✅ Output: Finally block executed! 10
    }
}
