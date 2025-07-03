package binarysearch.binarysearchqns;

public class PainterPartition {

    public static void main(String[] args) {
        int[] arr = {5, 10, 30, 20, 15};
        int k = 3;
        System.out.println("Minimum time: " + minTime(arr, k));  // Expected: 35
    }

    //O( n log (sum)) O( 1)
    public static int minTime(int[] arr, int k) {
        int n = arr.length;
        int max = 0;
        int sum = 0;

        for (int num : arr) {
            max = Math.max(max, num);
            sum += num;
        }

        int low = max;
        int high = sum;
        int answer = sum;

        System.out.println("Initial search range: [" + low + ", " + high + "]");

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("Trying mid = " + mid);

            if (isPossible(arr, k, mid)) {
                answer = mid;
                System.out.println("Possible with maxTime = " + mid + ", trying smaller...");
                high = mid - 1;
            } else {
                System.out.println("Not possible with maxTime = " + mid + ", trying larger...");
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean isPossible(int[] arr, int k, int maxTime) {
        int painterCount = 1;
        int timeSum = 0;

        System.out.println("  Checking feasibility for maxTime = " + maxTime);
        for (int i = 0; i < arr.length; i++) {
            int pages = arr[i];

            if (pages > maxTime) {
                System.out.println("    Board " + i + " too long (" + pages + "), exceeds maxTime.");
                return false;
            }

            if (timeSum + pages <= maxTime) {
                timeSum += pages;
                System.out.println("    Adding board " + i + " (" + pages + "), total = " + timeSum);
            } else {
                painterCount++;
                timeSum = pages;
                System.out.println("    Assigning new painter #" + painterCount + " starting at board " + i + " (" + pages + ")");
                if (painterCount > k) {
                    System.out.println("    Too many painters needed, not possible.");
                    return false;
                }
            }
        }

        return true;
    }
}
