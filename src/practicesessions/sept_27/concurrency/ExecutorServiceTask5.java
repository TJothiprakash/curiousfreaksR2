package practicesessions.sept_27.concurrency;

import java.util.concurrent.*;

public class ExecutorServiceTask5 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newCachedThreadPool();

        FutureTask<String>[] tasks = new FutureTask[5];

        // Create and submit 5 tasks using explicit FutureTask
        for (int i = 0; i < 5; i++) {
            final int taskId = i + 1;

            Callable<String> callable = new Callable<>() {
                @Override
                public String call() throws Exception {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("Task " + taskId + " running in " + threadName);
                    Thread.sleep(1000);
                    return "Task " + taskId + " executed by " + threadName;
                }
            };

            tasks[i] = new FutureTask<>(callable);
            executor.execute(tasks[i]);
        }

        // Retrieve results
        for (FutureTask<String> task : tasks) {
            System.out.println(task.get());
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("All tasks completed. Main thread exiting.");
    }
}

