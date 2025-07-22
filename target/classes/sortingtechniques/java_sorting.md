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
