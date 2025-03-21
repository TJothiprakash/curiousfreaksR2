package practicesessions.dec_25_practice_session;

/*Node Class:
A simple class with two attributes: data and next.
Queue Class:
Initialize front and rear as null when the queue is empty.
For enqueue, create a new node, and adjust the pointers.
For dequeue, remove the node from the front and adjust the front pointer.
*/
public class CustomQueueUsingLinkedList {
    private Node front;
    private Node rear;

    public CustomQueueUsingLinkedList() {
        this.front = null;
        this.rear = null;
    }

    // Add an element to the queue
    public void enqueue(int data) {
        Node newNode = new Node(data);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }

    }

    // Remove an element from the queue
    public int dequeue() {
        if (front == null) {
            throw new RuntimeException("queue is empty!!! ");
        }
        int data = front.data;
        if (front.next != null) {
            front = front.next;
        }

        // If the queue is now empty, set rear to null as well
        if (front == null) {
            rear = null;
        }
        return data;

    }

    public int peek() {
        if (front != null) {
            return front.data;
        }
        return -1;
    }

    public boolean isEmpty() {
        return front == null;
    }

}
