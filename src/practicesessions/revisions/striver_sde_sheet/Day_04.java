package practicesessions.revisions.striver_sde_sheet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day_04 {
    //TODO
    // 2 sum problem
    //approach can do using map.., two pointer

    //TODO
    // length of longest subarray with sum  equals 0

    public static void main(String[] args) {
        int arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
        System.out.println(longestSubarrayWithSumZero(arr));
    }

    private static int longestSubarrayWithSumZero(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; ++i) {
            int sum = 0;
            for (int j = i; j < arr.length; ++j) {
                sum += arr[j];
                if (sum == 0) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    //Count the number of subarrays with given xor K
    //TODO
    //Approach
    public static int subarraysWithXorK(int[] a, int k) {
        int n = a.length; //size of the given array.
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>(); //declaring the map.
        mpp.put(xr, 1); //setting the value of 0.
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            // prefix XOR till index i:
            xr = xr ^ a[i];

            //By formula: x = xr^k:
            int x = xr ^ k;

            // add the occurrence of xr^k
            // to the count:
            if (mpp.containsKey(x)) {
                cnt += mpp.get(x);
            }

            // Insert the prefix xor till index i
            // into the map:
            if (mpp.containsKey(xr)) {
                mpp.put(xr, mpp.get(xr) + 1);
            } else {
                mpp.put(xr, 1);
            }
        }
        return cnt;
    }


    //TODO

    /// length of longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static int lengthofLongestSubstringOptimalApproach(String str) {

        if (str.length() == 0)
            return 0;
        int maxans = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) // outer loop for traversing the string
        {
            Set<Character> se = new HashSet<>();
            for (int j = i; j < str.length(); j++) // nested loop for getting different
            // string starting with str[i]
            {
                if (se.contains(str.charAt(j))) // if element if found so mark it as ans
                // and break from the loop
                {
                    maxans = Math.max(maxans, j - i);
                    break;
                }
                se.add(str.charAt(j));
            }
        }
        return maxans;
    }


}
