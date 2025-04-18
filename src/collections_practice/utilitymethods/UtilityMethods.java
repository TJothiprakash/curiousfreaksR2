package collections_practice.utilitymethods;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UtilityMethods {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int[] arr = new int[]{1, 2, 4, 6, 23, 2, 3, 0, 43, 43, 89, 65,0,0};
        //list = List.of(1, 2, 3, 1, 2, 7, 8, 4, 5, 9);
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println(list);
        Collections.sort(list);
        System.out.println("Sorted order :" + "" + list);
        Collections.reverse(list);
        System.out.println("Reverse order :" + "" + list);
        List<String> listOfStrings = List.of("apple", "banana", "grape", "cherry", "mango");
        String max = Collections.max(listOfStrings);
        String min = Collections.min(listOfStrings);
        System.out.println("Max is "+max +" Min is "+ min );
        int minnum = Collections.min(list);
        int maxnum = Collections.max(list);
        System.out.println("Min and max numbers are "+minnum +" "+" "+maxnum);
        int freqofanumber = Collections.frequency(list,0);
        System.out.println(freqofanumber);
        System.out.println(list);
        Collections.replaceAll(list,0, 99);
        System.out.println(list);
        List<Integer > copy = new ArrayList<Integer>(Collections.nCopies(list.size(),0));

        System.out.println("Before copying "+copy);
        Collections.copy(copy,list);
        System.out.println("After copying "+copy);
        Collections.rotate(list, 3);
        System.out.println("After 3 rotations " +list);
        Collections.unmodifiableCollection(list);
        System.out.println(list);
        Collections.synchronizedList(list);

    }
}
