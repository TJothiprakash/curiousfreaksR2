package arrays.nov_06_revision;
/*Find even or odd -done
Find last digit in a number -done
Count digits in a number(Solving above last digit prob wil make this easy for you)--done use while loop num = num/10

Reverse a number(Try thinking how you can use above logic in solving this)
use while loop num = num/10 and append to the string num%10

Find power of a number
use num=num*num and num=num%10

GCD
num % i

Print all divisors of a number
iterate up to sqrt(n) and print divisors

Prime number(Try solving by yourself)
iterate upto squart of number and check for factors

Armstrong number(Solving power of number, will make this easy for you)
use while loop to extract the digits and make cube of that numsber and sum it up


Check palindrome of number(Use the techniques you learnt so far solving above probs and solve this by yourself)
use while loop num = num/10 and append to the string num%10

Square root of a number(Try to first figure out algo to solve this)
use binary search to solve this

Perfect number
calculate divisors sum and check with the number
*/

public class Practice {
    public static int gcd(int a, int b) {
        // Using Euclid's algorithm
        while (b != 0) {
            int temp = b;
            b = a % b; // Remainder
            a = temp; // Update a to the previous b
        }
        return a; // When b becomes 0, a is the GCD
    }

    // normal method
    public static int gcd1(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd1(b, a % b);
        }
    }

    public static int sqrtOfNumber(int num) {

        int start = 1;
        int end = num;
        int ans = 0;
        while (start <= end) {
            int mid = (start + end) / 2;

            if (mid == (num / mid)) {
                ans = mid;
                return mid;
            } else if (mid * mid > num) {

                end = mid - 1;

            } else {
                ans = mid;
                start = mid + 1;
            }


        }
        return ans;
    }
}
