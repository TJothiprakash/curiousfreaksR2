package concurrency;

class SafeCounterBlock {
    private int count = 0;
    private final Object lock = new Object(); // Often a dedicated lock object
    public void increment() {
        // Other non-critical code can go here...
        // Acquire lock only for the critical section
        synchronized (lock) { // Or synchronized(this)
            count++;
        } // Lock released automatically here
        // Other non-critical code...
    }
    public int getCount() {
        synchronized (lock) { // Ensure consistent reading
            return count;
        }
    }
}
// ... (Use SafeCounterBlock in the RaceConditionDemo structure)

// Output will also reliably be 20000
class UnsafeCounter {
    private int count = 0;
    public void increment() {
        // This looks simple, but it's NOT atomic!
        // 1. Read current value of count
        // 2. Add 1 to the value
        // 3. Write the new value back to count
        count++;
    }
    public int getCount() {
        return count;
    }
}
class SafeCounterMethod {
    private int count = 0;
    // Acquire lock on 'this' object before executing
    public synchronized void increment() {
        count++;
    } // Lock released automatically here
    public synchronized int getCount() {
        // Also synchronized to ensure reading the most up-to-date value
        return count;
    }
}
// ... (Use SafeCounterMethod in the RaceConditionDemo structure)
// Now the output will reliably be 20000
public class RaceConditionDemo {
    public static void main(String[] args) throws InterruptedException {
        UnsafeCounter counter2 = new UnsafeCounter();
        SafeCounterMethod counter = new SafeCounterMethod();
        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        // Wait for both threads to finish
        //t1.join();
        t2.join();
        // Expected: 20000. Actual: Often less! Why?
        System.out.println("Final count: " + counter.getCount());
    }
}
