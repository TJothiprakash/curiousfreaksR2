✅ Time and Space Complexity of Recursion

1. Time Complexity
   Time complexity in recursion depends on:

The number of recursive calls made.

Whether there’s overlapping computation (which can be optimized with memoization).

🔹 Basic Rule: Total time = number of calls × work done per call

2. Space Complexity
   This mainly comes from the call stack:

Each recursive call adds a new frame to the stack.

So, maximum depth of recursion = space used.

🔸 If recursion is tail-recursive and optimized, stack usage can be minimal.
🔸 But in general, space = O(depth of recursion)

🔁 Classic Examples

1. Simple Linear Recursion
   java
   Copy
   Edit
   void printN(int n) {
   if (n == 0) return;
   System.out.println(n);
   printN(n - 1);
   }
   Time = O(n) → 1 call per value down to 0

Space = O(n) → Call stack holds n frames before base case

2. Fibonacci (Without Memoization)
   java
   Copy
   Edit
   int fib(int n) {
   if (n <= 1) return n;
   return fib(n-1) + fib(n-2);
   }
   Time = O(2^n) → Exponential tree of calls

Space = O(n) → Max depth of recursion is n

3. Backtracking (e.g., N-Queens)
   Time: Depends on the total number of combinations tried

Space: O(n) (for recursive depth) + possibly extra for tracking state

4. Merge Sort
   java
   Copy
   Edit
   void mergeSort(int[] arr, int l, int r) {
   if (l < r) {
   int m = (l + r) / 2;
   mergeSort(arr, l, m);
   mergeSort(arr, m + 1, r);
   merge(arr, l, m, r);
   }
   }
   Time = O(n log n)

Space:

Call stack = O(log n)

If using temp arrays in merge step = O(n) auxiliary space

✅ Conclusion
Recursion Type Time Complexity Space Complexity (Stack)
Linear recursion O(n)    O(n)
Tree recursion O(2ⁿ), O(3ⁿ), etc. O(n)
Divide & conquer O(n log n)    O(log n) (e.g., merge sort)
Tail recursion (optimized)    O(n)    O(1)
