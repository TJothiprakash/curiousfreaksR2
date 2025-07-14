package stack.july_13;
public class TwoStacksUsingArray<T> {
    private final T[] arr;
    private int front = -1;
    private int back;

    @SuppressWarnings("unchecked")
    public TwoStacksUsingArray(int capacity) {
        this.arr = (T[]) new Object[capacity];
        this.back = capacity; // Start just beyond last index
    }

    public void pushFront(T value) {
        if (front + 1 == back) {
            throw new RuntimeException("Stack is full (front + 1 == back)");
        }
        arr[++front] = value;
    }

    public T popFront() {
        if (front == -1) {
            throw new RuntimeException("Front stack is empty");
        }
        T value = arr[front];
        arr[front--] = null;
        return value;
    }

    public void pushBack(T value) {
        if (front + 1 == back) {
            throw new RuntimeException("Stack is full (front + 1 == back)");
        }
        arr[--back] = value;
    }

    public T popBack() {
        if (back == arr.length) {
            throw new RuntimeException("Back stack is empty");
        }
        T value = arr[back];
        arr[back++] = null;
        return value;
    }

    public boolean isFull() {
        return front + 1 == back;
    }

    public int frontSize() {
        return front + 1;
    }

    public int backSize() {
        return arr.length - back;
    }
}

