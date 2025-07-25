package dynamic_programming.july_24;

import java.util.Arrays;

public class PracticeII {
    public static void main(String[] args) {

    }

    public int LIS(int arr[]){
        int count =0;
        int memo[] = new int[arr.length+1];
        Arrays.fill(memo, -1);
        return countLIS(-1,count,arr,memo);
    }

    private int countLIS(int prev, int curr, int[] arr, int[] memo) {
        if(curr == arr.length) {
            return 1;
        }
        int take=0;
        if(memo[curr]!= -1)return memo[curr];
        if(prev == -1 || arr[curr] > arr[prev]){
         take = 1+ countLIS(curr,curr+1,arr, memo);
        }
        int nottake = countLIS(curr, curr+1, arr, memo);
        memo[curr]= nottake+take;
        return memo[curr];
    }
}
