package linkedlist;

public class Union {
    public static Node unionofList(Node head1, Node head2) {

        Node temp1 = head1;
        Node temp2 = head2;
        Node result = new Node(0); // Dummy node to start the result list
        Node temp = result; // Pointer to build the result list

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) {
                if (temp.data != temp1.data) {  // Check if data is not already added
                    temp.next = new Node(temp1.data);
                    temp = temp.next;
                }
                temp1 = temp1.next;  // Move to the next node in list 1
            } else if (temp1.data > temp2.data) {
                if (temp.data != temp2.data) {  // Check if data is not already added
                    temp.next = new Node(temp2.data);
                    temp = temp.next;
                }
                temp2 = temp2.next;  // Move to the next node in list 2
            } else {
                if (temp.data != temp1.data) {  // Check if data is not already added
                    temp.next = new Node(temp1.data);
                    temp = temp.next;
                }
                temp1 = temp1.next;
                temp2 = temp2.next;  // Move both pointers when data is equal
            }
        }

        // Process remaining nodes in list 1
        while (temp1 != null) {
            if (temp.data != temp1.data) {
                temp.next = new Node(temp1.data);
                temp = temp.next;
            }
            temp1 = temp1.next;
        }

        // Process remaining nodes in list 2
        while (temp2 != null) {
            if (temp.data != temp2.data) {
                temp.next = new Node(temp2.data);
                temp = temp.next;
            }
            temp2 = temp2.next;
        }

        return result.next; // Return the result, skipping the dummy node
    }
}
