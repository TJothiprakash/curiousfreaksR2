package practicesessions.dec_25_practice_session;

public class CustomStack {
    private static final int RESIZE_FACTOR = 2;
    private int[] stack;
    private int size;
    private int index = 0;

    private CustomStack() {
        int DEFAULT_SIZE = 10;
        this.stack = new int[DEFAULT_SIZE];

    }

    private CustomStack(int size) {
        this.size = size;
        this.stack = new int[size];

    }

    // push
    public void push(int data) throws Exception {
        if (index == size) {
            if (!resize(size * RESIZE_FACTOR)) {
                throw new Exception("stack is full and can't be resized.");
            }
        }
        stack[index++] = data;

    }

    private boolean resize(int i) {
        if (i > Integer.MAX_VALUE) {
            return false;
        }
        int[] oldStack = stack;
        stack = new int[i];
        copyElements(oldStack, stack);
        return true;
    }

    private void copyElements(int[] oldStack, int[] stack) {
        for (int i = 0; i < oldStack.length; i++) {
            stack[i] = oldStack[i];
        }
    }

    //pop
    public int pop() {
        int temp = stack[index];
        stack[index] = 0;
        index--;
        return temp;
    }

    public int peek() {
        return stack[index];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == size;
    }

}
