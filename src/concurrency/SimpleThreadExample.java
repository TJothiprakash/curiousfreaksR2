package concurrency;
// Option 1: Implementing the Runnable interface (preferred)
// =========================================================
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task running in thread: " + Thread.currentThread().getName());
        // Do some work here...
    }
}
// Option 2: Extending the Thread class
// =====================================
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running directly: " + Thread.currentThread().getName());
        // Do some work here...
    }
}
public class SimpleThreadExample {
    public static void main(String[] args) {
        // Using Runnable
        Thread taskThread = new Thread(new MyTask());
        taskThread.start(); // Don't call run() directly! Call start()
        // Using Thread subclass
        MyThread directThread = new MyThread();
        directThread.start();
        System.out.println("Main thread finished.");
        // Note: Output order isn't guaranteed! Threads run concurrently.
    }
}
