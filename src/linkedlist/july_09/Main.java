package linkedlist.july_09;

public class Main {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList<>();
        linkedList.add(0);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.print();
        System.out.println();
        LinkedList.Node reversed = linkedList.reverseR();
        while (reversed != null){
            System.out.print( reversed.data + " -> ");
            reversed = reversed.next;
        }
        System.out.println("null");
        System.out.println();
        linkedList.print();
        System.out.println();
    }
}
