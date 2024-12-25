package dec_25_practice_session;

public class CustomQueueImplUsingArray {
    public static void main(String[] args) {
        CustomQueueImplUsingArray queue = new CustomQueueImplUsingArray(3);

        System.out.println("Is Empty: " + queue.isEmpty()); // true
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println(queue.size());
        System.out.println("Is Full: " + queue.isEmpty()); // true

        System.out.println("Peek: " + queue.peek()); // 10
        System.out.println("Dequeue: " + queue.dequeue()); // 10
        System.out.println("Peek: " + queue.peek()); // 20
        System.out.println(queue.size());
        queue.enqueue(40); // Adds 40
        System.out.println("Dequeue: " + queue.dequeue()); // 20

        System.out.println("Is Empty: " + queue.isEmpty()); // false
        System.out.println(queue.size());
    }

    private int[] queue;
    private int front;
    private int rear;
    private int size;

    // Constructor to initialize the queue
    public CustomQueueImplUsingArray(int size) {
        this.size = size;
        queue = new int[size];
        front = -1; // Points to the first element
        rear = -1;  // Points to the last element
    }

    // Add an element to the queue
    public void enqueue(int data) {
        if (rear == size -1 ) { // Overflow check
            throw new RuntimeException("Queue is full now!");
        }
        if (front == -1) { // First element added
            front = 0;
        }
        queue[++rear] = data; // Increment rear and add element
    }

    // Remove an element from the queue
    public int dequeue() {
        if (front == -1 || front > rear) { // Underflow check
            throw new RuntimeException("Queue is empty!");
        }
        int data = queue[front]; // Get the front element
        front++; // Increment front
        if (front > rear) { // Reset pointers if queue is now empty
            front = -1;
            rear = -1;
        }
        return data;
    }

    // Peek at the front element without removing it
    public int peek() {
        if (front == -1) { // Check if queue is empty
            throw new RuntimeException("Queue is empty!");
        }
        return queue[front];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == -1; // Both pointers reset
    }
    public int size() {
        return rear - front + 1;
    }
    public void checkgit(){

    }
}
