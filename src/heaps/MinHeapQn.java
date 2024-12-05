package heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinHeapQn {

    public static void main(String[] args) {
        int[] arr1 = {12, 5, 787, 1, 23};
        int k1 = 6;
        System.out.println(kthlargest(arr1, k1)); // Output: [787, 23]
    }
    public static List<Integer> kthlargest(int []arr, int k){
        PriorityQueue<Integer> minheap = new PriorityQueue<>(k);
        for(int num: arr){
            if(minheap.size() < k){
                minheap.add(num);
            }else if(minheap.peek() < num){
                minheap.poll();
                minheap.add(num);
            }
        }
       // System.out.println(minheap);
        ArrayList result = new ArrayList(minheap);
        Collections.sort(result.reversed());
       // System.out.println(result);
       return result;
    }
}
