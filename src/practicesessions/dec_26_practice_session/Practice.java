package practicesessions.dec_26_practice_session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Practice {

    // sliding window problem
    public int maxSum(int[] arr, int k) {
        int n = arr.length;
        int maxSum = Integer.MIN_VALUE, sum = 0;
        // Edge case: If k is greater than the array length
        if (k > n) {
            throw new IllegalArgumentException("Window size k cannot be greater than the array length");
        }
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        if (sum > maxSum) {
            maxSum = sum;
        }
        for (int i = k; i < n; i++) {
            sum += arr[i] - arr[i - k];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

    // longest substring at most k elements
  /*  public String longestSubstring(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, maxLen = 0;
        int n = s.length();

        for (int end = 0; end < n; end++) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);

            // Shrink the window if the number of distinct characters exceeds k
            while (map.size() > k) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                if (map.get(s.charAt(start)) == 0) {
                    map.remove(s.charAt(start));
                }
                start++;
            }

            // Update max length
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return s.substring(start, start + maxLen);
    }*/
    public String longestSubstring(String s, int k) {
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0, maxLen = 0, bestStart = 0;  // Track the best start index
        int n = s.length();

        for (int end = 0; end < n; end++) {
            map.put(s.charAt(end), map.getOrDefault(s.charAt(end), 0) + 1);

            // Shrink the window if the number of distinct characters exceeds k
            while (map.size() > k) {
                map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                if (map.get(s.charAt(start)) == 0) {
                    map.remove(s.charAt(start));
                }
                start++;
            }

            // Update max length and best start index
            if (end - start + 1 > maxLen) {
                maxLen = end - start + 1;
                bestStart = start;
            }
        }

        return s.substring(bestStart, bestStart + maxLen);
    }


    // minimum window substring
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Frequency map for string t
        HashMap<Character, Integer> targetFreqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetFreqMap.put(c, targetFreqMap.getOrDefault(c, 0) + 1);
        }

        // Sliding window frequency map for string s
        HashMap<Character, Integer> windowFreqMap = new HashMap<>();

        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int minStart = 0; // To track the start index of the minimum window
        int formed = 0;   // Track how many characters in the window match the target frequency map

        // Iterate over the string with the end pointer
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            windowFreqMap.put(c, windowFreqMap.getOrDefault(c, 0) + 1);

            // Check if current character in window matches the required frequency in target map
            if (windowFreqMap.get(c).equals(targetFreqMap.get(c))) {
                formed++;
            }

            // When all characters from t are present in the window, try to shrink the window
            while (formed == targetFreqMap.size()) {
                int windowLength = end - start + 1;

                // Update minimum length window if current window is smaller
                if (windowLength < minLen) {
                    minLen = windowLength;
                    minStart = start;
                }

                // Try to shrink the window by moving the start pointer
                char startChar = s.charAt(start);
                windowFreqMap.put(startChar, windowFreqMap.get(startChar) - 1);
                if (windowFreqMap.get(startChar) < targetFreqMap.getOrDefault(startChar, 0)) {
                    formed--;
                }
                start++;
            }
        }

        // Return the result if a valid window was found, otherwise return empty string
        if (minLen == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(minStart, minStart + minLen);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // Traverse from left to right along the top row
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++; // Move the top boundary down

            // Traverse from top to bottom along the right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Move the right boundary left

            if (top <= bottom) {
                // Traverse from right to left along the bottom row
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--; // Move the bottom boundary up
            }

            if (left <= right) {
                // Traverse from bottom to top along the left column
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Move the left boundary right
            }
        }

        return result;
    }

    public void reArrangeArray(int arr[]) {
        int left = 0, right = arr.length - 1;
        int result[] = new int[arr.length]; // To store rearranged elements
        int index = 0;

        // Traverse and fill the result array with largest and smallest alternately
        while (left <= right) {
            if (index % 2 == 0) {
                result[index] = arr[right];
                right--;
            } else {
                result[index] = arr[left];
                left++;
            }
            index++;
        }

        // Optionally, copy the rearranged array back into the original array
        System.arraycopy(result, 0, arr, 0, arr.length);
    }

    public void reArrangeArrayInplaceModification(int arr[]) {
        int n = arr.length;

    }

    public void countInversions(int arr[]) {

    }
}
