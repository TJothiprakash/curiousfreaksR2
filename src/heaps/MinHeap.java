package heaps;

import java.util.ArrayList;

public class MinHeap {
    private ArrayList<Integer> heap;

    // Constructor for the class.
    MinHeap(int size) {
        heap = new ArrayList<>(size); // Initialize the heap with a capacity.
    }

    // Function to peek at the minimum element without removing it.
    int peekMinElement() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        return heap.get(0); // Return the root of the heap, which is the minimum.
    }

    // Function to extract and remove the minimum element.
    int extractMinElement() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        int minElement = heap.get(0); // Root element is the minimum.
        int lastElement = heap.remove(heap.size() - 1); // Remove the last element.
        if (!heap.isEmpty()) {
            heap.set(0, lastElement); // Replace root with the last element.
            downHeap(0); // Restore the heap property.
        }
        return minElement;
    }

    // Function to delete an element at a specific index.
    void deleteElement(int index) {
        if (index < 0 || index >= heap.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        heap.set(index, Integer.MIN_VALUE); // Replace with a very small value.
        upHeap(index); // Move it to the root.
        extractMinElement(); // Remove the root (minimum value).
    }

    // Function to insert a value into the heap.
    void insert(int val) {
        heap.add(val); // Add the new value to the end of the list.
        upHeap(heap.size() - 1); // Adjust the heap upwards to restore the heap property.
    }

    // Helper method to restore the heap property upwards.
    private void upHeap(int index) {
        int parent = parent(index);
        while (index > 0 && heap.get(index) < heap.get(parent)) {
            swap(index, parent);
            index = parent;
            parent = parent(index);
        }
    }

    // Helper method to restore the heap property downwards.
    private void downHeap(int index) {
        int smallest = index;
        int left = left(index);
        int right = right(index);

        if (left < heap.size() && heap.get(left) < heap.get(smallest)) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }
        if (smallest != index) {
            swap(index, smallest);
            downHeap(smallest);
        }
    }

    // Helper methods to calculate parent, left, and right child indices.
    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return 2 * index + 1;
    }

    private int right(int index) {
        return 2 * index + 2;
    }

    // Helper method to swap two elements in the heap.
    private void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}

