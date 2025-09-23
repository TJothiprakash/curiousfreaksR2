package practicesessions.sept_22.concurrency;

public class DaemonAfterStartDemo {
    public static void main(String[] args) {
        Thread longRunning = new Thread(() -> {
            while (true) {
                System.out.println("Long-running thread is working...");
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            }
        });

        longRunning.start(); // Thread is started

        try {
            // Attempt to set it as daemon AFTER it started
            longRunning.setDaemon(true); // ❌ Not allowed
        } catch (IllegalThreadStateException e) {
            System.out.println("Cannot set daemon after start: " + e);
        }

        System.out.println("Main thread finished");
    }
}
