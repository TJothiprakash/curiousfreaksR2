package matrix;

import matrix.r_6.MatrixPractice;

public class Main {
    public static void main(String[] args) {
        RotateMatrix rotateMatrix = new RotateMatrix();
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RotateMatrix.rotateMatrix(arr);
        System.out.println("After Rotation");
        /*O(n * n) O(r * c))*/
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        MatrixPractice matrixPractice = new MatrixPractice();
        matrixPractice.rotate(arr);

    }
}
