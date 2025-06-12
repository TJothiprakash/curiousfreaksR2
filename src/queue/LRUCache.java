package queue;

import java.util.HashMap;


public class LRUCache {
    private HashMap<Integer, Node> cache;
    private Node head, tail;
    private int capacity, size;
    // Constructor to initialize LRUCache
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.size = 0;

        // Create dummy head and tail nodes for easier list operations
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    // Helper method to move a node to the end (most recently used)
    private void moveToTail(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Helper method to add a node right before the tail (most recently used)
    private void addNode(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    // Helper method to remove a node from the doubly linked list
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // Get method to return the value of the key, if exists
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Move the accessed node to the tail (most recently used)
        Node node = cache.get(key);
        moveToTail(node);
        return node.value;
    }

    // Set method to insert or update the key-value pair
    public void set(int key, int value) {
        if (cache.containsKey(key)) {
            // Update the value and move the node to the tail
            Node node = cache.get(key);
            node.value = value;
            moveToTail(node);
        } else {
            // If cache is full, remove the least recently used item
            if (size == capacity) {
                // Remove the node from the head (least recently used)
                Node toRemove = head.next;
                removeNode(toRemove);
                cache.remove(toRemove.key);
                size--;
            }

            // Create a new node and add it to the cache and the list
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addNode(newNode);
            size++;
        }
    }

    // Doubly Linked List Node
    private static class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
/*

public class Main {
    public static void main(String[] args) {
        // Example Test Case 1
        LRUCache cache = new LRUCache(2);
        cache.set(1, 2);
        System.out.println(cache.get(1)); // Output: 2
        cache.set(2, 3);
        System.out.println(cache.get(2)); // Output: 3
        cache.set(1, 5); // Updates the value of key 1 to 5
        System.out.println(cache.get(1)); // Output: 5
//
        // Example Test Case 2
        LRUCache cache2 = new LRUCache(2);
        cache2.set(1, 2);
        cache2.set(2, 3);
        cache2.set(1, 5);
        cache2.set(4, 5);
        System.out.println(cache2.get(4)); // Output: 5
        System.out.println(cache2.get(3)); // Output: -1
    }
}

*/
