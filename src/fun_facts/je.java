package fun_facts;

import java.io.IOException;

public class je {
}
 class Test3 {
    public static void main(String[] args) {
        try {
            throw new NullPointerException("Oops!");
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception caught");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("End of program");
    }
}
class Parent {
    void show() throws IOException {  // Checked Exception
        System.out.println("Parent");
    }
}

class Child extends Parent {
    @Override
    void show() {   // No "throws IOException" here
        System.out.println("Child");
    }

    public static void main(String[] args) throws IOException {
        Parent p = new Child();
        p.show();
    }
}


 class Test6 {
    public static void main(String[] args) {
        try {
            int arr[] = new int[5];
            arr[10] = 50;  // ❌ Out of bounds
        } catch (ArrayIndexOutOfBoundsException e) {  // ❌ General comes before specific
            System.out.println("Array index error!");
        } catch (Exception e) {
            System.out.println("Exception caught!");
        }
    }
}


 class Test8 {
    public static void main(String[] args) {
        try {
            throw new RuntimeException("Something went wrong!");
        } finally {
            System.out.println("Finally block executed!");
        }
    }
}


 class Test9 {
    public static void main(String[] args) {
        try {
            System.out.println("Try block executed!");
            System.exit(0);  // ❌ Exiting program
        } finally {
            System.out.println("Finally block executed!");
        }
    }
}

 class Test11 {
    public static void main(String[] args) {
        try {
            System.out.println("Try block executed!");
            throw new RuntimeException("Exception in try!");
        } finally {
            System.out.println("Finally block executed!");
            throw new RuntimeException("Exception in finally!");  // 💥
        }
    }
}


 class Test10 {
    public static void main(String[] args) {
        try {
            System.out.println("Try block executed!");
            throw new RuntimeException("Exception in try!");
        } catch (Exception e) {
            System.out.println("Catch block executed!");
            throw new RuntimeException("Exception in catch!");  // 💥
        } finally {
            System.out.println("Finally block executed!");
        }
    }
}
