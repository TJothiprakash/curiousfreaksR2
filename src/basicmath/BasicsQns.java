package basicmath;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BasicsQns {
    public static void main(String[] args) {

        BasicsQns obj = new BasicsQns();
//        System.out.println(obj.findOddEven(10));
////        System.out.println(obj.findOddEven(11));
//        System.out.println(obj.findOddEven1(12));
//        System.out.println(obj.findOddEven1(13));
//        System.out.println(obj.findLastdigit(123));
//        System.out.println(obj.countDigits(123456789));
//        System.out.println(obj.countDigits(10));
//        System.out.println(obj.countDigits(123));
//        System.out.println(obj.reverseNumber(123456789));
//        System.out.println(obj.findPowerofGivenReverse(3));
//        System.out.println(obj.gcd(12, 18));
        System.out.println(obj.printAllDivisors(12));
    }

    boolean isPerfectNumber(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }

    boolean checkPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    boolean isAmstrong(int n) {
        int sum = 0;
        int temp = n;
        while (temp > 0) {
            int rem = temp % 10;
            sum += rem * rem * rem;
            temp /= 10;
        }
        return sum == n;
    }


    List<Integer> printAllDivisors(int n) {
        List<Integer> li = new java.util.ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                li.add(i);
            }
        }

        return li;
    }

    List<Integer> printAllDivisors1(int n) {
        Set<Integer> divisors = new TreeSet<>(); // TreeSet to keep elements sorted
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                divisors.add(i);
                divisors.add(n / i);
            }
        }
        return new ArrayList<>(divisors);
    }


    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            //System.out.println(a%b);
            return gcd(b, a % b);
        }
    }


    long findPowerofGivenReverse(int n) {
        int reversed = reverseNumber(n);
        int result = reversed;
        for (int i = 1; i < reversed; i++) {
            result = result * reversed;
        }

        return (long) result;
    }


    int reverseNumber(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 10);
            n = n / 10;
        }
        return Integer.parseInt(sb.toString());
    }


    boolean findOddEven(int n) {
        return (n & 1) == 0;

    }

    boolean findOddEven1(int n) {
        return n % 2 == 0;
    }

    int findLastdigit(int n) {
        return n % 10;
    }

    int countDigits(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 10;
            count++;
        }
        return count;
    }


}
