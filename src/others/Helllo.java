package others;


public class Helllo{

     public static void main(String[] args) {
        Hi hi = new Hi();
//         System.out.println();
//        System.out.println(hi.getClass().getName());
//        Hi hi1 = new Hi();
//         System.out.println(hi1.Hi());
//         System.out.println(hi + "            /n" + hi1);
//
////         var var = 25;
////         System.out.println(var);
//         String multilineStr = "Line 1\nLine 2\rLine 3";
//         multilineStr.lines().forEach(System.out::println);
//// Outputs:
// Line 1
// Line 2
// Line 3
        String something = "hahah";

         switch (something){
             case "one" :
                 System.out.println("one ");
                 break;
             case  "two":
                 System.out.println("tow ");
                 break;
             default:
                 System.out.println("nothing thsiis default ");
                 break;
         }

     }

    static class  Hi {
        public String Hi() {
            System.out.println("Hello World");
            return "HI JP!!!";
        }

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
 class Main1 {
    public static void main(String[] args) {
        System.out.println("Memory Address of A.num in Main: " + System.identityHashCode(A.num));

        B obj = new B();
        obj.checkAddress(); // Checking from another class

    }


}
