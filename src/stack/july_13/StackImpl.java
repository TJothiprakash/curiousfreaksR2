package stack.july_13;

public class StackImpl<T> {
    private final T[] arr;
    private int top = -1;
    private int capacity;

    @SuppressWarnings("unchecked")
    public StackImpl(int capacity) {
        this.arr = (T[]) new Object[capacity];
    }

    // push , pop , peek, size, isEmpty
    public void push(T data) {
        if (top + 1 == arr.length) {
            throw new StackOverflowError(" stack is full");
        }
        arr[++top] = data;

    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        T data = arr[top];
        arr[top--] = null;
        return data;
    }

    public boolean isEmpty() {
        return top > 0;
    }

    public T peek() {
    if(isEmpty()){
        throw new RuntimeException("stack is empty");
    }
    return arr[top];
    }


}

