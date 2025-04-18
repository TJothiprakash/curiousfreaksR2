package practicesessions.april_18;

public class CustomDoublyLinkedList {

    public static void main(String[] args) {
        DoublyNode head = new DoublyNode(1);
        DoublyNode second = new DoublyNode(2);
        DoublyNode third = new DoublyNode(3);
        head.setNext(second);
        second.setPrev(head);
        second.setNext(third);
        third.setPrev(second);
//        System.out.println(head.getData());
        displayNode(head);


    }


    private static void displayNode(DoublyNode head) {

        DoublyNode temp = head;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    private static void reverseLinkedList(DoublyNode head) {
        DoublyNode prev = null;
        DoublyNode current = head;
        DoublyNode next = null;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            current.setPrev(next);
            prev = current;
            current = next;
        }
        head = prev;
        System.out.println("Reversed linked list");

    }

}
