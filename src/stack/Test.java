package stack;

public class Test {
    static void main() {
        String num = "14";
        System.out.println( num.charAt(0)-'0');
        System.out.println( num.charAt(0));
        Character ans = num.charAt(0);
        Integer ans1 = num.charAt(0)-'0';
        System.out.println(ans1.getClass());
        System.out.println(  'a'-'0');
        System.out.println("Test.main");
        System.out.println();
        System.out.println("ans1 = " + ans1);
        System.out.println();
        System.out.println((int ) 'z');
        System.out.println(ans.getClass());

    }
}
