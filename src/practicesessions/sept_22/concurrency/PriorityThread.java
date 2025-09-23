package practicesessions.sept_22.concurrency;

// Problem:
// Create threads with different priorities and observe their behavior.

public class PriorityThread extends Thread {

    public PriorityThread(String name, int priority) {
        super(name);
        // Set thread priority (must be between MIN_PRIORITY(1) and MAX_PRIORITY(10))
        this.setPriority(priority);
    }

    @Override
    public void run() {
        // Print thread name and its priority
        System.out.println("Thread Name: " + getName() + ", Priority: " + getPriority());
        for (int i = 1; i <= 5000; i++) {
            if (i % 1000 == 0) {
                System.out.println(getName() + " reached count " + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Create threads with different priorities
        PriorityThread maxPriorityThread = new PriorityThread("MAX_PRIORITY_THREAD", Thread.MAX_PRIORITY);
        PriorityThread normPriorityThread = new PriorityThread("NORM_PRIORITY_THREAD", Thread.NORM_PRIORITY);
        PriorityThread minPriorityThread = new PriorityThread("MIN_PRIORITY_THREAD", Thread.MIN_PRIORITY);

        // Start all threads
        maxPriorityThread.start();
        maxPriorityThread.join();
        normPriorityThread.start();
        minPriorityThread.start();
    }
}
