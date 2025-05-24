package queue.may_24_2025;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }


}

public class QueueImplUsingLL {
    Node front, rear;
    int size;

    public QueueImplUsingLL() {
        this.front = null;
        this.rear = null;
        this.size = 0;

    }

    public void enqueue(int data) {
        Node new_Node = new Node(data);
        if (this.isEmpty()) {
            front = rear = new_Node;
            size++;
            return;
        }
        rear.next = new_Node;
        rear = new_Node;
        size++;
        return;

    }

    private boolean isEmpty() {
        return this.front == null;
    }

    public int size() {
        return this.size;
    }

    public void display() {
        if (this.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = this.front;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public int dequeue() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("Queue is empty");
        }
        int data = this.front.data;
        this.front = this.front.next;
        size--;
        if (this.front == null) {
            this.rear = null;
        }
        return data;
    }

}
