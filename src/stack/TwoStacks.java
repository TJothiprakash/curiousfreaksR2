package stack;

class TwoStacks {
    int[] arr; // Array to store both stacks
    int top1;  // Top pointer for stack 1
    int top2;  // Top pointer for stack 2
    int size;  // Size of the array

    // Constructor to initialize the two stacks
    public TwoStacks(int n) {
        size = n;
        arr = new int[n];
        top1 = -1;
        top2 = n;
    }

    // Test the functionality
    public static void main(String[] args) {
        TwoStacks stacks = new TwoStacks(10); // Array size is 10
        stacks.push1(2);
        stacks.push1(3);
        stacks.push2(4);

        System.out.println(stacks.pop1()); // Output: 3
        System.out.println(stacks.pop2()); // Output: 4
        System.out.println(stacks.pop2()); // Output: -1
    }

    // Push an element into stack 1
    public void push1(int x) {
        if (top1 < top2 - 1) { // Check for available space
            top1++;
            arr[top1] = x;
        } else {
            System.out.println("Stack Overflow: No space for push1");
        }
    }

    // Push an element into stack 2
    public void push2(int x) {
        if (top1 < top2 - 1) { // Check for available space
            top2--;
            arr[top2] = x;
        } else {
            System.out.println("Stack Overflow: No space for push2");
        }
    }

    // Pop an element from stack 1
    public int pop1() {
        if (top1 >= 0) { // Check if stack 1 is not empty
            int result = arr[top1];
            top1--;
            return result;
        } else {
            return -1; // Stack 1 is empty
        }
    }

    // Pop an element from stack 2
    public int pop2() {
        if (top2 < size) { // Check if stack 2 is not empty
            int result = arr[top2];
            top2++;
            return result;
        } else {
            return -1; // Stack 2 is empty
        }
    }
}
