package collections_practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    static void main() {

        int[] arr = new int[]{1, 2, 4, 5, 64, 6, 32};
        int sum = Arrays.stream(arr).sum();
        System.out.println("sum = " + sum);
        
        List<Integer> list = List.of(1, 2, 4, 5, 64, 6, 32);
       int sum1= list.stream().mapToInt(x -> x).sum();
       int sum2 = list.stream().reduce(0, Integer::sum);
        System.out.println("sum1 = " + sum1);
    }


}
