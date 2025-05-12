package practicesessions.may_11;
import java.util.Objects;

public class CustomHashMap<K, V> {
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int SIZE = 16; // default number of buckets
    private Node<K, V>[] buckets;

    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        buckets = new Node[SIZE];
    }

    private int getBucketIndex(K key) {
        return Math.abs(Objects.hashCode(key)) % SIZE;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        Node<K, V> head = buckets[index];

        // Check if key already exists
        Node<K, V> current = head;
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                current.value = value; // update
                return;
            }
            current = current.next;
        }

        // Insert at head of list
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        buckets[index] = newNode;
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current.value;
            }
            current = current.next;
        }

        return null; // not found
    }

    public V remove(K key) {
        int index = getBucketIndex(key);
        Node<K, V> current = buckets[index];
        Node<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return current.value;
            }

            prev = current;
            current = current.next;
        }

        return null; // not found
    }

    public void printMap() {
        for (int i = 0; i < SIZE; i++) {
            Node<K, V> current = buckets[i];
            System.out.print("Bucket " + i + ": ");
            while (current != null) {
                System.out.print("[" + current.key + "=" + current.value + "] -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
}
