package practicesessions.sept_27.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTask1 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 5 Runnable tasks
        for (int i = 1; i <= 5; i++) {
            int taskId = i; // capture task id for clarity
            executor.submit(() -> {
                System.out.println("Task " + taskId + " running in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " completed in " + Thread.currentThread().getName());
            });
        }

        // Shut down executor (gracefully)
        executor.shutdown();

        try {
            // Wait until all tasks are finished or 5 seconds max
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown...");
                executor.shutdownNow(); // kill remaining tasks
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("All tasks finished. Main thread exiting.");
    }
}

