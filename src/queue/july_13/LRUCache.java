package queue.july_13; /******************************************************************************
 * Problem
 * --------
 * Design an LRU (Least Recently Used) cache that supports O(1) `get` and `put`
 * for up to `capacity` items. On cache overflow, evict the *least recently
 * used* item.
 *
 * Intuition
 * ---------
 * Combine:
 *   1. HashMap  : key  -> Node      (O(1) lookup)
 *   2. D‑Linked List: Node <-> Node (O(1) removal / add‑to‑front)
 * The list’s head = most recently used (MRU),
 * tail = least recently used (LRU).
 *
 * Dry‑Run
 * -------
 * capacity = 2
 * put(1,A)  => [1:A]                    map={1:node1}
 * put(2,B)  => [2:B, 1:A]               map={1,2}
 * get(1)    => returns A, list becomes  [1:A, 2:B]
 * put(3,C)  => overflow: evict tail(2)  list=[3:C, 1:A], map={1,3}
 *
 * Code
 * ----
 ******************************************************************************/

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head, tail; // dummy head / tail for clean ops

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity * 2);
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    /* O(1) public API */

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToFront(node);      // mark as recently used
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {                 // update existing
            node.value = value;
            moveToFront(node);
            return;
        }
        if (map.size() == capacity) {       // evict LRU
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
        Node fresh = new Node(key, value);  // insert new
        addAfterHead(fresh);
        map.put(key, fresh);
    }

    /* ───────────────── Helpers (all O(1)) ───────────────── */

    private void moveToFront(Node n) {
        remove(n);
        addAfterHead(n);
    }

    private void remove(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void addAfterHead(Node n) {
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
    }

    /* Quick demo */

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);       // [1]
        cache.put(2, 20);       // [2,1]
        System.out.println(cache.get(1)); // 10 => [1,2]
        cache.put(3, 30);       // evict 2 => [3,1]
        System.out.println(cache.get(2)); // -1
        cache.put(4, 40);       // evict 1 => [4,3]
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40
    }
}

/******************************************************************************
 * Complexity
 * ----------
 * Time : O(1) for both `get` and `put`.
 * Space: O(capacity) for HashMap + linked nodes.
 ******************************************************************************/
