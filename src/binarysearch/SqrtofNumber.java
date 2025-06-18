package binarysearch;


/*
* calculates teh squareroor of the given number
* @param n - given number
* */
public class SqrtofNumber {
    public static int floorSqrt(int n) {
        int low = 1, high = n;
        //Binary search on the answers:
        while (low <= high) {
            long mid = (low + high) / 2;
            long val = mid * mid;
            if (val <= (long) (n)) {
                //eliminate the left half:
                low = (int) (mid + 1);
            } else {
                //eliminate the right half:
                high = (int) (mid - 1);
            }
        }
        return high;
    }

    public static void main(String[] args) {
        int n = 28;
        int ans = floorSqrt(n);
        System.out.println("The floor of square root of " + n + " is: " + ans);
    }
}


