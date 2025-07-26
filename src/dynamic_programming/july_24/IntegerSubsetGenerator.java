package dynamic_programming.july_24;

import dynamic_programming.july_24.abstractions.SequenceGenerator;

import java.util.List;

public class IntegerSubsetGenerator extends SequenceGenerator<Integer> {
    public IntegerSubsetGenerator(List<Integer> input) {
        super(input);
    }

    @Override
    protected void handleBaseCase(List<Integer> curr) {
        result.add(curr); // Add directly to result
    }

    public static void main(String[] args) {
        List<Integer> input = List.of(1, 2, 3);
        IntegerSubsetGenerator gen = new IntegerSubsetGenerator(input);
        List<List<Integer>> subsets = gen.generateAll();

        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }
    }
}
