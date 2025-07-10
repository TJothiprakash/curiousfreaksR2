package arrays;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread());
        int a = 10;
        final Object obj = new Thread(() -> {
        });
        System.out.println(a);
        int[] arr = {1, 2, 3, 4, 5};
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        System.out.println();
        ans.add(2);
        ans.add(3);
        System.out.println(ans);
        System.out.println("ans = " + ans);
        for (int i = 0; i < arr.length; i++) {
            int i1 = arr[i];

        }
        for (Integer an : ans) {
            System.out.println(an);
        }
        System.out.println("arr = " + arr);
    }
}
