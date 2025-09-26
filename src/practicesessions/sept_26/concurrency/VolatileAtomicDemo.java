package practicesessions.sept_26.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileAtomicDemo {
    // Volatile ensures visibility but not atomicity
    private volatile int counter = 0;

    // Non-atomic increment (just for demo)
    public void incrementNonAtomic() {
        counter++; // may lose updates
    }

    // Fixed version with AtomicInteger
    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    public void incrementAtomic() {
        atomicCounter.incrementAndGet(); // atomic operation
    }

    public int getCounter() {
        return counter;
    }

    public int getAtomicCounter() {
        return atomicCounter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileAtomicDemo demo = new VolatileAtomicDemo();

        // Non-atomic scenario
        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                demo.incrementNonAtomic();
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Non-atomic counter (expected 2000): " + demo.getCounter());

        // Atomic scenario
        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                demo.incrementAtomic();
            }
        };

        Thread t3 = new Thread(task2);
        Thread t4 = new Thread(task2);

        t3.start();
        t4.start();
        t3.join();
        t4.join();

        System.out.println("Atomic counter (expected 2000): " + demo.getAtomicCounter());
    }
}
