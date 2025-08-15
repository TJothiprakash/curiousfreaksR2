package practicesessions.aug_13;

import java.util.Arrays;

public class PrefixArray {
    static void main() {
        new PrefixArray().prefixArray(new int[]{1,2,3,4});
    }
    public void prefixArray(int arr[]) {
        int[] prefix = new int[arr.length + 1];
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i-1];
        }
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---------------");

        System.out.println("-----------------");
        for (int i : prefix) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("---------------");
        System.out.println("-----------------");

    }
}


class Solution4 {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int[]prefix = new int [nums.length+1];

        for(int i =1; i< prefix.length;i++){
            prefix[i]= prefix[i-1]+nums[i-1];
        }


        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            // Binary search to find largest k with prefix[k] <= q
            int idx = Arrays.binarySearch(prefix, q + 1);
            if (idx < 0) idx = -idx - 1; // insertion point
            answer[i] = idx - 1;
        }

        return answer;
    }
}