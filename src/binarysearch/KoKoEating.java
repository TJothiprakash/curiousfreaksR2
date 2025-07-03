/*
Problem:
Koko loves to eat bananas. She has `n` piles of bananas, where the `i-th` pile has `v[i]` bananas.
Each hour, she chooses a pile and eats up to `k` bananas. If the pile has less than `k`, she eats all.

Given `h` hours to eat all bananas, find the **minimum integer speed `k`** such that she can finish all the bananas within `h` hours.

Intuition:
- The smaller the eating speed `k`, the more time Koko takes.
- We can binary search on `k` in the range [1, max(v[i])]
- For each `k`, we calculate the total hours needed. If it's within `h`, we try a smaller `k`.

Dry Run:
v = [7, 15, 6, 3], h = 8
max pile = 15, so search in [1, 15]
Try k = 8 → hours = ceil(7/8) + ceil(15/8) + ceil(6/8) + ceil(3/8) = 1 + 2 + 1 + 1 = 5 ≤ 8 → try smaller
Try k = 4 → 2+4+2+1 = 9 > 8 → try bigger

Code:
*/

package binarysearch;

public class KoKoEating {

    public static void main(String[] args) {
        int[] v = {7, 15, 6, 3};
        int h = 8;
        int ans = minimumRateToEatBananas(v, h);
        System.out.println("✅ Koko should eat at least " + ans + " bananas/hr.");
    }

    // O(n) time, O(1) space
    public static int findMax(int[] v) {
        int maxi = Integer.MIN_VALUE;
        for (int value : v) {
            maxi = Math.max(maxi, value);
        }
        return maxi;
    }

    // O(n) time, O(1) space
    public static int calculateTotalHours(int[] v, int hourly) {
        int totalH = 0;
        for (int pile : v) {
            totalH += Math.ceil((double) pile / hourly);
        }
        return totalH;
    }

    // O(n * log(max(v))) time, O(1) space
    public static int minimumRateToEatBananas(int[] v, int h) {
        int low = 1, high = findMax(v);
        int answer = high;

        System.out.println("🍌 Binary search for eating speed between 1 and " + high);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int totalH = calculateTotalHours(v, mid);
            System.out.println("➡️ Trying speed = " + mid + " → Total hours needed = " + totalH);

            if (totalH <= h) {
                answer = mid;
                System.out.println("✅ Feasible. Try smaller → New range: [" + low + ", " + (mid - 1) + "]");
                high = mid - 1;
            } else {
                System.out.println("❌ Too slow. Try faster → New range: [" + (mid + 1) + ", " + high + "]");
                low = mid + 1;
            }
        }

        return answer;
    }

    /*
    Time Complexity:
        - O(n * log M), where M = max element in the array
        - For each possible speed, we check total hours in O(n)

    Space Complexity:
        - O(1), only variables used
    */
}
