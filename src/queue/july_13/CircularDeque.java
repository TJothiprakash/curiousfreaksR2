package queue.july_13;

public class CircularDeque<T> {

    private final Object[] arr;
    private int front = 0;
    private int rear = 0;
    private final int capacity;

    public CircularDeque(int size) {
        if (size < 2) throw new IllegalArgumentException("Capacity must be ≥ 2");
        this.capacity = size;
        this.arr = new Object[size];
    }

    public void offerFirst(T data) {
        if (isFull()) throw new IllegalStateException("Deque is full");
        front = (front - 1 + capacity) % capacity;
        arr[front] = data;
    }

    public void offerLast(T data) {
        if (isFull()) throw new IllegalStateException("Deque is full");
        arr[rear] = data;
        rear = (rear + 1) % capacity;
    }

    @SuppressWarnings("unchecked")
    public T pollFirst() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        T value = (T) arr[front];
        arr[front] = null;
        front = (front + 1) % capacity;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T pollLast() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        rear = (rear - 1 + capacity) % capacity;
        T value = (T) arr[rear];
        arr[rear] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peekFirst() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        return (T) arr[front];
    }

    @SuppressWarnings("unchecked")
    public T peekLast() {
        if (isEmpty()) throw new IllegalStateException("Deque is empty");
        int lastIdx = (rear - 1 + capacity) % capacity;
        return (T) arr[lastIdx];
    }

    public int size() {
        return (rear - front + capacity) % capacity;
    }

    public boolean isEmpty() {
        return front == rear;
    }

    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
}
