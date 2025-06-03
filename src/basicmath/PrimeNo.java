package basicmath;

public class PrimeNo {
    public static void main(String[] args) {
        int n = 13;
        int ans = isPrime(n);
        System.out.println((ans == 1) ? true : false);
    }


    public static int isPrime(int n) {
        // Handle edge cases
        if (n <= 1) {
            return 0; // Not prime
        }

        if (n == 2) {
            return 1; // 2 is prime
        }

        if (n % 2 == 0) {
            return 0; // Even numbers greater than 2 are not prime
        }

        // Check for divisibility up to sqrt(n)
        int sqrtN = (int) Math.sqrt(n);
        for (int i = 3; i <= sqrtN; i += 2) {
            if (n % i == 0) {
                return 0; // n is divisible by i, so it's not prime
            }
        }

        return 1; // If no divisor is found, n is prime
    }


}
