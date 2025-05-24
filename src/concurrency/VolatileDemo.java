package concurrency;
class StatusFlag {
    // Guarantees visibility across threads
    private volatile boolean stopRequested = false;
    public void requestStop() {
        stopRequested = true; // Write is made visible quickly
    }
    public void runWork() {
        while (!stopRequested) { // Read reliably sees the latest value
            // Do some work...
            System.out.println("Working...");
            try { Thread.sleep(500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        System.out.println("Stopping work.");
    }
}
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        StatusFlag status = new StatusFlag();
        Thread workerThread = new Thread(status::runWork);
        workerThread.start();
        Thread.sleep(2000); // Let it work for a bit
        System.out.println("Main thread requesting stop...");
        status.requestStop(); // Signal the worker thread
        workerThread.join(); // Wait for worker to finish
        System.out.println("Main thread finished.");
    }
}