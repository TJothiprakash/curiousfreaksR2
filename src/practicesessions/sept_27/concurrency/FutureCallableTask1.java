package practicesessions.sept_27.concurrency;

import java.util.concurrent.*;

public class FutureCallableTask1 {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello from Callable";
            }
        };

        // Submit the Callable to executor
        Future<String> future = executor.submit(callable);
        future.cancel(true);
        System.out.println("future.isCancelled() = " + future.isCancelled());
        // Get and print the result (blocks until done)
        String result = future.get();
        System.out.println(result);

        executor.shutdown();
    }
}
