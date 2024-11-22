package linkedlist;

public class ReverseLinkedList {
    public static Node reverseList(Node head) {
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            Node next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;


    }

    public static Node recursiveApproach(Node head){
        if(head==null || head.next==null){
            return head;
        }
        Node newHead=recursiveApproach(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }
}
