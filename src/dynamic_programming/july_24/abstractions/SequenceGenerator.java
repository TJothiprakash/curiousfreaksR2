package dynamic_programming.july_24.abstractions;
import java.util.ArrayList;
import java.util.List;

public abstract class SequenceGenerator<T> {
    protected List<T> input;
    protected List<List<T>> result = new ArrayList<>();

    public SequenceGenerator(List<T> input) {
        this.input = input;
    }

    public List<List<T>> generateAll() {
        generate(0, new ArrayList<>());
        return result;
    }

    protected abstract void handleBaseCase(List<T> curr);

    protected void generate(int index, List<T> curr) {
        if (index == input.size()) {
            handleBaseCase(new ArrayList<>(curr));
            return;
        }

        // Not pick
        generate(index + 1, curr);

        // Pick
        curr.add(input.get(index));
        generate(index + 1, curr);
        curr.remove(curr.size() - 1); // Backtrack
    }
}
