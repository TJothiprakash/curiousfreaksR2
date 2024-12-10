package arrays.nov_06_revision;

public class Main {

    public static void main(String[] args) {
       /* Practice practice = new Practice();
        System.out.println(practice.gcd1(3, 9));*/

//        int[] arr = {1, 2, 3, 4, 5, 6, 7};
//        int k = 2;
//        RotateArray rotateArray = new RotateArray();
//      //  int[] rotatedArray = rotateArray.rotateArrayUsingReverse(arr, k);
//        /*for (int i = 0; i < rotatedArray.length; i++) {
//            System.out.print(rotatedArray[i] + " ");
//        }*/
//        rotateArray.reverseArray(arr, 0, 3);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
        ZerosinArray zerosinArray = new ZerosinArray();
        int[] arr = {0, 1, 0, 3, 12};
        zerosinArray.moveZeroes(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
