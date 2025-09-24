package practicesessions.sept_24.concurrency;

import java.util.concurrent.Callable;

public class FactorialCalculator implements Callable<Integer> {
    private int number;

    public FactorialCalculator(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        // TODO: Calculate factorial and return it
        int answer = 1;
        for (int i = 1; i <= number; i++) {
            answer *= i;
        }
        return answer;
    }
}
