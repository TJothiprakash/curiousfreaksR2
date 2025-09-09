package practicesessions.sept_8;
import dynamic_programming.F;

import java.util.PriorityQueue;

public class FurthestBuildingReachability {
    static void main() {
        FurthestBuildingReachability furthestBuildingReachability = new FurthestBuildingReachability();
        System.out.println(furthestBuildingReachability.furthestBuilding(new int[]{1,2,3,4,5,6,7},4,3));
    }
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = heights.length;

        for (int i = 0; i < n - 1; i++) {
            int climb = heights[i + 1] - heights[i];

            if (climb > 0) {
                minHeap.offer(climb);
                if (minHeap.size() > ladders) {
                    bricks -= minHeap.poll();
                }
                if (bricks < 0) {
                    return i;
                }
            }
        }
        return n - 1;
    }
}
