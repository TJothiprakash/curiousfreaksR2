package arrays;

import java.util.Arrays;

public class SieveOfEranthosis {
    public static void main(String[] args) {
        int N = 30;
        SieveOfEranthosis sieve = new SieveOfEranthosis();
        sieve.sieveOfEratosthenes(N);
    }
    // Function to print prime numbers up to N using Sieve of Eratosthenes
    public void sieveOfEratosthenes(int N) {
        // Step 1: Create a boolean array of size N+1 and initialize all values to true
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        // Step 2: Mark 0 and 1 as non-prime
        isPrime[0] = isPrime[1] = false;

        // Step 3: Implement the Sieve of Eratosthenes
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false; // Mark multiples of i as non-prime
                }
            }
        }

        // Step 4: Print all prime numbers up to N
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }
}
