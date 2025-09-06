package linkedlist.sept_6;

public class Operations {

    static void main(){
        Node root = new Node(4);
        Node one= new Node(3);
        root.next = one;
        Node two = new Node(1);
        one.next = two;
    }
    Node reverse(Node head) {
        if (head == null) {
            return head;
        }
        Node current = head;
        while (current.next != null) {
            Node next = current.next;
            current.next = reverse(current);
            current.next = next;

        }
        return head;
    }
}
