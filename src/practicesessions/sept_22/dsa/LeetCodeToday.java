package practicesessions.sept_22.dsa;

public class LeetCodeToday {

    public static void main(String[] args) {
        int arr[] = new int[]{1, 1, 2, 2, 3};
        LeetCodeToday leetCodeToday = new LeetCodeToday();
        leetCodeToday.maxFrequencyElements(arr);

    }

    public int maxFrequencyElements(int[] nums) {
        // Find max element to size hash array safely
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[] hash = new int[maxVal + 1];
        int maxFreq = 0;

        // Count frequencies
        for (int i = 0; i < nums.length ; i++ ) {
            hash[nums[i]]++;
            maxFreq = Math.max(maxFreq, hash[nums[i]]);
        }

        // Sum contributions of elements with max frequency
        int count = 0;
        for (int freq : hash) {
            if (freq == maxFreq) {
                count += freq;
            }
        }
        return count;
    }

}
