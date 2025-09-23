package practicesessions.sept_22.concurrency;
// Problem:
// Simulate a background logger using a daemon thread that prints log messages indefinitely.

public class BackgroundLogger1 {
    public static void main(String[] args) throws InterruptedException {
        // Background logger thread (daemon)
        Thread logger = new Thread(() -> {
            int logCount = 1;
            try {
                while (true) { // run indefinitely
                    System.out.println("Logging message #" + logCount++);
                    Thread.sleep(1000); // simulate 1 second between log entries
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Set logger thread as daemon BEFORE starting it
        logger.setDaemon(true);
        logger.setName("BackgroundLoggerThread");
        logger.start();

        // Simulate a short main task (2 seconds)
        Thread.sleep(2000);

        System.out.println("Main task finished. Exiting...");
        // After main exits, JVM stops the daemon thread automatically
    }
}
