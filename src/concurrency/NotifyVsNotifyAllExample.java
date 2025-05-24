package concurrency;
class SharedResource {
    synchronized void waitMethod() {
        try {
            System.out.println(Thread.currentThread().getName() + " is waiting...");
            wait(); // Thread will wait and release the monitor
            System.out.println(Thread.currentThread().getName() + " is resumed!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized void notifyMethod() {
        //notify(); // Only one waiting thread is notified
         notifyAll(); // Uncomment this to notify all waiting threads
        System.out.println("Notified one thread");
    }
}
public class NotifyVsNotifyAllExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Runnable waitingTask = () -> resource.waitMethod();
        // Create and start 3 threads that wait on the resource
        Thread t1 = new Thread(waitingTask, "Thread-1");
        Thread t2 = new Thread(waitingTask, "Thread-2");
        Thread t3 = new Thread(waitingTask, "Thread-3");
        t1.start();
        t2.start();
        t3.start();
        try {
            Thread.sleep(1000); // Give time for all threads to start waiting
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Notify one thread
        new Thread(() -> resource.notifyMethod()).start();
    }
}