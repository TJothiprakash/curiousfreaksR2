package linkedlist.aug_30;


class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode prev;
    Node node = null;

    public DoublyNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
        this.node = null;
    }
}

public class Practice {
    static void main(String[] args) {

    }

    public Node cloneLL(Node root) {
        Node cloneHead = new Node(root.data);
        Node original = root.next;
        Node cloneTail = cloneHead;

        while (original != null) {
            cloneTail.next = new Node(original.data);
            cloneTail = cloneTail.next;
            original = original.next;
        }
        return cloneHead;
    }

    public DoublyNode cloneDoublyNode(DoublyNode root) {
        DoublyNode cloneHead = new DoublyNode(root.data);
        DoublyNode prev = cloneHead;
        DoublyNode original = root.next;

        while (original != null) {
            DoublyNode newNode = new DoublyNode(original.data);
            prev.next = newNode;

            newNode.prev = prev;

            prev = newNode;
            original = original.next;

        }
        return cloneHead;
    }


    public DoublyNode flatten(DoublyNode root) {

        DoublyNode flattened = new DoublyNode(-1);
        DoublyNode original = root;
        DoublyNode prev = flattened;


        while (original != null) {
            flattened.next = original;
            while (original.node != null) {
                original.node = original.node.next;
            }

        }
        return null;

    }

}

