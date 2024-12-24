package arrays.practice.dec_24_revision;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Practice practice = new Practice();
        //practice.subArray(new int[]{1, 2, 3, 4});
        // practice.subArraysUsingRecursion(new int[]{1,2,3,4});
        List<List<Integer>> listsofSubarrays = new ArrayList<>();
        ArrayList<Integer> subArray = new ArrayList<>();
        listsofSubarrays = practice.generateSubarraysUsingSlidingWindow(new int[]{1, 2, 3, 4});
        /*System.out.println(listsofSubarrays);
        System.out.println(listsofSubarrays.size());*/
    }

}
