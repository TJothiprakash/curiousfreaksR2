package practicesessions.jan_16;

public class Practice {
    public static void main(String[] args) {
        boolean result = findOddorEven(5);
        System.out.println("is 5 even ? "+result);
    }

    private static boolean findOddorEven(int i) {
        return i % 2 == 0 ? true : false;
    }
}

/*if (i % 2 == 0) { System.out.println("Even"); } else { System.out.println("Odd"); }	                Find even or odd

int lastDigit = i % 10; System.out.println("Last digit: " + lastDigit);                                	Find last digit in a number

int count = 0; while (i > 0) { i = i / 10; count++; } System.out.println("Count of digits: " + count);	Count digits in a number (Solving above last digit problem will make this easy for you)

int reversed = 0; while (i > 0) { int digit = i % 10; reversed = reversed * 10 + digit; i = i / 10; }
System.out.println("Reversed number: " + reversed);                                                     Reverse a number (Try thinking how you can use above logic in solving this)

int result = 1; for (int j = 0; j < b; j++) { result *= a; } System.out.println("Power: " + result);	Find power of a number

while (b != 0) { int temp = b; b = a % b; a = temp; } System.out.println("GCD: " + a);	                 GCD

for (int j = 1; j <= i; j++) { if (i % j == 0) { System.out.println(j); } }	                             Print all divisors of a number

boolean isPrime = true; if (i < 2) { isPrime = false; } else { for (int j = 2; j <= Math.sqrt(i); j++)
 { if (i % j == 0) { isPrime = false; break; } } } System.out.println("Is prime: " + isPrime);	            Prime number (Try solving by yourself)

int sum = 0, temp = i; int digits = String.valueOf(i).length(); while (temp > 0) { int digit = temp % 10;
sum += Math.pow(digit, digits); temp /= 10; } System.out.println("Is Armstrong: " + (sum == i));	        Armstrong number (Solving power of a number will make this easy for you)

int original = i, reversed = 0; while (i > 0) { int digit = i % 10; reversed = reversed * 10 + digit;
i = i / 10; } System.out.println("Is palindrome: " + (original == reversed));	                            Check palindrome of a number (Use the techniques you learnt so far solving above problems and solve this by yourself)

double sqrt = Math.sqrt(i); System.out.println("Square root: " + sqrt);	                                    Square root of a number (Try to first figure out algo to solve this)

int sum = 0; for (int j = 1; j < i; j++) { if (i % j == 0) { sum += j; } }
System.out.println("Is perfect number: " + (sum == i));                                                 	Perfect number*/


