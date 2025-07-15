package heaps.july_14;


// array  type data structures represented as tree like
// minheap, maxheap
//poll, peek, remove, size , isempty
// log n retrieval and insertion

/// *
///  root
/// parent - i -1/2
/// left - 2i+1
/// right -   2i - 2
/// */

public class HeapImpl {

    private final int[] heap;
    private final int capacity;
    private int size;

    public HeapImpl(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;// default starting pt
    }
    // upheap, downheap , add eleemnt , remove element, peek, size, isempty

    public int poll() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        int data = heap[0];
        int index = size - 1;
        heap[0] = heap[index];
        size--;
        bubbleDown(0);
        return data;
    }

    private void bubbleDown(int index) {
        while (index < size) {
            int left = left(index);
            int right = right(index);
            int largets = index;
            if (left < size && heap[left] > heap[largets]) largets = left;
            if (right < size && heap[right] > heap[largets]) largets = right;
            if (largets != index) {
                swap(index, largets);
                index = largets;

            } else {
                break;
            }
        }
    }

    public void add(int data) {
        if (size == capacity) throw new RuntimeException(" heap is full "); // can add resize logic later
        int index = size;
        heap[index] = data;
        size++;
        bubbleUp(index);
    }

    private void bubbleUp(int index) {

        int parent = (index - 1) / 2;
        //        int parent = (index - 1) / 2;
        //        while (index > 0 && heap[index]  violates‑heap‑rule‑with  heap[parent]) {
        //            swap(index, parent);
        //            index   = parent;
        //            parent  = (index - 1) / 2;
        //        }
        while (index > 0 && heap[index] >= heap[parent]) {
            swap(index, parent);
            index = parent;
            parent = parent(index);
        }

    }


    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }


    private void swap(int index, int parent) {
        int temp = heap[index];
        heap[index] = heap[parent];
        heap[parent] = temp;

    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Heap is empty");
        return heap[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

}
