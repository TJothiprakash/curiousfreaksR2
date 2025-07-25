package dynamic_programming.july_24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IterativeApproach {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        generateSubsetsIteratively(arr);
    }

    public static void generateSubsetsIteratively(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());  // start with empty subset

        for (int num : arr) {
            int size = res.size();
            for (int i = 0; i < size; i++) {
                List<Integer> newSubset = new ArrayList<>(res.get(i));
                newSubset.add(num);
                res.add(newSubset);
            }
        }
        System.out.println(res);
        System.out.println("Subsets of " + Arrays.toString(arr) + ":");
        for (List<Integer> subset : res) {
            System.out.println(subset);
        }
        System.out.println("Total subsets: " + res.size());
    }


}


