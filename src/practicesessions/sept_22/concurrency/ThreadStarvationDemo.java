package practicesessions.sept_22.concurrency;

public class ThreadStarvationDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable highPriorityTask = () -> {
            for (int i = 1; i <= 1000; i++) {
                // Busy work
                System.out.println("high  :" + i);
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        };

        Runnable lowPriorityTask = () -> {
            for (int i = 1; i <= 1000; i++) {
                // Busy work
                System.out.println("low L:" + i);
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        };

        Thread high = new Thread(highPriorityTask, "HighPriorityThread");
        Thread low = new Thread(lowPriorityTask, "LowPriorityThread");

        high.setPriority(Thread.MAX_PRIORITY);
        low.setPriority(Thread.MIN_PRIORITY);

        high.start();
        high.join();
        low.start();
    }
}
/*Exercise 5: Thread priorities on different OS platforms

How it behaves:

Windows:

JVM maps Java thread priorities (1–10) to Windows thread priorities.

Higher-priority threads get more CPU time, low-priority threads can be starved in extreme cases.

Linux / UNIX:

Linux uses time-slicing with nice values.

Java priorities are hints, not guarantees.

The OS scheduler may largely ignore small priority differences unless using real-time scheduling.

macOS:

Similar to Linux; thread priority hints are not strictly enforced.

Conclusion:

Thread priorities are hints to the scheduler, not guarantees.

Behavior varies by OS and JVM implementation.

For critical tasks, do not rely on priority for correctness; use proper synchronization and task management instead.*/