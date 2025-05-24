package concurrency;
import java.util.LinkedList;
import java.util.Queue;
class SimpleBlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;
    private final Object lock = new Object(); // Use a dedicated lock
    public SimpleBlockingQueue(int capacity) {
        this.capacity = capacity;
    }
    public void put(T item) throws InterruptedException {
        synchronized (lock) {
            // WAIT while the queue is full
            while (queue.size() == capacity) {
                System.out.println(Thread.currentThread().getName() + " waiting - Queue FULL");
                lock.wait(); // Releases lock, goes to sleep
            }
            queue.offer(item);
            System.out.println(Thread.currentThread().getName() + " produced " + item);
            lock.notifyAll(); // Notify potentially waiting consumers
        }
    }
    public T take() throws InterruptedException {
        synchronized (lock) {
            // WAIT while the queue is empty
            while (queue.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " waiting - Queue EMPTY");
                lock.wait(); // Releases lock, goes to sleep
            }
            T item = queue.poll();
            System.out.println(Thread.currentThread().getName() + " consumed " + item);
            lock.notifyAll(); // Notify potentially waiting producers
            return item;
        }
    }
}
public class WaitNotifyDemo {
    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        // Producer Thread
        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    Thread.sleep(100); // Simulate production time
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Producer");
        // Consumer Thread
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Integer item = queue.take();
                    Thread.sleep(500); // Simulate consumption time
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }, "Consumer");
        producer.start();
        consumer.start();
    }
}
