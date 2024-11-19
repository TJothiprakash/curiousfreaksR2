package recursion;

public class PowerofN {
    public static void main(String[] args) {
        int n = 2, x=4;
        System.out.println(calculatePower(n));
    }
    private static int calculatePower(int n) {
        // Reverse the number
        int reverse = reverseNumber(n);

        // Calculate n raised to the power of its reverse
        // Assuming result fits within a 32-bit signed integer
        return (int) Math.pow(n, reverse);
    }

    private static int reverseNumber(int n) {
        int reverse = 0;
        while (n > 0) {
            reverse = reverse * 10 + (n % 10);
            n /= 10;
        }
        return reverse;
    }

}
