package practicesessions.sept_25;
import java.util.LinkedList;
import java.util.Queue;

public class WaitNotifyTasks {

    // ---------------- Task 1 ----------------
    // Producer-Consumer scenario
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 5;

    public void produce(int item) throws InterruptedException {
        synchronized(queue) {
            while(queue.size() == capacity) {
                queue.wait(); // wait until space is available
            }
            queue.add(item);
            queue.notifyAll(); // notify consumers
        }
    }

    public int consume() throws InterruptedException {
        synchronized(queue) {
            while(queue.isEmpty()) {
                queue.wait(); // wait until item is available
            }
            int item = queue.remove();
            queue.notifyAll(); // notify producers
            return item;
        }
    }

    // ---------------- Task 2 ----------------
    // Simple blocking flag
    private boolean isFlagSet = false;

    public synchronized void waitForFlag() throws InterruptedException {
        while(!isFlagSet) {
            wait(); // wait until flag is true
        }
    }

    public synchronized void setFlag() {
        isFlagSet = true;
        notifyAll(); // wake all waiting threads
    }

    // ---------------- Task 3 ----------------
    // Countdown latch
    private int count = 3;

    public synchronized void await() throws InterruptedException {
        while(count > 0) {
            wait(); // wait until count reaches zero
        }
    }

    public synchronized void countDown() {
        if(count > 0) {
            count--;
            if(count == 0) {
                notifyAll(); // wake waiting threads when zero
            }
        }
    }

    // ---------------- Task 4 ----------------
    // Shared resource access control
    private boolean resourceAvailable = true;

    public synchronized void acquireResource() throws InterruptedException {
        while(!resourceAvailable) {
            wait(); // wait until resource is free
        }
        resourceAvailable = false; // acquire resource
    }

    public synchronized void releaseResource() {
        resourceAvailable = true; // release resource
        notifyAll(); // notify waiting threads
    }

    // ---------------- Task 5 ----------------
    // Turn-based system for two threads
    private boolean isThread1Turn = true;

    public synchronized void waitForTurn(boolean thread1) throws InterruptedException {
        while(thread1 != isThread1Turn) {
            wait(); // wait if not your turn
        }
    }

    public synchronized void toggleTurn() {
        isThread1Turn = !isThread1Turn; // switch turn
        notifyAll(); // notify waiting threads
    }

    // ---------------- Optional main for testing ----------------
    public static void main(String[] args) {
        WaitNotifyTasks tasks = new WaitNotifyTasks();

        // Task 1 Test: Producer-Consumer
        Thread producer = new Thread(() -> {
            try {
                for(int i=1; i<=10; i++) {
                    tasks.produce(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch(Exception e) { e.printStackTrace(); }
        });

        Thread consumer = new Thread(() -> {
            try {
                for(int i=1; i<=10; i++) {
                    int item = tasks.consume();
                    System.out.println("Consumed: " + item);
                    Thread.sleep(150);
                }
            } catch(Exception e) { e.printStackTrace(); }
        });

        producer.start();
        consumer.start();

        // Other tasks can be tested similarly using small threads
    }
}
