package linkedlist.july_09;


public class LinkedList<T> {
    private Node<T> root;

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> temp = root;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void insert(int index, T data) {
        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = root;
            root = newNode;
            return;
        }
        Node<T> temp = root;
        int currentIndex = 0;
        while (temp != null && currentIndex < index - 1) {
            temp = temp.next;
            currentIndex++;
        }
        if (temp == null) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void update(T data, T newData) {
        Node<T> temp = root;
        while (temp != null) {
            if (temp.data.equals(data)) {
                temp.data = newData;
                return;
            }
            temp = temp.next;
        }
        throw new RuntimeException("Data not found: " + data);
    }

    public void delete(int index) {
        if (root == null) {
            throw new RuntimeException("List is empty!");
        }
        if (index == 0) {
            root = root.next;
            return;
        }
        Node<T> temp = root;
        int currentIndex = 0;
        while (temp != null && currentIndex < index - 1) {
            temp = temp.next;
            currentIndex++;
        }
        if (temp == null || temp.next == null) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        temp.next = temp.next.next;
    }

    public void print() {
        Node<T> temp = root;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Node class inside for encapsulation
    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public void search(T data) {
        if (root == null) {
            System.out.println("list is empty ");
            return;
        }
        int index = 0;
        Node temp = root;
        while (temp != null) {
            if (temp.data.equals(data)) {
                System.out.println(data + " found in the list @ index " + index);
                return;
            }
            index++;
            temp = temp.next;
        }
        System.out.println(data + " not found in the list ");
    }

    public void searchR(T data) {
        if (root == null) return;
        int index = 0;
        Node temp = root;
        boolean isfound = helper(0, temp, data);
        System.out.println("is the data found " + isfound);
    }

    public boolean helper(int index, Node root, T data) {
        if (root == null) {
            return false;
        }
        if (root.data.equals(data)) {
            System.out.println(data + " found in the list @ index " + index);
            return true;
        }
        return helper(index + 1, root.next, data);


    }

    // reverse the linked list
    public Node<T> reverseI(Node<T> root) {
        if (root == null) {
            System.out.println(" list is empty");
            return root;
        }
        Node temp = root;
        Node prevNode = null;

        while (temp != null) {
            Node<T> nextNode = temp.next; // save next
            temp.next = prevNode;  // reverse pointer
            prevNode = temp; // move prev forward
            temp = nextNode;// move current forward
        }
        return prevNode;

    }

    public Node<T> reverseR() {
        return reverseR(root);
    }

    public Node<T> reverseR(Node<T> root) {
        if (root == null) {
            System.out.println("list is empty");
            return root;
        }
        Node head = helper1(root);

        return head;
    }

    private Node helper1(Node<T> root) {
        if (root == null || root.next == null) {
            return root;
        }
        Node newHead = helper1(root.next);

        root.next.next = root;
        root.next = null;
        return newHead;
    }
    /*public Node<T> reverseI(Node<T> root) {
    if (root == null) {
        System.out.println("List is empty");
        return null;
    }

    Node<T> prevNode = null;
    Node<T> current = root;

    while (current != null) {
        Node<T> nextNode = current.next; // save next
        current.next = prevNode;         // reverse pointer
        prevNode = current;              // move prev forward
        current = nextNode;              // move current forward
    }

    return prevNode; // new head
}
*/


}
