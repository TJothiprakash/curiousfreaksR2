package recursion;

import java.util.ArrayList;
import java.util.List;

public class Pattern {
    public static void main(String[] args) {
        int n = 31;
        List<Integer> res = printPattern(n);
        System.out.println(res);
    }

    private static List<Integer> printPattern(int n) {
        List<Integer> res = new ArrayList<>();
        generatePattern(n, res, n, false);
        return res;
    }

    private static void generatePattern(int original, List<Integer> res, int current, boolean increasing) {
        res.add(current);

        // Base case: Stop when we return to the original value
        if (current == original && increasing) {
            return;
        }

        // Recursive case: Decrease or increase by 5
        if (current > 0 && !increasing) {
            generatePattern(original, res, current - 5, false);
        } else {
            generatePattern(original, res, current + 5, true);
        }
    }
}






/*
    private static List printPattern(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(n);
        pattern(n,res,n-5);
        return res;
    }

    private static void pattern(int n, List<Integer> res,int i) {
        if(n==i){
            return;
        }
        if(n>0){
            i=i-5;
            res.add(n);
            pattern(n,res,i);
        }else if(n<0){
            i=i+5;
            res.add(n);
            pattern(n,res,i);
        }
    }*/

