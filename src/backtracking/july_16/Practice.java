package backtracking.july_16;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        Practice practice = new Practice();
//        List<List<String>> result = practice.rat_In_a_Maze(new int[][]{{1, 0, 1, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}});
//        List<List<String>> result1 = practice.rat_In_a_Maze(new int[][]{{1, 0, 0},
//                {1, 1, 0},
//                {0, 1, 1}});
//        System.out.println("rat in a maze solution  : " + result1);
//
        int n = 4;
        List<List<Integer>> ans = practice.nQueensPuzzle(n);
        System.out.println(ans);
    }

    public List<String> permuteString(String s) {
        List<Integer> path = new ArrayList<>();
        List<String> result = new ArrayList<>();
        boolean[] visited = new boolean[s.length()];
        StringBuilder sb = new StringBuilder();
        backtrack(s, sb, path, result, visited);
        return result;

    }

    public void backtrack(
            String s,
            StringBuilder sb,
            List<Integer> path,
            List<String> result,
            boolean[] visited
    ) {
        if (sb.length() == s.length()) {
            result.add(sb.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            sb.append(s.charAt(i));
            backtrack(s, sb, path, result, visited);
            visited[i] = false;
            sb.deleteCharAt(i);
        }
    }

    public List<List<Integer>> combinationSUmII(int candidates[], int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack1(candidates, target, 0, path, result);
        return result;
    }

    private void backtrack1(int[] candidates, int target, int i, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int j = i; j < candidates.length; j++) {
            if (candidates[j] > target) break;
            if (j > i && candidates[j] == candidates[j - 1]) continue;
            path.add(candidates[j]);
            backtrack1(candidates, target - candidates[j], j + 1, path, result);
            path.remove(path.size() - 1);

        }
    }

    public List<List<Integer>> validCombinations(int k, int target) {
        int nums[] = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        backtrack2(nums, path, result, k, target, 0, 0);

        return result;
    }

    private void backtrack2(int[] nums, List<Integer> path, List<List<Integer>> result, int k, int target, int count, int index) {
        if (count == k && target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > target) break;
            path.add(nums[i]);
            backtrack2(nums, path, result, k, target - nums[i], count + 1, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public List<List<String>> rat_In_a_Maze(int mat[][]) {
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] visited = new boolean[n][m];
        if (mat[0][0] == 1) {
            backtrack3(mat, path, result, 0, 0, n, m, visited);
//            backtrack(mat, path, result, 0, 0, n, m, visited);
        }

        return result;
    }

    private void backtrack3(int[][] mat, List<String> path, List<List<String>> result, int i, int j, int n, int m, boolean[][] visited) {
        if (i == n - 1 && j == m - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        visited[i][j] = true;

        int dx[] = {0, 0, +1, -1};
        int dy[] = {+1, -1, 0, 0};
        String s[] = {"R", "L", "D", "U"};
        for (int k = 0; k < 4; k++) {
            int di = i + dx[k];
            int dj = j + dy[k];
            if (isSafeMove(di, dj, mat, visited, n, m)) {
                path.add(s[k]);
                backtrack3(mat, path, result, di, dj, n, m, visited);
                path.remove(path.size() - 1);
            }
        }
        visited[i][j] = false;
    }

    private boolean isSafeMove(int i, int j, int[][] mat, boolean[][] visited, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m && mat[i][j] == 1 && !visited[i][j];
    }

    private static final String[] keypad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> findkeypadComnbinations(int arr[]) {
        List<String> result = new ArrayList<>();
        if (arr == null || arr.length == 0) return result;

        backtrack4(arr, result, 0, new StringBuilder());
        return result;
    }

    private void backtrack4(int[] arr, List<String> result, int index, StringBuilder sb) {
        if (index == arr.length) {
            result.add(sb.toString());
            return;
        }

        String letters = keypad[arr[index]];

        for (char c : letters.toCharArray()) {
            sb.append(c);
            backtrack4(arr, result, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);

        }
    }

    public List<List<Integer>> subsetsinLexicographicalorder(int arr[]) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack5(0, arr, result, path);
        return result;
    }

    public void backtrack5(int index, int arr[], List<List<Integer>> result, List<Integer> path) {

        result.add(new ArrayList<>(path));

        for (int i = index; i < arr.length; i++) {
            path.add(arr[i]);
            backtrack5(i + 1, arr, result, path);
            path.remove(path.size() - 1);
        }

    }

    public List<List<Integer>> subsetwithDuplicates(int arr[]) {
        Arrays.sort(arr);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        backtrack6(0, arr, result, path);
        return result;
    }

    private void backtrack6(int index, int @NotNull [] arr, @NotNull List<List<Integer>> result, List<Integer> path) {
        result.add(new ArrayList<>(path));

        for (int i = index; i < arr.length; i++) {

            if (i > index && arr[i] == arr[i - 1]) continue;
            path.add(arr[i]);
            backtrack6(i + 1, arr, result, path);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> nQueensPuzzle(int n) {
        int row = n, col = n;
        int board[][] = new int[row][col];
        List<List<Integer>> result = new ArrayList<>();
        placeQueens(0, board, result, n);
        return result;
    }

    private void placeQueens(int col, int[][] board, List<List<Integer>> result, int n) {
        if (col == n) {
            List<Integer> list = new ArrayList<>();
            for (int c = 0; c < n; c++) {
                for (int r = 0; r < n; r++) {
                    if (board[r][c] == 1) {
                        list.add(r + 1);
                        break;
                    }
                }
            }
            result.add(list);
            printBoard(board, n);
            return;
        }
        for (int row = 0; row < n; row++) {
            if (checkSafePlace(row, col, board, n)) {
                board[row][col] = 1;
                placeQueens(col + 1, board, result, n);
                board[row][col] = 0;
            }
        }
    }

    private boolean checkSafePlace(int row, int col, int[][] board, int n) {
        // 1) Check row on the left side
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 1) return false;
        }

        // 2) Check upper‑left diagonal ↖
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) return false;
        }

        // 3) Check lower‑left diagonal ↙
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) return false;
        }

        // Column is implicitly unique, so we're safe
        return true;
    }

    private void printBoard(int[][] board, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println("------");
    }

    public List<String> permutationWithSpace(String s) {

        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        backtrack7(1, s, sb, result);
        Collections.sort(result);
        return result;
    }

    private void backtrack7(int index, String s, StringBuilder sb, List<String> result) {
        if (index == s.length()) {
            result.add(sb.toString());
            return;
        }

        sb.append(' ').append(s.charAt(index));   // branch A
        backtrack7(index + 1, s, sb, result);
        sb.delete(sb.length() - 2, sb.length());  // undo both chars

        sb.append(s.charAt(index));               // branch B
        backtrack7(index + 1, s, sb, result);
        sb.deleteCharAt(sb.length() - 1);         // undo one char

    }

    public List<String> restoreIPAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack8(0, s, new ArrayList<>(), result);
        return result;
    }

    private void backtrack8(int index, String s, ArrayList<String> path, List<String> result) {
        if (path.size() == 4 && index == s.length()) {
//            result.add(path.toString());
            result.add(String.join(".", path));
            return;
        }

        for (int i = index; i < index + 3 && i < s.length(); i++) {
            String temp = s.substring(index, i + 1);
            if (temp.length() > 1 && temp.charAt(0) == '0') continue;
            int num = Integer.parseInt(temp);
            if (num > 255) break;
            path.add(temp);
            backtrack8(i + 1, s, path, result);
            path.remove(path.size() - 1);

        }


    }


    //    find kth permutation - formaula approach
    public String kthPermutationUsingFormula(int n, int k) {
        // Pre‑compute factorials up to 9! (fits in int)
        int[] fact = new int[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = fact[i - 1] * i;

        // 1) Build the initial list of digits.
        List<Integer> digits = new ArrayList<>();
        for (int i = 1; i <= n; i++) digits.add(i);

        // 2) Convert (k‑1) to factorial number system.
        StringBuilder sb = new StringBuilder();
        int index = k - 1;   // make it 0‑based

        for (int i = n; i >= 1; i--) {
            int blockSize = fact[i - 1];          // (i-1)! permutations per leading digit
            int idx = index / blockSize;          // which digit to pick
            index = index % blockSize;            // remainder for the sub‑problem

            sb.append(digits.get(idx));
            digits.remove(idx);                   // shrink the pool
        }
        return sb.toString();
    }

    public String kthPermutationUsingBacktracking(int n, int k) {
        List<Integer> result = new ArrayList<>();
        boolean[] used = new boolean[n + 1];
        int[] fact = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        // Precompute factorials
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        backtrack9(n, k, used, fact, sb);
        return sb.toString();
//        return null;
    }

    private void backtrack9(int n, int k, boolean[] used, int[] fact, StringBuilder sb) {
        for (int pos = 0; pos < n; pos++) {
            for (int num = 1; num <= n; num++) {
                if (used[num]) continue;

                int perms = fact[n - pos - 1];

                if (k > perms) {
                    k -= perms; // skip this block
                } else {
                    sb.append(num);
                    used[num] = true;
                    backtrack9(n, k, used, fact, sb); // recurse for next position
                    return;
                }
            }
        }
    }

}
