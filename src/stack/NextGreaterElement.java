package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NextGreaterElement {
    public static List<Integer> nextGreaterElement(int[] nums1) {
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < nums1.length; i++) {
            int next = nums1[i];
            boolean found = false;
            for (int j = i + 1; j < nums1.length; j++) {
                if (nums1[j] > next) {
                    result.add(nums1[j]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                result.add(-1);
            }
        }
        return result;
    }
    public static List<Integer> ngeUsingStack(int []numbs){
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        //1, 3, 2, 4
        // 3 ,4  ,4,-1
        for(int i=numbs.length-1;i>=0;i--){
            while(!stack.isEmpty() && stack.peek()<=numbs[i]){
                stack.pop();
            }
            result.add(stack.isEmpty()? -1:stack.peek());
            stack.push(numbs[i]);
        }
        return result;
    }


    // circular next greater element
    public static List<Integer> ngeUsingStackCircular(int []nums){
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=nums.length-1;i>=0;i--){
            while(!stack.isEmpty() && stack.peek()<=nums[i]){
                stack.pop();
            }
            result.add(stack.isEmpty()? -1:stack.peek());
            stack.push(nums[i]);
        }
        return result.reversed();
    }
/*
    public int[] nextGreaterElements(int[] nums) {
        int [] ans=new int[nums.length];
        Stack<Integer> st=new Stack<>();
        for(int i=nums.length*2-2;i>=0;i--){
            while(!st.isEmpty() && st.peek()<=nums[i%nums.length]){
                st.pop();
            }
            if(st.isEmpty()) ans[i%nums.length]=-1;
            else ans[i%nums.length]=st.peek();
            st.push(nums[i%nums.length]);
        }
        return ans;
    }*/
}
