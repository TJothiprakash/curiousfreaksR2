package practicesessions.sept_8;

// Java program for finding k closest points
// to the origin using sorting.
import java.util.*;

public class KClosesetPoints {

    // Function to calculate squared distance from the origin
    static int squaredDis(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // Function to find k closest points
    static int[][] kClosest(int[][] points, int k) {
        // Sort the points using the custom comparator
        Arrays.sort(points, (a, b) -> squaredDis(a) - squaredDis(b));

        // Return the first k closest points
        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i][0] = points[i][0];
            res[i][1] = points[i][1];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}, {5, -1}, {3, 2}, {1, 1}};
        int k = 3;

        int[][] res = kClosest(points, k);

        for (int i = 0; i < res.length; i++) {
            System.out.print("[" + res[i][0] + ", " + res[i][1] + "] ");
        }
    }
}