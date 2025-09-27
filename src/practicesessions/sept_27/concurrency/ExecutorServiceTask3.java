package practicesessions.sept_27.concurrency;
import java.util.concurrent.*;

public class ExecutorServiceTask3 {


    public static void main(String[] args) {
        // Custom RejectedExecutionHandler
        RejectedExecutionHandler handler = (r, executor) -> {
            System.out.println("Task " + r.toString() + " rejected. Queue is full!");
        };

        // ThreadPoolExecutor: core=2, max=2, queue capacity=2
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 2,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                handler
        );

        // Submit 6 tasks (more than pool + queue capacity = 4)
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println("Task " + taskId + " started in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " finished in " + Thread.currentThread().getName());
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread exiting.");
    }
}
