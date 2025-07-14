package stack.july_13;

import java.util.Stack;

/*💡 Intuition
We reduce the 2D problem to multiple histogram problems.

🧠 Core Idea:
For each row in the matrix, treat it like the base of a histogram.

If a cell has 1, accumulate the height from the row above.

Then, for each row’s histogram → apply Largest Rectangle in Histogram.

🔁 Dry Run Example
Given:

java
Copy
Edit
mat = [
  [0, 1, 1, 0],
  [1, 1, 1, 1],
  [1, 1, 1, 1],
  [1, 1, 0, 0]
]
We build height arrays:

sql
Copy
Edit
row 0: [0, 1, 1, 0]         → maxArea = 2
row 1: [1, 2, 2, 1]         → maxArea = 4
row 2: [2, 3, 3, 2]         → maxArea = 8 ✅
row 3: [3, 4, 0, 0]         → maxArea = 6*/

public class MaxRectangleInBinaryMatrix {

    public static int maximalRectangle(int[][] mat) {
        if (mat.length == 0) return 0;

        int n = mat.length;
        int m = mat[0].length;
        int[] height = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            // Step 1: Build histogram row by row
            for (int j = 0; j < m; j++) {
                height[j] = (mat[i][j] == 0) ? 0 : height[j] + 1;
            }

            // Step 2: Apply largest rectangle in histogram
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }

        return maxArea;
    }

    // Reuse your histogram area logic
    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[n];
        int[] right = new int[n];

        // Nearest Smaller to Left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Nearest Smaller to Right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) stack.pop();
            right[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }

        // Max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea, area);
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

        System.out.println("Max Rectangle Area: " + maximalRectangle(mat1)); // Output: 8
        System.out.println("Max Rectangle Area: " + maximalRectangle(mat2)); // Output: 6
    }
}
