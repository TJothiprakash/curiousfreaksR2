package stack;

import java.util.List;
import java.util.Stack;

import static stack.StockSpan.calculateSpan;

public class Main {
    public static void main(String[] args) {
        StackImpl<Integer> stack = new StackImpl<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
      /*  System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/
      /*  for (int i = 0; i < 256; i++) {
            stack.push(i);
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }*/
        // next greater element
       /* int [] arr={1, 3, 2, 4};
        List<Integer> result = NextGreaterElement.ngeUsingStack(arr); // [3, 4, 4, -1
        System.out.println(result);*/
        /*int [] arr={3, 8, 5, 2, 25};
        List<Integer>result = NextSmallestElements.nsOnLeft(arr.length,arr);
        System.out.println(result);*/
     /*   int[] arr1 = {100, 80, 60, 70, 60, 75, 85};
        int[] result1 = calculateSpan(arr1);
        for (int span : result1) {
            System.out.print(span + " ");
        }
        System.out.println();

        int[] arr2 = {10, 4, 5, 90, 120, 80};
        int[] result2 = calculateSpan(arr2);
        for (int span : result2) {
            System.out.print(span + " ");*/


        Stack<Integer> stack1 = new Stack<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);

        System.out.println("Original Stack: " + stack1);
      Stack<Integer> ans= Reverse.reverseonspace(stack1);
        System.out.println("Reversed Stack: " + ans);
    }

}
