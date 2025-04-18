package practicesessions.april_16;

public class LinkedList {
    public static void main(String[] args) {
        Node head = new Node(1);
        Node tail = new Node(2);
        Node n1 = new Node(3);
        Node n2 = new Node(4);
        Node n3 = new Node(5);
        Node n4 = new Node(6);
        Node n5 = new Node(7);
        Node n6 = new Node(8);
        Node n7 = new Node(9);
        Node n8 = new Node(10);
        Node n9 = new Node(11);
        Node n10 = new Node(12);
        Node n11 = new Node(13);
        Node n12 = new Node(14);
        Node n13 = new Node(15);
        Node n14 = new Node(16);
        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);
        n7.setNext(n8);
        n8.setNext(n9);
        n9.setNext(n10);
        n10.setNext(n11);
        n11.setNext(n12);
        n12.setNext(n13);
        n13.setNext(n14);
        n14.setNext(tail);

        // display data
        while (head.getNext() != null) {
            System.out.println(head.getData());
            head = head.getNext();
        }
    }
}
