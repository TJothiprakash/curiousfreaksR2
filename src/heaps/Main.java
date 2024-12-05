package heaps;

public class Main {
    public static void main(String[] args) throws Exception {
        MinHeap minHeap = new MinHeap(10);

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);
/*
        System.out.println(minHeap.peekMinElement()); // Outputs 1 (minimum, no removal)
        System.out.println(minHeap.extractMinElement()); // Outputs 1 and removes it
        System.out.println(minHeap.peekMinElement()); // Outputs 3 (new minimum)*/
       /* TopKFrequentElements topKfrequentElements = new TopKFrequentElements();
        int [] arr={1,1,2,3,2,1,2,1};
        int k = 2;
        System.out.println(topKfrequentElements.topKFrequent(arr,k));*/
        KthLargestElementinStreams kthLargestElementinStreams = new KthLargestElementinStreams();
        int []arr= {1,2,3,4,5,6};
        int k=4;
        int n= arr.length;
        System.out.println(kthLargestElementinStreams.kthLargestinStreams(k,arr,n));
    }
}
