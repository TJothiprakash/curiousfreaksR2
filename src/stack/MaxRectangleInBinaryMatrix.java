package stack;

import java.util.Stack;

public class MaxRectangleInBinaryMatrix {
    public static int maximalRectangle(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) return 0;

        int n = mat.length;
        int m = mat[0].length;
        int[] heights = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            // Update the histogram heights for the current row
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 1) {
                    heights[j] += 1;
                } else {
                    heights[j] = 0; // Reset height if current cell is 0
                }
            }

            // Compute the largest rectangle for the current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // Helper function to calculate the largest rectangle area in a histogram
    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n) ? 0 : heights[i];

            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
                maxArea = Math.max(maxArea, height * width);
            }

            stack.push(i);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[][] mat1 = {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0}
        };

        int[][] mat2 = {
                {0, 1, 1},
                {1, 1, 1},
                {0, 1, 1}
        };

        System.out.println(maximalRectangle(mat1)); // Output: 8
        System.out.println(maximalRectangle(mat2)); // Output: 6
    }
}

