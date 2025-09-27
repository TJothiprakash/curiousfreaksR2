package practicesessions.sept_27.concurrency;

import java.util.concurrent.*;
import java.util.*;

public class FutureCallableExercises {

    public static void main(String[] args) {
        // Executor for all tasks
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // ---------------- Task 3: Exception Handling in Callable ----------------
        Callable<String> exceptionTask = () -> {
            throw new RuntimeException("Intentional Exception from Callable");
        };

        Future<String> futureException = executor.submit(exceptionTask);

        try {
            String result = futureException.get();
            System.out.println("Task 3 result: " + result);
        } catch (ExecutionException e) {
            System.out.println("Task 3 caught exception: " + e.getCause());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Task 3 interrupted");
        }

        // ---------------- Task 4: Check if Future is Done or Cancelled ------------
        Callable<String> shortTask = () -> {
            Thread.sleep(1000);
            return "Task 4 completed";
        };

        Future<String> futureShort = executor.submit(shortTask);

        while (!futureShort.isDone()) {
            System.out.println("Task 4 is done? " + futureShort.isDone() + ", is cancelled? " + futureShort.isCancelled());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            System.out.println("Task 4 result: " + futureShort.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 4 failed: " + e.getMessage());
        }

        // ---------------- Task 5: Multiple Callables with invokeAll ----------------
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            tasks.add(() -> "Task 5 result from task " + taskId);
        }

        try {
            List<Future<String>> futures = executor.invokeAll(tasks);
            for (Future<String> f : futures) {
                System.out.println(f.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Task 5 failed: " + e.getMessage());
        }

        executor.shutdown();
    }
}

