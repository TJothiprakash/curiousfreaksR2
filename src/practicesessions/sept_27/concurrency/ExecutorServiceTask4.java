package practicesessions.sept_27.concurrency;

import java.util.concurrent.*;

public class ExecutorServiceTask4 {
    public static void main(String[] args) throws InterruptedException {
        int totalTasks = 8;
        CountDownLatch latch = new CountDownLatch(totalTasks);

        ScheduledExecutorService retryScheduler = Executors.newSingleThreadScheduledExecutor();

        // Custom handler that retries the ORIGINAL Runnable after 500ms
        RejectedExecutionHandler handler = (r, executor) -> {
            if (!executor.isShutdown() && !executor.isTerminating()) {
                retryScheduler.schedule(() -> {
                    try {
                        System.out.println("Task rejected. Retrying...");
                        executor.execute(r);
                        System.out.println("Task re-submitted.");
                    } catch (RejectedExecutionException e) {
                        System.out.println("Task still rejected.");
                    }
                }, 500, TimeUnit.MILLISECONDS);
            } else {
                System.out.println("Executor is shutting down. Dropping task.");
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 2,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                handler
        );

        // Submit tasks
        for (int i = 1; i <= totalTasks; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Task " + taskId + " started in " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // simulate work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Task " + taskId + " finished in " + Thread.currentThread().getName());
                latch.countDown();
            });
        }

        // Wait for all tasks to finish before shutting down
        latch.await();
        executor.shutdown();
        retryScheduler.shutdown();

        System.out.println("All tasks completed. Main thread exiting.");
    }
}

