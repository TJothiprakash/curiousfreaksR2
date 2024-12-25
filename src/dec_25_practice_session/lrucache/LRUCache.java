package dec_25_practice_session.lrucache;

import java.awt.*;
import java.util.HashMap;

public class LRUCache {
    private HashMap<Integer, Node> cache;
    private int capacity;
    private Node head, tail;

   // SystemTray.isSupported();
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            moveToFront(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            Node node = new Node(key, value);
            if (cache.size() == capacity) {
                Node tail = removeTail();
                cache.remove(tail.key);
            }
            cache.put(key, node);
            addToFront(node);
        }
    }

    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    private Node removeTail() {
        Node lruNode = tail.prev;
        removeNode(lruNode);
        return lruNode;
    }

    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
