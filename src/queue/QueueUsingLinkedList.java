package queue;

public class QueueUsingLinkedList<T> {
    private Node<T> front; // Points to the front of the queue
    private Node<T> rear;  // Points to the rear of the queue
    private int size;      // Tracks the number of elements in the queue
    public QueueUsingLinkedList() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Add an element to the rear of the queue
//    O(1)O(1)
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (rear == null) {
            // If the queue is empty, both front and rear point to the new node
            front = rear = newNode;
        } else {
            // Add the new node at the rear and update rear
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove and return the element from the front of the queue
//    O(1)O(1)
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        T data = front.data; // Get the data from the front node
        front = front.next;  // Move the front pointer to the next node
        if (front == null) {
            // If the queue becomes empty, update rear to null
            rear = null;
        }
        size--;
        return data;
    }

    // Peek at the front element without removing it
//    O(1)O(1)
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty!");
        }
        return front.data;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Get the current size of the queue
    public int size() {
        return size;
    }

    // Display the elements of the queue
//    O(n)O(1)
    public void display() {
        Node<T> current = front;
        System.out.print("Queue: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Node class representing each element in the queue
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}

