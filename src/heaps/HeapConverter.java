package heaps;
public class HeapConverter {
    public static void convertToMaxHeap(int[] heap) {
        int n = heap.length;
        // Start from the last non-leaf node and heapify each node
        for (int i = (n - 2) / 2; i >= 0; i--) {
            maxHeapify(heap, n, i);
        }
    }

    private static void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] minHeap = {1, 3, 5, 7, 9, 11};
        convertToMaxHeap(minHeap);

        for (int i : minHeap) {
            System.out.print(i + " ");
        }
        // Output: 11 9 5 7 3 1 (or another valid max-heap form)
    }
}
