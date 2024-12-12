package linkedlist.practice;

public class Main {
  /*  static {
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println("This is staic block and it is invoked before main method");
        System.out.println("----------------------------------------------------");
        System.out.println();
    }
*/
    public static void main(String[] args) {
        Node head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);
        Node fourth = new Node(4);
        Node fifth = new Node(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = null;
       /* printList(head);
        System.out.println();*/
    /*    Node res = new LinkedListOperations().searchElement(head, 3);
        System.out.println("Element found at index: " + res.data);
*/
       // System.out.println(head.data);
        Node res = new LinkedListOperations().reverseRecursively(head);

        System.out.println();
        printList(res);
        System.out.println();
        //System.out.println(res.data);
    }


    private static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + "-> ");
            head = head.next;
        }
    }
}
