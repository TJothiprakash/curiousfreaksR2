package dynamic_programming.dp_onlongestincreasingsubsequence;

import java.util.*;

public class DivisibleSet {
    // Function to find the longest divisible subset
    static List<Integer> divisibleSet(List<Integer> arr) {
        int n = arr.size();

        // Sort the array
        Collections.sort(arr);

        List<Integer> dp = new ArrayList<>(Collections.nCopies(n, 1));
        List<Integer> hash = new ArrayList<>(Collections.nCopies(n, 0));

        for (int i = 0; i < n; i++) {
            hash.set(i, i); // Initializing with current index
            for (int prev_index = 0; prev_index <= i - 1; prev_index++) {
                if (arr.get(i) % arr.get(prev_index) == 0 && 1 + dp.get(prev_index) > dp.get(i)) {
                    dp.set(i, 1 + dp.get(prev_index));
                    hash.set(i, prev_index);
                }
            }
        }

        int ans = -1;
        int lastIndex = -1;

        for (int i = 0; i < n; i++) {
            if (dp.get(i) > ans) {
                ans = dp.get(i);
                lastIndex = i;
            }
        }

        List<Integer> temp = new ArrayList<>();
        temp.add(arr.get(lastIndex));

        while (hash.get(lastIndex) != lastIndex) {
            lastIndex = hash.get(lastIndex);
            temp.add(arr.get(lastIndex));
        }

        // Reverse the array
        Collections.reverse(temp);

        return temp;
    }

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(1, 16, 7, 8, 4);

        List<Integer> ans = divisibleSet(arr);

        System.out.print("The longest divisible subset elements are: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}


class LargestDivisibleSubsetRecursive {
    static int[] nums;
    static Map<String, List<Integer>> memo;

    public static List<Integer> largestDivisibleSubset(int[] input) {
        Arrays.sort(input); // Sort for easier divisibility
        nums = input;
        memo = new HashMap<>();

        List<Integer> bestSubset = new ArrayList<>();

        // Try starting from every index
        for (int i = 0; i < nums.length; i++) {
            List<Integer> subset = dfs(i, -1);
            if (subset.size() > bestSubset.size()) {
                bestSubset = subset;
            }
        }

        return bestSubset;
    }

    // Recursive function to build subset
    private static List<Integer> dfs(int curr, int prev) {
        String key = curr + "|" + prev;
        if (memo.containsKey(key)) return memo.get(key);

        List<Integer> include = new ArrayList<>();
        if (prev == -1 || nums[curr] % nums[prev] == 0) {
            include.add(nums[curr]);
            List<Integer> nextBest = new ArrayList<>();
            for (int next = curr + 1; next < nums.length; next++) {
                List<Integer> candidate = dfs(next, curr);
                if (candidate.size() > nextBest.size()) {
                    nextBest = candidate;
                }
            }
            include.addAll(nextBest);
        }

        // Try skipping current
        List<Integer> exclude = dfs(curr + 1, prev);

        List<Integer> result = include.size() > exclude.size() ? include : exclude;
        memo.put(key, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 3}));     // Output: [1, 2] or [1, 3]
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 4, 8})); // Output: [1, 2, 4, 8]
    }
}



/*
Question:
Given a set of distinct positive integers, return the largest subset where for every pair (a, b), a % b == 0 or b % a == 0

Intuition:
- Sort the array to simplify checking divisibility
- For each element, build the longest divisible subset ending at that element
- Reconstruct the subset using a prev[] array

Approach:
- Sort the array
- dp[i] = length of largest divisible subset ending at nums[i]
- prev[i] = index of previous element in the subset
- Track maxLen and ending index

Time: O(n^2)
Space: O(n)
*/


 class LargestDivisibleSubset {

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        Arrays.sort(nums);
        int[] dp = new int[n];       // Length of divisible subset ending at i
        int[] prev = new int[n];     // Previous index in the subset
        Arrays.fill(dp, 1);          // Every element can be a subset of length 1
        Arrays.fill(prev, -1);

        int maxLen = 1;
        int maxIndex = 0;

        // Build the dp and prev arrays
        for (int i = 1; i < n; i++) {
            System.out.println("Checking divisors for: " + nums[i]);
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                    System.out.println("  " + nums[j] + " divides " + nums[i] + " → dp[" + i + "] = " + dp[i]);
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct the subset
        List<Integer> result = new ArrayList<>();
        int curr = maxIndex;
        while (curr >= 0) {
            result.add(nums[curr]);
            curr = prev[curr];
        }

        Collections.reverse(result);
        return result;
    }

    // Driver
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 2, 4, 8};
        int[] nums3 = {4, 8, 10, 240};

        System.out.println("Result 1: " + largestDivisibleSubset(nums1)); // [1,2] or [1,3]
        System.out.println("Result 2: " + largestDivisibleSubset(nums2)); // [1,2,4,8]
        System.out.println("Result 3: " + largestDivisibleSubset(nums3)); // [4,8,240] or similar
    }
}

/*
Time Complexity:
- Sorting: O(n log n)
- DP loop: O(n^2)

Space Complexity:
- O(n) for dp and prev arrays
*/
/*
Question:
Given a set of distinct positive integers, return the largest subset such that for every pair (a, b), a % b == 0 or b % a == 0.

Intuition:
- Sort the array.
- At each index, decide whether to include it in the subset or skip it.
- Include it only if it divides or is divisible by the previous number.
- Use recursion + memoization to explore all valid paths and cache results.

Approach:
- Sort nums[]
- Recurse from index 0 with prev = -1 (no number yet)
- Memoize based on (index, prevIndex)

Time: O(n^2)
Space: O(n^2)
*/


 class LargestDivisibleSubsetRecursive1 {

    // Main function
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;

        Map<String, List<Integer>> memo = new HashMap<>();

        List<Integer> result = helper(0, -1, nums, memo);
        return result;
    }

    // Recursive helper with memoization
    private static List<Integer> helper(int index, int prevIndex, int[] nums, Map<String, List<Integer>> memo) {
        if (index == nums.length) return new ArrayList<>();

        String key = index + "|" + prevIndex;
        if (memo.containsKey(key)) return memo.get(key);

        // Exclude current element
        List<Integer> exclude = helper(index + 1, prevIndex, nums, memo);

        List<Integer> include = new ArrayList<>();
        if (prevIndex == -1 || nums[index] % nums[prevIndex] == 0) {
            include.add(nums[index]);
            include.addAll(helper(index + 1, index, nums, memo));
        }

        List<Integer> better = (include.size() > exclude.size()) ? include : exclude;
        memo.put(key, better);
        return better;
    }

    // Driver
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 2, 4, 8};
        int[] nums3 = {4, 8, 10, 240};

        System.out.println("Result 1: " + largestDivisibleSubset(nums1)); // [1,2] or [1,3]
        System.out.println("Result 2: " + largestDivisibleSubset(nums2)); // [1,2,4,8]
        System.out.println("Result 3: " + largestDivisibleSubset(nums3)); // [4,8,240]
    }
}

/*
Time Complexity:
- At most O(n^2) subproblems (index × prevIndex)
- Each subproblem builds a new list (not optimal for huge inputs, but okay ≤1000)

Space Complexity:
- O(n^2) for memo map
- O(n) stack space
*/
