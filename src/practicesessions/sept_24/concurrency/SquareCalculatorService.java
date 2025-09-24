package practicesessions.sept_24.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SquareCalculatorService {

    public void calculateSquares() throws InterruptedException, ExecutionException {
        // Create a single-threaded executor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Prepare a list of Callable tasks
        List<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(new Square(5));
        tasks.add(new Square(7));
        tasks.add(new Square(10));

        // Submit all tasks and get Future objects
        List<Future<Integer>> futures = new ArrayList<>();
        for (Callable<Integer> task : tasks) {
            futures.add(executor.submit(task));
        }

        // Collect results
        for (Future<Integer> future : futures) {
            System.out.println("Square result: " + future.get());
        }

        // Shutdown executor
        executor.shutdown();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new SquareCalculatorService().calculateSquares();
    }
}

class Square implements Callable<Integer> {
    private final int a;

    public Square(int a) {
        this.a = a;
    }

    @Override
    public Integer call() {
        return a * a;
    }
}
