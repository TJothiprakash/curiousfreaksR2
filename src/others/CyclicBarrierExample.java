package others;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        int numThreads = 3;
        CyclicBarrier barrier = new CyclicBarrier(numThreads, () -> System.out.println("All threads reached the barrier!"));
        for (int i = 0; i < numThreads; i++) {
            new Thread(new Worker(barrier)).start();
        }
    }
    static class Worker implements Runnable {
        private final CyclicBarrier barrier;
        Worker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is working...");
            try {
                Thread.sleep(1000); // Simulating work
                System.out.println(Thread.currentThread().getName() + " reached the barrier");
                barrier.await(); // Wait at the barrier
                System.out.println(Thread.currentThread().getName() + " passed the barrier!");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
