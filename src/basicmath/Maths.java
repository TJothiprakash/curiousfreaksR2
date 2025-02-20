package basicmath;

public class Maths {
    public static void main(String[] args) {
        long num = 17l;
        long num1 = 38l;
       // int res = countNofDigits(num1);
        //System.out.println(res);
        long reversed = reversed(num1);
        long reverseofpower = reversedExponent(num);
       // System.out.println("reversed exponent of " + num + " is " + reverseofpower);
        // System.out.println(reversed);

        int a =2;int b=32;
        int gcd=gcd(a,b);
        //System.out.println(gcd);
        System.out.println(1%1);
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            //System.out.println(a%b);
            return gcd(b, a % b);
        }

    }/*
    public static int gcd(int a, int b) {
        // Using Euclid's algorithm
        while (b != 0) {
            int temp = b;
            b = a % b; // Remainder
            a = temp; // Update a to the previous b
        }
        return a; // When b becomes 0, a is the GCD
    }*/


    private static long reversedExponent(long num) {
        long reverse = reversed(num);
       /* System.out.println("reversed number is " + reverse);
        System.out.print(reverse + " times of " + num + "  ie) ");
       */ long ans = 1;
        for (int i = 0; i < reverse; i++) {
            ans = ans * num;
        }
        return ans;
    }

    private static long reversed(long num) {
        String str = String.valueOf(num);
        StringBuilder sb = new StringBuilder();

        // Reverse the digits properly
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }

        // Convert back to long to handle larger numbers
        return Long.parseLong(sb.toString());
    }

    private static int countNofDigits(long num) {


        int count = 0;
        while (num > 0) {
            num = num / 10;
            count++;
            int temp = (int) (num % 10);

        }


        return count;
    }

    {/*/"lets add something"}//*/}
}
