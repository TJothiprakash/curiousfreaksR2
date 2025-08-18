package arrays.traversals;
/*Staircase Algorithm (2D Matrix Search)

🔹 Used in a sorted 2D matrix (each row & column sorted).

Intuition:

Start from top-right corner.

If element > target → move left.

If element < target → move down.

Dry Run (matrix below, target=5):
1  4  7
2  5  8
3  6  9


Start (0,2)=7 → >5 → left to (0,1)=4

<5 → move down to (1,1)=5 → found
⏱️ Complexity: O(n+m)*/
public  class StaircaseSearch {
    static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;
        int i = 0, j = m - 1;

        while (i < n && j >= 0) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] mat = {
                {1, 4, 7},
                {2, 5, 8},
                {3, 6, 9}
        };
        System.out.println(searchMatrix(mat, 5)); // true
    }
}
