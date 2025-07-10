package arrays.practice.dec_24_revision;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Practice {

    // Recursive function to print all possible subarrays
    // for given array
//    O(2^n ) O( n)
    static void printSubArrays(int[] arr, int start, int end) {
        // Stop if we have reached the end of the array
        if (end == arr.length)
            return;
            // Increment the end point and start from 0
        else if (start > end)
            printSubArrays(arr, 0, end + 1);
            // Print the subarray and increment the starting
            // point
        else {
            System.out.print("[");
            for (int i = start; i < end; i++)
                System.out.print(arr[i] + ", ");
            System.out.println(arr[end] + "]");
            printSubArrays(arr, start + 1, end);
        }
        return;
    }

    public static void main(String[] args) {
        int arr[]= {1,2,3,4,5};
        printSubArrays(arr,0, 0);


    }
    // This code is contributed by Sania Kumari Gupta
//    O( n * n *k) O(n)
    public static @NotNull List<List<Integer>> subArray(int @NotNull [] arr) {
        //1.. using loops

        List<List<Integer>> listofSubarrays = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                ArrayList<Integer> subarray = new ArrayList<>();
                for (int k = i; k <= j; k++) {
                    System.out.print(k + " ");
                    subarray.add(arr[k]);
                }
                listofSubarrays.add(subarray);
                System.out.println();
            }

        }
        System.out.println(listofSubarrays);
        System.out.println(listofSubarrays.size());
        return listofSubarrays;
    }
    //same above
    public static void printSubarrays(int @NotNull [] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(numbers[k] + " ");
                }
                System.out.println();
            }
            // System.out.println();
        }
    }
    //O(2n) O(n) - using set
    public int longestConsequitiveSequence(int @NotNull [] arr) {
        int longest = 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        for (int it : set) {
            // if 'it' is a s
            if (!set.contains(it - 1)) {
                // find consecutive numbers
                int cnt = 1;
                int x = it;
                while (set.contains(x + 1)) {
                    x = x + 1;
                    cnt = cnt + 1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }
    //O(2n) O(n) using collection
    public List<Integer> leadersinanArray(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int rightmax = arr[arr.length - 1];
        leaders.add(rightmax);  // First element is always a leader

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > rightmax) {
                rightmax = arr[i];
                leaders.add(rightmax);  // Add to list if a new leader is found
            }
        }

        Collections.reverse(leaders);  // Reverse the list as we have traversed backwards
        return leaders;
    }

    public List<List<Integer>> recurPermute(int[] arr) {
        List<List<Integer>> listOfPerMutations = new ArrayList<>();
        helperForPermutations(arr, 0, listOfPerMutations);
        return listOfPerMutations;
    }
    //O(2^n) O(n)
    private void helperForPermutations(int[] arr, int i, List<List<Integer>> listOfPerMutations) {
        if (i == arr.length) {
            List<Integer> ds = new ArrayList<>();
            for (int j = 0; j < arr.length; j++) {
                ds.add(arr[j]);
            }
            listOfPerMutations.add(ds);
            return;
        }
        for (int j = i; j < arr.length; j++) {
            swapElements(arr, i, j);
            helperForPermutations(arr, i + 1, listOfPerMutations);
            swapElements(arr, i, j);
        }


    }

    public List<Integer> getNextPermutation
            (List<List<Integer>> permutations, List<Integer> currentPermutation) {
        // Find the index of the current permutation in the list
        int index = permutations.indexOf(currentPermutation);

        // If the current permutation is the last one, return an empty list (no next permutation)
        if (index == -1 || index == permutations.size() - 1) {
            return new ArrayList<>();  // No next permutation
        }

        // Otherwise, return the next permutation
        return permutations.get(index + 1);
    }

    private void swapElements(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // Java code to print all possible subarrays for given array

    //maxprofit O(n) O(1)
    public int maxProfit(int[] arr) {
        int maxp = Integer.MIN_VALUE;
        int minprice = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            minprice = Math.min(minprice, arr[i]);
            maxp = Math.max(maxp, arr[i] - minprice);
        }
        return maxp;
    }

    //    O(n) O(1)
    public int maxSubArraySum(int[] arr) {
        int sum = 0, maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = Math.max(arr[i], sum + arr[i]);
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
    //O(n) O(1)
    public boolean sumofTwo(int[] arr, int target) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + arr[i + 1] == target) {
                return true;
            }

        }
        return false;
    }

// This code is contributed by Sania Kumari Gupta

    //    O(n * k ) O( k+n)
    public List<List<Integer>> generateSubarraysUsingSlidingWindow(int[] arr) {
        List<List<Integer>> listofSubarrays = new ArrayList<>();

        // Iterate over all possible window sizes
        for (int windowSize = 1; windowSize <= arr.length; windowSize++) {
            // Slide the window over the array
            for (int start = 0; start <= arr.length - windowSize; start++) {
                List<Integer> subarray = new ArrayList<>();

                // Collect elements in the current window
                for (int i = start; i < start + windowSize; i++) {
                    subarray.add(arr[i]);
                }

                // Add the subarray to the list
                listofSubarrays.add(subarray);
            }
        }
        System.out.println(listofSubarrays);
        return listofSubarrays;
    }

    public void collectSubArrays(int[] arr, int start, int end, List<List<Integer>> listofSubarrays) {
        // Stop if we have reached the end of the array
        if (end == arr.length)
            return;

            // Increment the end point and start from 0
        else if (start > end)
            collectSubArrays(arr, 0, end + 1, listofSubarrays);

            // Add the subarray to the list and increment the starting point
        else {
            // Create a new subarray
            List<Integer> subArray = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                subArray.add(arr[i]);
            }
            // Add the subarray to the list
            listofSubarrays.add(subArray);

            // Recur for the next starting index
            collectSubArrays(arr, start + 1, end, listofSubarrays);
        }
    }

    public List<List<Integer>> subArraysUsingRecursion(int[] arr) {
        List<List<Integer>> listofSubarrays = new ArrayList<>();
        helperForSubarrays(arr, 0, listofSubarrays, new ArrayList<>());
        System.out.println(listofSubarrays);
        System.out.println(listofSubarrays.size());
        return listofSubarrays;
    }
    //O(2^n) O(n)
    private void helperForSubarrays(int[] arr, int start, List<List<Integer>>
            listofSubarrays, ArrayList<Integer> subArray) {
        // Base Case: If we've considered all elements, stop recursion
        if (start == arr.length) {
            return;
        }

        // Explore all subarrays starting from the current 'start' index
        for (int i = start; i < arr.length; i++) {
            subArray.add(arr[i]);  // Add current element to the subarray
            listofSubarrays.add(new ArrayList<>(subArray));  // Add the subarray to the result

            // Recurse to explore longer subarrays
            helperForSubarrays(arr, i + 1, listofSubarrays, subArray);

            // Backtrack: Remove the last element to explore other subarrays
            subArray.remove(subArray.size() - 1);
        }

        // Recurse for the next starting index
        helperForSubarrays(arr, start + 1, listofSubarrays, new ArrayList<>());
    }
}