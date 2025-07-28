package others;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) {
        int numWorkers = 3;
        CountDownLatch latch = new CountDownLatch(numWorkers);
        for (int i = 0; i < numWorkers; i++) {
            new Thread(new Worker(latch)).start();
        }
        try {
            latch.await(); // Wait for all workers to finish
            System.out.println("All workers finished. Main thread proceeds.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static class Worker implements Runnable {
        private final CountDownLatch latch;
        Worker(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is working...");
            try {
                Thread.sleep(1000); // Simulating work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished work.");
            latch.countDown(); // Decrease the latch count
        }
    }
}