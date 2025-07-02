package recursion;

public class FactorialofN {

    public static void main(String[] args) {
        int n = 6;
        int res = factorial(n);
        System.out.println(res);
//        int fibanacciNo = fibanacci(n);
//        System.out.println(fibanacciNo);
    }

    //    O(2^n)O(n)
    private static int fibanacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return fibanacci(n - 1) + fibanacci(n - 2);
        }
    }

    //O(n) O(n)
    private static int factorial(int n) {
        if (n == 1) {
            System.out.println("returnign 1 from factorial");
            return 1;
        }
        return n * factorial(n - 1);
    }


}
