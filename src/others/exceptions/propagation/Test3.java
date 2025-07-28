package others.exceptions.propagation;
class A1 {
    void methodA() {
        int x = 10 / 0; // 💥 Exception occurs
    }
}

class B1 {
    void methodB() {
        A1 a = new A1();
        a.methodA(); // ❌ Exception propagates here
    }
}

class C {
    void methodC() {
        B1 b = new B1();
        b.methodB(); // ❌ Exception propagates here
    }
}

public class Test3 {
    public static void main(String[] args) {
        C c = new C();
        try {
            c.methodC(); // ✅ Catching the exception here
        } catch (ArithmeticException e) {
            System.out.println("Caught in main: " + e.getMessage());
        }
    }
}
