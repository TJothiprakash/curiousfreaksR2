package queue.may_24_2025;

import java.util.HashMap;

public class LRUCache {

    private class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head, tail; // dummy nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // dummy head and tail to avoid null checks
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        moveToFront(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value; // update
            moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }

            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToFront(newNode);
        }
    }

    // ---------------- Helper Methods ----------------

    private void removeNode(Node node) {
        // remove node from its current position
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    private void addToFront(Node node) {
        // insert node right after head
        Node first = head.next;

        node.prev = head;
        node.next = first;

        head.next = node;
        first.prev = node;
    }

    private void moveToFront(Node node) {
        // move accessed node to front
        removeNode(node);
        addToFront(node);
    }

    // For testing/demo
    public void display() {
        Node curr = head.next;
        while (curr != tail) {
            System.out.print("(" + curr.key + ", " + curr.value + ") ");
            curr = curr.next;
        }
        System.out.println();
    }
}

