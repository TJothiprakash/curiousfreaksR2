package Arrays;
import java.util.Arrays;
public class ArrayOperations {
    public static void main(String[] args) {
        int [] arr = {98,32,12,233321,2,3,4,5,6,7,8,9,10};
        Arrays.sort(arr);
        int max = arr[arr.length-1];
        int min = arr[0];
        System.out.println("Max element is "+max+"\n min element is "+min);

        int arr1[]={1,2,4,5,6,7,8,9};
        int ans= findMissingElement(arr1);
        System.out.println("Missing element is "+ans);

    }

    private static int findMissingElement(int[] arr1) {
        int n= arr1[arr1.length-1];
        int sum = (n*(n+1))/2;
        int actualSum=0;
        for (int i = 0; i < arr1.length; i++) {
            actualSum=actualSum+arr1[i];
        }

        return sum-actualSum;
    }
}

