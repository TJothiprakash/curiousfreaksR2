package dynamic_programming;

public class FibanacciSeries {
    public static void findFibanacci(int n) {

        int ans = fibanacciofN(n);
        System.out.println("Fibannaci number is " + ans);

    }

    private static int fibanacciofN(int n) {
        if (n <= 1) {
            return n;
        }

        return fibanacciofN(n - 1) + fibanacciofN(n - 2);
    }
}
