package backtracking;

public class Analysis {

    // create all subsets of "abcd"
    void backtrack(String s, int index, StringBuilder current) {
        if (index == s.length()) {
            System.out.println(current);
            return;
        }

        backtrack(s, index + 1, current);
        current.append(s.charAt(index));
        backtrack(s, index + 1, current);
        current.deleteCharAt(current.length() - 1);

    }

    public static void main(String[] args) {
        Analysis sol = new Analysis();
        String s = "abcd";
        sol.backtrack(s, 0, new StringBuilder());
    }

}
