package practicesessions.sept_26.concurrency;
/*✅ Normally, without volatile:

Each thread may keep a local cached copy of a variable in CPU registers or thread-local memory.

If one thread changes the variable, other threads may not see the new value immediately.

✅ With volatile:

The variable is always read directly from main memory.

Any write to the variable is immediately flushed to main memory.

This means all threads see the most up-to-date value.

⚡ Important: What volatile does not do

It does not make compound operations atomic.
Example: count++ is not atomic even if count
 is volatile (that’s why we needed synchronized or AtomicInteger earlier).

It only ensures visibility, not atomicity.*/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        VolatileCounter volatileCounter = new VolatileCounter();

        System.out.println("Initial count: " + volatileCounter.getCount());

        volatileCounter.increment();
        System.out.println("After main thread increment: " + volatileCounter.getCount());

        // Create threads
        Thread t1 = new Thread(volatileCounter::increment);
        Thread t2 = new Thread(volatileCounter::increment);
        Thread t3 = new Thread(volatileCounter::increment);
        Thread t4 = new Thread(volatileCounter::increment);
        Thread t5 = new Thread(volatileCounter::increment);

        // Start threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for threads to finish
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println("Final count after all threads: " + volatileCounter.getCount());
    }
}

// Task 1: Create a volatile counter that multiple threads increment concurrently and verify visibility.
class VolatileCounter {
    private volatile int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
