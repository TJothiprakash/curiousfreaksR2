package greedy;
/*esign a data structure that works like a LRU Cache. Here cap denotes the capacity of the cache and Q denotes the number of queries. Query can be of two types:

SET x y: sets the value of the key x with value y
GET x: gets the key of x if present else returns -1.
The LRUCache class has two methods get() and set() which are defined as follows.

get(key): returns the value of the key if it already exists in the cache otherwise returns -1.
set(key, value): if the key is already present, update its value. If not present, add the key-value pair to the cache. If the cache reaches its capacity it should remove the least recently used item before inserting the new item.
In the constructor of the class the capacity of the cache should be initialized.
Examples:

Input: cap = 2, Q = 2, Queries = SET 1 2, GET 1
Output: 2
Explanation: Cache Size = 2
SET 1 2 will insert the key-value pair (1,2) in the cache,
GET 1 will print the value corresponding to Key 1, ie 2.
Input: cap = 2,Q = 8, Queries = SET 1 2, SET 2 3, SET 1 5, SET 4 5, SET 6 7, GET 4, SET 1 2, GET 3
Output: 5 -1
Explanation: Cache Size = 2
SET 1 2 will insert the pair (1,2) in the cache.
SET 2 3 will insert the pair (2,3) in the cache: 1->2, 2->3(the most recently used one is kept at the rightmost position)
SET 1 5 will replace the value of 1 from 2 to 5 : 2 -> 3, 1 -> 5
SET 4 5 : 1 -> 5, 4 -> 5 (Cache size is 2, hence we delete the least recently used key-value pair)
SET 6 7 : 4 -> 5, 6 -> 7
GET 4 : Prints 5 (The cache now looks like 6 -> 7, 4->5)
SET 1 2 : 4 -> 5, 1 -> 2  (Cache size is 2, hence we delete the least recently used key-value pair)
GET 3 : No key value pair having key = 3. Hence, -1 is printed.
Expected Time Complexity: O(1) for both get() and set().
Expected Auxiliary Space: O(1) for both get() and set().
(Although, you may use extra space for cache storage and implementation purposes).

Constraints:
1 <= cap <= 10^3
1 <= Q <= 10^5
1 <= x, y <= 10^4
Approach:
Data Structures:

Hash Map (map): Stores key-value pairs.
Doubly Linked List: Keeps track of the order of usage, where the most recently used items are at the front and the least recently used items are at the back.
Methods:

get(key): Returns the value if the key exists and updates its position to the front (most recently used), else returns -1.
set(key, value): Adds or updates the key-value pair in the cache, moving the item to the front. If the cache exceeds capacity, removes the least recently used item (the item at the back).
Ja*/

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private Map<Integer, Node> cache; // HashMap to store key -> Node
    private int capacity;
    private Node head, tail; // Dummy head and tail to avoid null checks

    public LRUCache(int cap) {
        capacity = cap;
        cache = new HashMap<>();
        head = new Node(-1, -1); // Dummy head
        tail = new Node(-1, -1); // Dummy tail
        head.next = tail;
        tail.prev = head;
    }

    public Node getHead() {
        return head;
    }

    // Move the node to the front (most recently used)
    private void moveToFront(Node node) {
        removeNode(node);
        addNode(node);
    }

    // Add node to the front (right after head)
    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    // Remove the node from the list
    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    // Get the value for the key, move it to the front if it exists
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1; // Key not found
        }
        moveToFront(node); // Move this node to the front
        return node.value;
    }

    // Set the value for the key, update or add a new key-value pair
    public void set(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            node = new Node(key, value);
            if (cache.size() == capacity) {
                // Remove the least recently used (LRU) node
                cache.remove(tail.prev.key);
                removeNode(tail.prev);
            }
            addNode(node);
            cache.put(key, node);
        } else {
            node.value = value;
            moveToFront(node); // Move this node to the front (most recently used)
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void printCache() {
        Node head = getHead();
        System.out.println("-----------------------------------------------------");
        System.out.println("map is " + cache);

        while (head != null) {
            System.out.print("{  key " + head.key + " value is " + head.value + " } -> ");
            head = head.next;
        }
        System.out.println("end");
        System.out.println("-----------------------------------------------------");
    }

    // Doubly Linked List Node
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

class Main0 {
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);

        cache.set(1, 2); // Cache is {1=2}
        System.out.println(cache.get(1)); // returns 2
        cache.set(2, 3); // Cache is {1=2, 2=3}
        System.out.println(cache.get(2)); // returns 3
        cache.set(1, 5); // Cache is {2=3, 1=5}
        System.out.println(cache.get(1)); // returns 5
        System.out.println(cache.get(2)); // returns 3
        cache.set(4, 6); // Cache is {1=5, 4=6} (evicts key 2)
        System.out.println(cache.get(2)); // returns -1 (not found)
        System.out.println(cache.get(4)); // returns 6
        cache.printCache();
        cache.get(6);
        cache.set(3,8);
        cache.get(3);
        cache.get(2);

        cache.printCache();
    }
}
