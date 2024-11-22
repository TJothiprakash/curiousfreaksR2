package stack;

public class StackImpl<T> {
    private T[] stackArray;
    private int top = -1;
    private int capacity;

    public StackImpl() {
        this.capacity = 10;
        stackArray = (T[]) new Object[capacity];
    }

    public StackImpl(int initialCapacity) {
        this.capacity = initialCapacity;
        stackArray = (T[]) new Object[capacity];
    }

    private void resizeStack(int newCapacity) {
        T[] newStack = (T[]) new Object[newCapacity];
        System.arraycopy(stackArray, 0, newStack, 0, stackArray.length);
        stackArray = newStack;
        capacity = newCapacity;
    }

    public boolean push(T data) {
        if (top == capacity - 1) {
            System.out.println("Stack is full, resizing the stack");
            resizeStack(capacity * 2);  // Double the capacity
        }
        stackArray[++top] = data;
        return true;
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }
        return stackArray[top--];
    }

    public T peek() {
        if (top == -1) {
            System.out.println("Stack is empty");
            return null;
        }
        return stackArray[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
