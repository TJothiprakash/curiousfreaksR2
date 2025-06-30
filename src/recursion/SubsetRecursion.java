package recursion;


import org.jetbrains.annotations.NotNull;

/*We use pure take/not-take recursion:

At each index:

Take the character → add it to current

Not take the character → skip it

Repeat until the end of string.

🌳 Subset Tree for "abc"
Each node = (index, currentSubset)

arduino
Copy
Edit
                      (0, "")
                  /             \
           (1, "a")            (1, "")
          /       \           /        \
     (2,"ab")   (2,"a")   (2,"b")   (2,"")
      /   \      /   \     /   \     /   \
(3,"abc")(3,"ab")(3,"ac")(3,"a")(3,"bc")(3,"b")(3,"c")(3,"")
At index == s.length(), we print the currentSubset.

*/
public class SubsetRecursion {
    public static void main(String[] args) {
        String input = "abc";
        generateSubsets(input, 0, "");
    }

    static void generateSubsets(@NotNull String input, int index, String current) {
        // 📍 Print the current recursion call
        System.out.println("Call → index: " + index + ", current: " + current);

        // ✅ Base Case: End of string
        if (index == input.length()) {
            System.out.println("Subset: " + current);
            return;
        }

        // ✅ Take current character
        generateSubsets(input, index + 1, current + input.charAt(index));

        // ❌ Do not take current character
        generateSubsets(input, index + 1, current);
    }
}


/*⏱️ Time & Space Complexity
Time: O(2ⁿ) → all subsets

Space: O(n) → recursion depth only*/