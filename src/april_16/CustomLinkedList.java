package april_16;

public class CustomLinkedList<T> {
    private Node<T> head;

    public CustomLinkedList() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        System.out.println("Node added: " + data);
    }

    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.print(temp.getData() + " -> ");
            temp = temp.getNext();
        }
        System.out.println("null");
    }

    public int getSize() {
        int count = 0;
        Node<T> temp = head;
        while (temp != null) {
            count++;
            temp = temp.getNext();
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void update(T oldData, T newData) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.getData().equals(oldData)) {
                temp.setData(newData);
                System.out.println("Updated node from " + oldData + " to " + newData);
                return;
            }
            temp = temp.getNext();
        }
        System.out.println("Data not found: " + oldData);
    }

    public void remove(T data) {
        if (head == null) return;

        if (head.getData().equals(data)) {
            head = head.getNext();
            System.out.println("Removed head node with data: " + data);
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {
            if (current.getData().equals(data)) {
                prev.setNext(current.getNext());
                System.out.println("Removed node with data: " + data);
                return;
            }
            prev = current;
            current = current.getNext();
        }
        System.out.println("Data not found: " + data);
    }

    public boolean find(T data) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.getData().equals(data)) {
                System.out.println("Found data: " + data);
                return true;
            }
            temp = temp.getNext();
        }
        System.out.println("Data not found: " + data);
        return false;
    }

    public void insertAtIndex(T data, int index) {
        if (index < 0) throw new IndexOutOfBoundsException("Invalid index");

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            System.out.println("Inserted at head: " + data);
            return;
        }

        Node<T> temp = head;
        for (int i = 0; i < index - 1 && temp != null; i++) {
            temp = temp.getNext();
        }

        if (temp == null) throw new IndexOutOfBoundsException("Index too large");

        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        System.out.println("Inserted " + data + " at index " + index);
    }
}
