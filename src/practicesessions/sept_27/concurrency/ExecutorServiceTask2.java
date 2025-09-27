package practicesessions.sept_27.concurrency;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTask2 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        final int maxRuns = 15;
        final Runnable task = new Runnable() {
            private int count = 0;

            @Override
            public void run() {
                count++;
                System.out.println("[" + count + "] Current time: " + LocalTime.now());

                if (count >= maxRuns) {
                    System.out.println("Reached " + maxRuns + " executions. Shutting down...");
                    scheduler.shutdown();
                }
            }
        };

        // Start immediately, then repeat every 1 second
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

        try {
            scheduler.awaitTermination(10, TimeUnit.SECONDS); // wait for tasks to finish
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("Main thread exiting.");
    }

}
