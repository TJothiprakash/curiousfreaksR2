package revision.custom_data_structures;

public class CustomStack<T> {
    private Object[] stack;
    private int pointer;   // points to next empty slot
    private int capacity;  // total max size
    private int size;      // number of elements in stack

    public CustomStack(int capacity) {
        this.capacity = capacity;
        this.stack = new Object[capacity];
        this.pointer = 0;
        this.size = 0;
    }

    public CustomStack() {
        this(10); // default capacity
    }

    public void push(T data) {
        if (size == capacity) {
            System.out.println("Stack is full!!!");
            return;
        }
        stack[pointer++] = data;
        size++;
    }

    public T pop() {
        if (size == 0) {
            System.out.println("Stack is empty!");
            throw new RuntimeException("Empty stack");
        }
        T data = (T) stack[--pointer];
        stack[pointer] = null; // avoid memory leak
        size--;
        return data;
    }

    public T peek() {
        if (size == 0) {
            throw new RuntimeException("Empty stack");
        }
        return (T) stack[pointer - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
