package arrays.practice;

/*Find even or odd if (n % 2 == 0) {
    System.out.println("Even");
} else {
    System.out.println("Odd");
}
Find last digit in a number
int lastdigit = n % 10;
Count digits in a number(Solving above last digit prob wil make this easy for you)
while(n >0){
    n = n/10;
    count++;
    }
Reverse a number(Try thinking how you can use above logic in solving this)
StringBuilder sb= new sb()
while(n >0){
    n = n/10;
    sb.append(n+"");
    count++;
    }
    return sb;
Find power of a number
num=num*num


GCD :     while (b != 0) {
            int temp = b;
            b = a % b; // Remainder
            a = temp; // Update a to the previous b
        }
        return a;



Print all divisors of a number
for (int i = 1; i <= Math.sqrt(n); i++) {
    if (n % i == 0) {
        System.out.println(i);
        if (i != n / i) {
            System.out.println(n / i);
        }
    }
}

Prime number(Try solving by yourself)
for (int i = 1; i <= Math.sqrt(n); i++) {
    if (n % i == 0) {
        System.out.println(i);
        if (i != n / i) {
            System.out.println(n / i);
        }
    }
}
Armstrong number(Solving power of number, will make this easy for you)
Check palindrome of number(Use the techniques you learnt so far solving above probs and solve this by yourself)

Square root of a number(Try to first figure out algo to solve this)
Perfect number
*/
public class ArrayPractice {
    public static void main(String[] args) {

    }
//    O(n) O(1)
    public static boolean isPalindrome(int num) {
        int originalNum = num;
        int reversedNum = 0;

        // Reverse the number
        while (num > 0) {
            int digit = num % 10; // Get the last digit
            reversedNum = reversedNum * 10 + digit; // Build the reversed number
            num /= 10; // Remove the last digit
        }

        // Check if the original number is equal to the reversed number
        return originalNum == reversedNum;
    }

}
