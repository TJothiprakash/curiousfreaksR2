package slidingwindow;

import java.util.function.DoubleBinaryOperator;

public class Main {
    public static void main(String[] args) {
      //  MaximumSubarraySum obj = new MaximumSubarraySum();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int [] arr1={100, 200, 300, 400};
        SubarraySumwithKElements obj = new SubarraySumwithKElements();
        System.out.println(obj.subarraySum(arr1, 3));

        //System.out.println(obj.maxSubArraySum(arr1));
        //System.out.println(obj.maxSubArraySum(arr));
    }
}
