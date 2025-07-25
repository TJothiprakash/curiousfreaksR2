package dynamic_programming.july_24;

import dynamic_programming.july_24.abstractions.SequenceGenerator;

import java.util.ArrayList;
import java.util.List;

public class StringSubsequenceGenerator extends SequenceGenerator<Character> {
    public StringSubsequenceGenerator(String str) {
        super(toCharList(str));
    }

    private static List<Character> toCharList(String str) {
        List<Character> chars = new ArrayList<>();
        for (char c : str.toCharArray()) chars.add(c);
        return chars;
    }

    @Override
    protected void handleBaseCase(List<Character> curr) {
        // Convert to string if needed
        StringBuilder sb = new StringBuilder();
        for (char c : curr) sb.append(c);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        new StringSubsequenceGenerator("abc").generateAll();
    }
}
