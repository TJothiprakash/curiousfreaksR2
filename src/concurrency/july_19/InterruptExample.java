package concurrency.july_19;
public class InterruptExample {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Working... " + System.currentTimeMillis());
                try {
                    Thread.sleep(500); // simulate work
                } catch (InterruptedException e) {
                    System.out.println("Interrupted during sleep. Exiting...");
                    break;
                }
            }
            System.out.println("Thread exiting cleanly.");
        };

        Thread t1 = new Thread(task);
        t1.start();
          

        // Wait for 3 seconds then interrupt
        Thread.sleep(3000);
        System.out.println("Main thread interrupting t1...");
        t1.interrupt();
    }
}
