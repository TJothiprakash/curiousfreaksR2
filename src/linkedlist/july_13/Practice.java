package linkedlist.july_13;

class Node<T> {
    T data;
    Node next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }

}


public class Practice<T> {
    public static <T> void main(String[] args) {
        Node<T> root = null;
        System.out.println("hello world");
        System.out.println("hello world" + root);
    }
}


