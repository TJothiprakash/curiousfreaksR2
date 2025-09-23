package practicesessions.sept_22.concurrency;
// Problem:
// Demonstrate Java thread priorities and observe their effect (not guaranteed).

public class ThreadPriorityTest {
    public static void main(String[] args) {
        Runnable counter = () -> {
            String threadName = Thread.currentThread().getName();
            for (int i = 1; i <= 5000; i++) {
                if (i % 1000 == 0) {
                    System.out.println(threadName + " reached count " + i);
                }
            }
        };

        Thread maxPriorityThread = new Thread(counter, "MAX_PRIORITY_THREAD");
        Thread normPriorityThread = new Thread(counter, "NORM_PRIORITY_THREAD");
        Thread minPriorityThread = new Thread(counter, "MIN_PRIORITY_THREAD");

        // Set thread priorities
        maxPriorityThread.setPriority(Thread.MAX_PRIORITY); // 10
        normPriorityThread.setPriority(Thread.NORM_PRIORITY); // 5
        minPriorityThread.setPriority(Thread.MIN_PRIORITY); // 1

        // Start threads
        maxPriorityThread.start();
        normPriorityThread.start();
        minPriorityThread.start();
    }
}
