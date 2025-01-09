package practicesessions.revisions.striver_sde_sheet;

import java.util.*;

public class Day_06 {
    //intersection of two linked list
    //utility function to check presence of intersection
    static DoublyNode intersectionPresent(DoublyNode head1, DoublyNode head2) {
        while (head2 != null) {
            DoublyNode temp = head1;
            while (temp != null) {
                //if both nodes are same
                if (temp == head2) return head2;
                temp = temp.next;
            }
            head2 = head2.next;
        }
        //intersection is not present between the lists return null
        return null;
    }

    //utility function to check presence of intersection
    static DoublyNode intersectionPresentusingHashset(DoublyNode head1, DoublyNode head2) {
        HashSet<DoublyNode> st = new HashSet<>();
        while (head1 != null) {
            st.add(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            if (st.contains(head2)) return head2;
            head2 = head2.next;
        }
        return null;

    }


    static int getDifference(DoublyNode head1, DoublyNode head2) {
        int len1 = 0, len2 = 0;
        while (head1 != null || head2 != null) {
            if (head1 != null) {
                ++len1;
                head1 = head1.next;
            }
            if (head2 != null) {
                ++len2;
                head2 = head2.next;
            }

        }
        return len1 - len2;//if difference is neg-> length of list2 > length of list1 else vice-versa
    }

    //utility function to check presence of intersection
    static DoublyNode intersectionPresentUsingDifference(DoublyNode head1, DoublyNode head2) {
        int diff = getDifference(head1, head2);
        if (diff < 0)
            while (diff++ != 0) head2 = head2.next;
        else while (diff-- != 0) head1 = head1.next;
        while (head1 != null) {
            if (head1 == head2) return head1;
            head2 = head2.next;
            head1 = head1.next;
        }
        return head1;

    }


    //utility function to check presence of intersection
    static DoublyNode intersectionPresentUsingDoublePointer(DoublyNode head1, DoublyNode head2) {
        DoublyNode d1 = head1;
        DoublyNode d2 = head2;

        while (d1 != d2) {
            d1 = d1 == null ? head2 : d1.next;
            d2 = d2 == null ? head1 : d2.next;
        }

        return d1;
    }


    // detect loop in linked list
    public static boolean detectLoop(DoublyNode head) {
        // Initialize a pointer 'temp'
        // at the head of the linked list
        DoublyNode temp = head;

        // Create a map to keep track
        // of encountered nodes
        Map<DoublyNode, Integer> nodeMap = new HashMap<>();

        // Step 2: Traverse the linked list
        while (temp != null) {
            // If the node is already in
            // the map, there is a loop
            if (nodeMap.containsKey(temp)) {
                return true;
            }
            // Store the current node in the map
            nodeMap.put(temp, 1);
            // Move to the next node
            temp = temp.next;
        }

        // Step 3: If the list is successfully
        // traversed without a loop, return false
        return false;
    }

    public static boolean detectCycle(DoublyNode head) {
        // Initialize two pointers, slow and fast,
        // to the head of the linked list
        DoublyNode slow = head;
        DoublyNode fast = head;

        // Step 2: Traverse the linked list
        // with the slow and fast pointers
        while (fast != null && fast.next != null) {
            // Move slow one step
            slow = slow.next;
            // Move fast two steps
            fast = fast.next.next;

            // Check if slow and fast pointers meet
            if (slow == fast) {
                return true;  // Loop detected
            }
        }

        // If fast reaches the end of the
        // list, there is no loop
        return false;
    }

    // Function to reverse a linked list
    // using the 3-pointer approach
    static DoublyNode reverseLinkedList(DoublyNode head) {
        // Initialize'temp' at
        // head of linked list
        DoublyNode temp = head;

        // Initialize pointer 'prev' to NULL,
        // representing the previous node
        DoublyNode prev = null;

        // Traverse the list, continue till
        // 'temp' reaches the end (NULL)
        while (temp != null) {
            // Store the next node in
            // 'front' to preserve the reference
            DoublyNode front = temp.next;

            // Reverse the direction of the
            // current node's 'next' pointer
            // to point to 'prev'
            temp.next = prev;

            // Move 'prev' to the current
            // node for the next iteration
            prev = temp;

            // Move 'temp' to the 'front' node
            // advancing the traversal
            temp = front;
        }

        // Return the new head of
        // the reversed linked list
        return prev;

    }

    // Function to get the Kth node from
    // a given position in the linked list
    static DoublyNode getKthNode(DoublyNode temp, int k) {
        // Decrement K as we already
        // start from the 1st node
        k -= 1;

        // Decrement K until it reaches
        // the desired position
        while (temp != null && k > 0) {
            // Decrement k as temp progresses
            k--;

            // Move to the next node
            temp = temp.next;
        }

        // Return the Kth node
        return temp;
    }

    // Function to reverse nodes in groups of K
    static DoublyNode kReverse(DoublyNode head, int k) {
        // Initialize a temporary
        // node to traverse the list
        DoublyNode temp = head;

        // Initialize a pointer to track the
        // last node of the previous group
        DoublyNode prevLast = null;

        // Traverse through the linked list
        while (temp != null) {

            // Get the Kth node of the current group
            DoublyNode kThDoublyNode = getKthNode(temp, k);

            // If the Kth node is NULL
            // (not a complete group)
            if (kThDoublyNode == null) {

                // If there was a previous group,
                // link the last node to the current node
                if (prevLast != null) {
                    prevLast.next = temp;
                }

                // Exit the loop
                break;
            }

            // Store the next node
            // after the Kth node
            DoublyNode nextDoublyNode = kThDoublyNode.next;

            // Disconnect the Kth node
            // to prepare for reversal
            kThDoublyNode.next = null;

            // Reverse the nodes from
            // temp to the Kth node
            reverseLinkedList(temp);

            // Adjust the head if the reversal
            // starts from the head
            if (temp == head) {
                head = kThDoublyNode;
            } else {
                // Link the last node of the previous
                // group to the reversed group
                prevLast.next = kThDoublyNode;
            }

            // Update the pointer to the
            // last node of the previous group
            prevLast = temp;

            // Move to the next group
            temp = nextDoublyNode;
        }

        // Return the head of the
        // modified linked list
        return head;
    }


}


class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode child;

    // Constructors to initialize the
    // data, next, and child pointers
    DoublyNode() {
        data = 0;
        next = null;
        child = null;
    }

    DoublyNode(int x) {
        data = x;
        next = null;
        child = null;
    }

    DoublyNode(int x, DoublyNode nextDoublyNode, DoublyNode childDoublyNode) {
        data = x;
        next = nextDoublyNode;
        child = childDoublyNode;
    }
}

class FlattenLinkedList {

    // Function to convert an ArrayList to a linked list
    static DoublyNode convertArrToLinkedList(ArrayList<Integer> arr) {
        // Create a dummy node to serve as
        // the head of the linked list
        DoublyNode dummyDoublyNode = new DoublyNode(-1);
        DoublyNode temp = dummyDoublyNode;

        // Iterate through the ArrayList and
        // create nodes with elements
        for (int i = 0; i < arr.size(); i++) {
            // Create a new node with the element
            temp.child = new DoublyNode(arr.get(i));
            // Move the temporary pointer
            // to the newly created node
            temp = temp.child;
        }
        // Return the linked list starting
        // from the next of the dummy node
        return dummyDoublyNode.child;
    }

    // Function to flatten a linked list with child pointers
    static DoublyNode flattenLinkedList(DoublyNode head) {
        ArrayList<Integer> arr = new ArrayList<>();

        // Traverse through the linked list
        while (head != null) {
            // Traverse through the child
            // nodes of each head node
            DoublyNode t2 = head;
            while (t2 != null) {
                // Store each node's data in the ArrayList
                arr.add(t2.data);
                // Move to the next child node
                t2 = t2.child;
            }
            // Move to the next head node
            head = head.next;
        }

        // Sort the ArrayList containing
        // node values in ascending order
        Collections.sort(arr);

        // Convert the sorted ArrayList
        // back to a linked list
        return convertArrToLinkedList(arr);
    }

    // Print the linked list by
    // traversing through child pointers
    static void printLinkedList(DoublyNode head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.child;
        }
        System.out.println();
    }

    // Print the linked list
    // in a grid-like structure
    static void printOriginalLinkedList(DoublyNode head, int depth) {
        while (head != null) {
            System.out.print(head.data);

            // If child exists, recursively
            // print it with indentation
            if (head.child != null) {
                System.out.print(" -> ");
                printOriginalLinkedList(head.child, depth + 1);
            }

            // Add vertical bars
            // for each level in the grid
            if (head.next != null) {
                System.out.println();
                for (int i = 0; i < depth; ++i) {
                    System.out.print("| ");
                }
            }
            head = head.next;
        }
    }

    public static void main(String[] args) {
        // Create a linked list with child pointers
        DoublyNode head = new DoublyNode(5);
        head.child = new DoublyNode(14);

        head.next = new DoublyNode(10);

        head.next.child = new DoublyNode(4);

        head.next.next = new DoublyNode(12);
        head.next.next.child = new DoublyNode(20);
        head.next.next.child.child = new DoublyNode(13);

        head.next.next.next = new DoublyNode(7);
        head.next.next.next.child = new DoublyNode(17);

        // Print the original
        // linked list structure
        System.out.println("Original linked list:");
        printOriginalLinkedList(head, 0);

        // Flatten the linked list
        // and print the flattened list
        DoublyNode flattened = flattenLinkedList(head);
        System.out.println("\nFlattened linked list: ");
        printLinkedList(flattened);
    }
}
