package stack.july_13;


import java.util.Stack;

public class GetMinFromStack {
    public static void main(String[] args) {

    }

    public int getMinFromStackInO1Time(Integer[] arr) {
        Stack<Integer> mainStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        for (Integer a : arr) {
            mainStack.push(a);
            if (minStack.isEmpty() || a <= minStack.peek()) {
                minStack.push(a);
            }
        }

        return minStack.isEmpty() ? -1 : minStack.peek(); // O(1) getMin
    }

}