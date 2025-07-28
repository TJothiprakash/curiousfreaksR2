package others.exceptions.propagation;

public class ExceptionChaining {
    public static void main(String[] args) {
        try {
            try {
                int x = 10 / 0; // 💥 Primary Exception
            } catch (ArithmeticException e) {
                throw new RuntimeException("Exception in outer block", e); // 🚀 Wrapping
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Caught: " + e.getMessage());
            System.out.println("Caused by: " + e.getCause()); // 🔥 Root cause
        }
    }
}
