🚀 Java Sorting Algorithms Behind the Scenes
Data Type / Collection Sorting Method Algorithm Used Worst Case
Arrays.sort(int[])    Primitive array sort Dual-Pivot QuickSort O(n²)
Arrays.sort(Integer[])    Object array sort TimSort (Hybrid)    O(n log n) ✅
Collections.sort(List<T>)    List sort TimSort (Hybrid)    O(n log n) ✅

🔍 Why the Difference?
Primitives → Use QuickSort because it's fast and doesn't require boxing/unboxing.

Objects (like Integer[], String[], int[][], List<Integer>) → Use TimSort, a hybrid of:

Merge Sort (for guaranteed performance)

Insertion Sort (for small/sorted parts)

✅ Summary:
Yes, Collections and object arrays always use TimSort, so O(n log n) is guaranteed in worst case.

Only primitive arrays like int[] use Dual-Pivot QuickSort, which has a worst-case of O(n²), but it's fast in practice.


| **Algorithm**      | **Intuition (Logic)**                                                                                   | **Simple Code Lines (Java)**                                                                      |
| ------------------ | ------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------- |
| **Bubble Sort**    | Repeatedly swap adjacent elements if they are in the wrong order. Largest element “bubbles” to the end. | `for(i=0;i<n-1;i++) for(j=0;j<n-i-1;j++) if(a[j]>a[j+1]) swap(a,j,j+1);`                          |
| **Selection Sort** | Find the minimum element and put it in the correct position (front).                                    | `for(i=0;i<n-1;i++){ min=i; for(j=i+1;j<n;j++) if(a[j]<a[min]) min=j; swap(a,i,min); }`           |
| **Insertion Sort** | Take elements one by one and insert them in the correct position among the already sorted part.         | `for(i=1;i<n;i++){ key=a[i]; j=i-1; while(j>=0 && a[j]>key) a[j+1]=a[j--]; a[j+1]=key; }`         |
| **Merge Sort**     | Divide array into halves, sort recursively, and merge sorted halves.                                    | `mergeSort(a,l,r){ if(l<r){ m=(l+r)/2; mergeSort(a,l,m); mergeSort(a,m+1,r); merge(a,l,m,r); } }` |
| **Quick Sort**     | Pick a pivot, partition array into <pivot and >pivot, recursively sort partitions.                      | `quickSort(a,l,r){ if(l<r){ p=partition(a,l,r); quickSort(a,l,p-1); quickSort(a,p+1,r);} }`       |
| **Heap Sort**      | Build a max heap, repeatedly extract the max and place it at the end.                                   | `heapSort(a){ buildHeap(a); for(i=n-1;i>0;i--){ swap(a,0,i); heapify(a,0,i); } }`                 |
| **Counting Sort**  | Count frequency of each number, rebuild array in sorted order (works for integers, limited range).      | `countSort(a){ count[]=new int[max+1]; for(x:a) count[x]++; rebuild array; }`                     |
| **Radix Sort**     | Sort numbers digit by digit using counting sort as a subroutine.                                        | `for(exp=1;max/exp>0;exp*=10) countingSort(a,exp);`                                               |
