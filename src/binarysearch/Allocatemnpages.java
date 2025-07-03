/*
Problem: Allocate Minimum Number of Pages (Book Allocation Problem)

Given an array `arr[]` representing the number of pages in each book and a number `k` representing the number of students,
allocate books such that:
- Each student gets **contiguous** books.
- Each book is assigned to exactly one student.
- You need to **minimize the maximum number of pages** assigned to a student.

Return the minimized maximum number of pages.

Intuition:
- We are searching for the **minimum possible maximum pages**.
- Use binary search between `max(arr)` and `sum(arr)` (worst case).
- For each `mid` (candidate for maximum pages per student), check if it is possible to allocate books to `k` students such that no one gets more than `mid` pages.

Dry Run:
Books = [12, 42, 54, 65, 76, 435, 536, 543, 753], Students = 4
Range = [max = 753, sum = total pages]
Try mid = 1000 → possible → minimize
Try mid = 700 → not possible → increase

Code:
*/

package binarysearch;

import java.util.Arrays;

public class Allocatemnpages {

    public static void main(String[] args) {
        int[] pages = new int[]{12, 42, 543, 21, 435, 536, 753, 65, 54, 76};
        Arrays.sort(pages);
        System.out.println("📚 Sorted pages: " + Arrays.toString(pages));
        int ans = allocateBooks(pages, 4);
        System.out.println("✅ Minimum max pages: " + ans);
    }

    // Main allocation logic using binary search
    static int allocateBooks(int[] arr, int k) {
        if (arr.length < k) return -1; // not enough books

        int low = Arrays.stream(arr).max().getAsInt();   // At least one student must get the largest book
        int high = Arrays.stream(arr).sum();             // One student gets all books
        int result = -1;

        System.out.println("🔍 Binary search from " + low + " to " + high);

        while (low <= high) {
            int mid = low + (high - low) / 2;
            System.out.println("➡️ Trying max pages = " + mid);

            if (isPossible(arr, k, mid)) {
                System.out.println("✅ Possible with " + mid + " pages");
                result = mid;
                high = mid - 1;
            } else {
                System.out.println("❌ Not possible with " + mid + " pages");
                low = mid + 1;
            }
        }

        return result;
    }

    // Helper: check if we can allocate books with `mid` as max pages
    static boolean isPossible(int[] arr, int k, int mid) {
        int students = 1;
        int pagesSum = 0;

        for (int pages : arr) {
            if (pages > mid) {
                System.out.println("   ❗ Book with " + pages + " > mid = " + mid);
                return false;
            }

            if (pagesSum + pages <= mid) {
                pagesSum += pages;
            } else {
                students++;
                pagesSum = pages;

                if (students > k) {
                    System.out.println("   🚫 Need more than " + k + " students");
                    return false;
                }
            }
        }

        return true;
    }

    /*
    Time Complexity: O(n * log(sum - max))
        - Binary search range is (max page, total pages)
        - For each mid, we check O(n) books

    Space Complexity: O(1)
    */
}
