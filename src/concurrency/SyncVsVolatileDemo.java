package concurrency;

public class SyncVsVolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 200; j++) {
                    counter.increment(); // change this to test other versions
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        System.out.println("Final count: " + counter.getCount());
    }
}

class Counter {
    private int count = 0;
//    private volatile int count = 0; // Try volatile

    // ❌ Unsafe method (no sync)
    public void incrementUnsafe() {
        count = count + 1;
    }

    // ✅ Safe method (with synchronized)
    public synchronized void increment() {
        count++;
    }

    // ❌ Volatile does not protect compound ops like ++
    public void incrementVolatile() {
        count++; // Not atomic even if count is volatile
    }

    public int getCount() {
        return count;
    }
}
