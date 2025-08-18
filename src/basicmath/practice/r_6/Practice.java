package basicmath.practice.r_6;

import java.util.*;

/*Find even or odd
Find last digit in a number
Count digits in a number(Solving above last digit prob wil make this easy for you)
Reverse a number(Try thinking how you can use above logic in solving this)
Find power of a number
GCD
Print all divisors of a number
Prime number(Try solving by yourself)
Armstrong number(Solving power of number, will make this easy for you)
Check palindrome of number(Use the techniques you learnt so far solving above probs and solve this by yourself)
Square root of a number(Try to first figure out algo to solve this)
Perfect number
*/
public class Practice {

    boolean isPerfect(int n) {
        Set<Integer> set = Practice.printAllDivisors(n);
        System.out.println(set);
        int sum = new ArrayList<>(set).stream().mapToInt(x -> x).sum();
        return sum == n;
    }


    int findSqrtofN(int n) {
        if (n < 2) return n; // 0 or 1 case

        int left = 1, right = n, floor = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid <= n / mid) { // avoid overflow instead of mid * mid <= n
                floor = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return floor;
    }

    boolean isPalindrome(int n) {
        int reversed = Practice.reverseaNUm(n);
        return reversed == n;
    }


    boolean isAmstrong(int n) {
        int original = n;
        int temp = 0;
        while (n > 0) {
            int t = n % 10;
            temp += Math.pow(t, 3);
            n /= 10;
        }
        return original == temp;

    }

    static void main() {
        System.out.println();
        Practice practice = new Practice();
//        System.out.println(practice.isOdd(3));
//        System.out.println(practice.lastDigit(17));
//        System.out.println(practice.lastDigit(179));
//        System.out.println(practice.countDigits(1797343984));
//        System.out.println(practice.countDigits(179));
//        System.out.println(practice.reverseaNUm(179));
//        System.out.println(practice.powerOfN(3, 4));
//        System.out.println(practice.gcd(3, 27));
//        System.out.println(practice.gcd(4, 27));
//        System.out.println(practice.printAllDivisors(32));
        System.out.println(practice.isPerfect(10));
        System.out.println(practice.isPerfect(6));
//        System.out.println(practice.isPrime(81));
//        System.out.println(practice.isPrime(89));
    }

    boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;

        if (n % 2 == 0 || n % 3 == 0) return false;

        for (int i = 5; i <= Math.sqrt(n); i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) { // check 6k ± 1 pattern
                return false;
            }
        }
        return true;
    }


    public static HashSet<Integer> printAllDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int sqrt = (int) Math.sqrt(n);

        for (int i = 1; i <= sqrt; i++) {
            if (n % i == 0) {
//                divisors.add(i);
                set.add(i);
                set.add(n / i);
//                if (i != n / i) { // avoid duplicate for perfect squares
//                    divisors.add(n / i);
//                }
            }
        }
        set.remove(n);
        Collections.sort(divisors); // Optional: To keep divisors in ascending order
//        return divisors;
        return set;
    }


    int gcd(int a, int b) {
        if (a % b == 0)
            return b;

        return gcd(b, a % b);
    }


    long powerOfN(int n, int p) {
        int t = 1;
        for (int i = 0; i < p; i++) {
            t = t * n;
        }
        return (long) t;
    }

    boolean isOdd(long n) {
        return (n & 1) == 1;
    }

    int lastDigit(long n) {
        return (int) n % 10;
    }

    int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            n /= 10;
            count++;
        }
        return count;
    }

    static int reverseaNUm(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int t = n % 10;
            n /= 10;
            sb.append(t);
        }
        return Integer.parseInt(sb.toString());
    }

}
