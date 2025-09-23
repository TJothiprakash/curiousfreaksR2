package practicesessions.sept_22.concurrency;// Problem:
// Demonstrate difference between daemon and non-daemon threads.

public class DaemonVsNonDaemon {
    public static void main(String[] args) {
        // Daemon thread
        Thread daemon = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("Daemon thread running: iteration " + i);
                    Thread.sleep(500); // 500ms
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        daemon.setDaemon(true); // Set daemon before starting

        // Non-daemon (user) thread
        Thread userThread = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("User thread running: iteration " + i);
                    Thread.sleep(500); // 500ms
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        daemon.start();
        userThread.start();
        System.out.println("Main thread done.");
    }
}
