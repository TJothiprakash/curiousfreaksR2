package fun_facts;


public class Helllo{

    static class  Hi {
        public Hi() {
            System.out.println("Hello World");
        }
    }
     public static void main(String[] args) {
        Hi hi = new Hi();
        System.out.println(hi.getClass().getName());
        Hi hi1 = new Hi();
        // System.out.println(hi + "            /n"+hi1);
    }
}

class A {
    static int num = 100;
}

class B {
    void checkAddress() {
        System.out.println("Memory Address of A.num in Class B: " + System.identityHashCode(A.num));
    }
}
 class Main {
    public static void main(String[] args) {
        System.out.println("Memory Address of A.num in Main: " + System.identityHashCode(A.num));

        B obj = new B();
        obj.checkAddress(); // Checking from another class
    }
}
