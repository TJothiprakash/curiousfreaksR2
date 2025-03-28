package fun_facts.exceptions.propagation;

public class A {
    void methodA() {
        int x = 10 / 0; // 💥 Exception occurs here (ArithmeticException)
    }
}

class B {
    void methodB() {
        A1 a = new A1();
        a.methodA(); // ❌ No handling here, so it propagates to methodB()
    }
}

