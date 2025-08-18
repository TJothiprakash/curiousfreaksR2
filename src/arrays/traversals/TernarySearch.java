package arrays.traversals;

/*Ternary Search

🔹 Works on sorted array.
Instead of dividing into 2 halves like binary search, divide into 3.

Intuition:

Compute 2 mid-points: mid1 = l + (r-l)/3, mid2 = r - (r-l)/3

Narrow down the search range.

Dry Run (arr=[1,2,3,4,5,6,7], target=5):

mid1=2, mid2=5 → arr[mid2]=6 > 5 → search in [3..4] → found 5


⏱️ Complexity:
Time: O(log3 n) ≈ O(log n)
Space: Recursive → O(log n)
*/
public class TernarySearch {
    static int ternarySearch(int l, int r, int target, int[] arr) {
        if (r >= l) {
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;

            if (arr[mid1] == target) return mid1;
            if (arr[mid2] == target) return mid2;

            if (target < arr[mid1]) return ternarySearch(l, mid1 - 1, target, arr);
            else if (target > arr[mid2]) return ternarySearch(mid2 + 1, r, target, arr);
            else return ternarySearch(mid1 + 1, mid2 - 1, target, arr);
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        System.out.println(ternarySearch(0, arr.length-1, 5, arr)); // 4
    }
}

