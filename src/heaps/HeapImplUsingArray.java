package heaps;

import java.util.Arrays;

public class HeapImplUsingArray {
    private static final int DEFAULT_CAPACITY = 10;
    private int[] heapArray;
    private int size;
    private int capacity;

    // Constructors
    public HeapImplUsingArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.heapArray = new int[capacity];
        this.size = 0;
    }

    public HeapImplUsingArray(int capacity) {
        this.capacity = capacity;
        this.heapArray = new int[capacity];
        this.size = 0;
    }

    public static void main(String[] args) {
        HeapImplUsingArray heap = new HeapImplUsingArray();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(2);
        heap.insert(1);
        heap.displayHeap();
    }

    // Insert a new element into the heap
    public void insert(int data) {
        if (size == capacity) {
            resizeHeap();
        }
        heapArray[size] = data; // Add the new element at the end
        upheap(size); // Adjust the heap
        size++;
    }

    // Remove the root (minimum element in a min-heap)
    public int remove() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int root = heapArray[0];
        heapArray[0] = heapArray[size - 1]; // Replace root with the last element
        size--;
        downheap(0); // Adjust the heap
        return root;
    }

    // Helper to adjust heap after insertion
    private void upheap(int i) {
        int parentIndex = parent(i);
        while (i > 0 && heapArray[i] > heapArray[parentIndex]) {
            swap(i, parentIndex);
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    // Helper to adjust heap after removal
    private void downheap(int i) {
        while (true) {
            int leftChild = left(i);
            int rightChild = right(i);
            int smallest = i;

            if (leftChild < size && heapArray[leftChild] > heapArray[smallest]) {
                smallest = leftChild;
            }

            if (rightChild < size && heapArray[rightChild] > heapArray[smallest]) {
                smallest = rightChild;
            }

            if (smallest == i) {
                break;
            }

            swap(i, smallest);
            i = smallest;
        }
    }

    // Get the parent index
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Get the left child index
    private int left(int index) {
        return 2 * index + 1;
    }

    // Get the right child index
    private int right(int index) {
        return 2 * index + 2;
    }

    // Swap elements at two indices
    private void swap(int i, int j) {
        int temp = heapArray[i];
        heapArray[i] = heapArray[j];
        heapArray[j] = temp;
    }

    // Resize the heap array when capacity is exceeded
    private void resizeHeap() {
        capacity *= 2;
        heapArray = Arrays.copyOf(heapArray, capacity);
    }

    // Display the heap
    public void displayHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heapArray[i] + " ");
        }
        System.out.println();
    }

    // Get the size of the heap
    public int getSize() {
        return size;
    }

}

