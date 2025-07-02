package basicmath;

import java.util.Scanner;

public class MathBasics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example usage
        int num = 153;

        System.out.println("Even/Odd: " + isEven(num));
        System.out.println("Last Digit: " + getLastDigit(num));
        System.out.println("Count Digits: " + countDigits(num));
        System.out.println("Reverse: " + reverseNumber(num));
        System.out.println("Power (2^3): " + power(2, 3));
        System.out.println("GCD(24, 36): " + gcd(24, 36));
        System.out.print("Divisors of " + num + ": ");
        printDivisors(num);
        System.out.println("Is Prime: " + isPrime(num));
        System.out.println("Is Armstrong: " + isArmstrong(num));
        System.out.println("Is Palindrome: " + isPalindrome(num));
        System.out.println("Square Root (int) of 16: " + squareRoot(16));
        System.out.println("Is Perfect Number (28): " + isPerfect(28));

        sc.close();
    }

    // 1. Even or Odd
    //O(1) O(1)
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // 2. Last digit
//    O(1) O(1)
    public static int getLastDigit(int n) {
        return Math.abs(n) % 10;
    }

    // 3. Count digits
//    O(digitlength) O(1)
    public static int countDigits(int n) {
        if (n == 0) return 1;
        int count = 0;
        n = Math.abs(n);
        while (n > 0) {
            count++;
            n /= 10;
        }
        return count;
    }

    // 4. Reverse a number
//    O(digitlength) O(1)
    public static int reverseNumber(int n) {
        int rev = 0;
        while (n != 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }

    // 5. Power of a number (a^b)
//    O(b) O(1)
    public static int power(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
    }

    // 6. GCD using Euclidean Algorithm
//    maxO(a)/O(b) O(1)
    public static int gcd(int a, int b) {
        while (b != 0) {
            int rem = a % b;
            a = b;
            b = rem;
        }
        return a;
    }

    // 7. Print all divisors
//    O(sqlrt(n)  O(1)
    public static void printDivisors(int n) {
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
                if (i != n / i) System.out.print((n / i) + " ");
            }
        }
        System.out.println();
    }

    // 8. Prime check
//    O(n) O(1)
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }

    // 9. Armstrong number
//    O(digitlength) O(1)
    public static boolean isArmstrong(int n) {
        int digits = countDigits(n);
        int temp = n, sum = 0;
        while (temp != 0) {
            int digit = temp % 10;
            sum += power(digit, digits);
            temp /= 10;
        }
        return sum == n;
    }

    // 10. Palindrome number
//    similar already solved
    public static boolean isPalindrome(int n) {
        return n == reverseNumber(n);
    }

    // 11. Square root (integer part using binary search)
//    O(log 2 n) O(1)
    public static int squareRoot(int n) {
        if (n < 2) return n;
        int low = 1, high = n / 2, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((long) mid * mid == n)
                return mid;
            if ((long) mid * mid < n) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // 12. Perfect number
//    O(sqrt(n)) O(1)
    public static boolean isPerfect(int n) {
        if (n <= 1) return false;
        int sum = 1;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) sum += n / i;
            }
        }
        return sum == n;
    }
}
