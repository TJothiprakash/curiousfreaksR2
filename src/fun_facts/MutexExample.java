package fun_facts;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private int counter = 0;
    private final Lock lock = new ReentrantLock(); // Mutex

    public void increment() {
        lock.lock();  // Acquire the lock
        try {
            counter++;
            System.out.println(Thread.currentThread().getName() + " Counter: " + counter);
        } finally {
            lock.unlock();  // Release the lock
        }
    }
}

public class MutexExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = resource::increment;

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}
