package dynamic_programming.july_24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {


    public static void main(String[] args) {
        int arr[] = {1, 2, 3};
        int arr1[] = {0, 2, 4, 6, 8};
        new Test1().generate(arr);
        new Test1().generate(arr1);

    }

    public void generate(int arr[]) {
        List<List<Integer>> res = new ArrayList<>();
        List<List<Integer>> res1 = new ArrayList<>();
        generateSubsets(arr, res, 0, new ArrayList<>());
        generateSubsequence(arr, res1, 0, new ArrayList<>());
        System.out.println("Subset for array " + Arrays.toString(arr) + " is: " + res + " and its size is " + res.size());
        System.out.println(res);
        System.out.println("Subset for array " + Arrays.toString(arr) + " is: " + res + " and its size is " + res.size());
        System.out.println(res1);

    }

    private void generateSubsequence(int[] arr, List<List<Integer>> res, int index, List<Integer> currList) {
        if (index == arr.length) {
            res.add(new ArrayList<>(currList));
            return;
        }

//        not take
        generateSubsequence(arr, res, index + 1, currList);
        currList.add(arr[index]);
//        take
        generateSubsequence(arr, res, index + 1, currList);
        currList.remove(currList.size() - 1);
    }


    private void generateSubsets(int[] arr, List<List<Integer>> res, int index, List<Integer> currList) {
        if (index == arr.length) {
            res.add(new ArrayList<>(currList));
            return;
        }
//        not take
        generateSubsets(arr, res, index + 1, currList);
        currList.add(arr[index]);
//        take
        generateSubsets(arr, res, index + 1, currList);
        currList.remove(currList.size() - 1);
    }

    public void generate(String arr[]) {
        List<String> res = new ArrayList<>();
        System.out.println(res);
    }

}
