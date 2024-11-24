package slidingwindow;

import java.util.List;

public class Main {
    public static void main(String[] args) {
      //  MaximumSubarraySum obj = new MaximumSubarraySum();
        int[] arr = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
      /*  int [] arr1={100, 200, 300, 400};
        SubarraySumwithKElements obj = new SubarraySumwithKElements();
        System.out.println(obj.subarraySum(arr1, 3));
*/

        //System.out.println(obj.maxSubArraySum(arr1));
        //System.out.println(obj.maxSubArraySum(arr));
       /* DistinctElementsinWindow    obj = new DistinctElementsinWindow();
        System.out.println(obj.findDistinctElements(arr, 4));
*//*
        NegativeinEveryWindow obj = new NegativeinEveryWindow();
        System.out.println(obj.findFirstNegativeInWindow(arr, 2));*/
        MaxValueinSubArray obj = new MaxValueinSubArray();
        List<Integer> ans = obj.findMaxValue(arr, 4);// [10, 10, 10, 15, 15, 90, 90]
        System.out.println(ans);

    }
}
