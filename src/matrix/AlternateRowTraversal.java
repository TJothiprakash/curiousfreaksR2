package matrix;

public class AlternateRowTraversal {

    //    O(n *n) O(1)
    public static void printAlternateRows(int[][] matrix) {
        // Loop through each row of the matrix
        for (int i = 0; i < matrix.length; i++) {
            if (i % 2 == 0) {
                // Traverse from left to right for even-indexed rows
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
            } else {
                // Traverse from right to left for odd-indexed rows
                for (int j = matrix[i].length - 1; j >= 0; j--) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println(); // Move to the next line after each row
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        System.out.println("Alternate Row-wise Traversal:");
        printAlternateRows(matrix);
    }
}
