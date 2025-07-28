package others.exceptions.propagation;

public class Test1 {
    public static void main(String[] args) {
        B1 b = new B1();
//         b.methodB(); // ❌ Exception propagates to main() -> Program crashes!
//
        try {
            b.methodB();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("program finisehd");
    }
}
