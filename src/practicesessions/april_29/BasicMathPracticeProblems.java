package practicesessions.april_29;

import java.util.ArrayList;

import java.util.List;

import static basicmath.PrimeNo.isPrime;

public class BasicMathPracticeProblems {
    public static void main(String[] args) {
//        boolean result = OddEven(14);
//        System.out.println(result);
        // System.out.println(lastDigitinNum(8293921l));
        BasicMathPracticeProblems basicMathPracticeProblems = new BasicMathPracticeProblems();
//        System.out.println(basicMathPracticeProblems.countDigits(8291));
        String reversedNumber = String.valueOf(basicMathPracticeProblems.reverseNumber(1234));
//        System.out.println(reversedNumber);
//        System.out.println(basicMathPracticeProblems.powerofNumber(3));
//        System.out.println(basicMathPracticeProblems.GCD(6, 8));

        List<Integer> answer = basicMathPracticeProblems.divisorsofaNumber(34);
        List<Integer> primeList = basicMathPracticeProblems.printPrimeNoUptoGivenX(1232);
        for (int i : primeList) {
            System.out.print(i + " ");
        }

        System.out.println();
    }

    public static boolean OddEven(int x) {
        //       return (x % 2 ==0) ;
        return (x & 1) == 0;
    }

    public static int lastDigitinNum(long x) {
        int temp = (int) x % 10;

        return temp;
    }

    public int countDigits(int x) {
        int temp = 0;

        while (x > 0) {
            x = x / 10;
            temp++;
        }
        return temp;
    }

    public StringBuilder reverseNumber(int x) {
        int temp = 0;
        StringBuilder sb = new StringBuilder();


        while (x > 0) {
            temp = x % 10;
            x = x / 10;
            //temp++;
            sb.append(temp);
        }
        return sb;
    }

    long powerofNumber(int x) {

        // find the reversed no to the power raised of a given number
        int reversedX = Integer.valueOf(String.valueOf(reverseNumber(x)));
        System.out.println(reversedX + " this is reversed X");
        long ans = 1; // Start from 1
        while (reversedX > 0) {
            ans *= x;
            reversedX--;
        }

        return ans;

    }

    int GCD(int x, int y) {
        int ans = 0;
        // two numbers given find the greatest common divisor

        int smallest = Math.min(x, y);

        for (int i = 1; i <= smallest; i++) {
            if (x % i == 0 && y % i == 0) {
                ans = i;
            }

        }


        return ans;
    }

    int euclidsGCD(int x, int y) {

        if (y == 0) {
            return x;
        }
        return euclidsGCD(x, x % y);

    }


    List<Integer> divisorsofaNumber(int x) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);

        for (int i = 2; i < Math.sqrt(x); i++) {
            if (x % i == 0) {
                ans.add(i);
                if (i != x / i) { // Avoid adding square root twice
                    ans.add(x / i);
                }
            }
        }
        ans.add(x);
        return ans;
    }

    List<Integer> printPrimeNoUptoGivenX(int x) {
        List<Integer> primenos = new ArrayList<>();
        //        primenos.add(1);
        primenos.add(2);
        for (int i = 3; i <= x; i++) {
            int primeCheck = isPrime(i);
            if (primeCheck == 1) {
                primenos.add(i);
            }
        }


        return primenos;
    }

    boolean isPalindrome(int x) {
        return x == Integer.valueOf(String.valueOf(reverseNumber(x)));
    }

    int floorSQRT(int x) {
        int i = 1;
        while (i * i <= x) {
            i++;
        }
        return i - 1;
    }


    boolean perfectNumber(int x) {
        boolean ans = false;
        List<Integer> factors = divisorsofaNumber(x);
        factors.remove(x);
        int show = factors.stream().mapToInt(Integer::intValue).sum();


        return show == x;
    }
}
