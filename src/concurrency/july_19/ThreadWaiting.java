package concurrency.july_19;

public class ThreadWaiting {
    public static void main(String[] args) {
        WaitingStateDemo waitingStateDemo = new WaitingStateDemo();

        Thread t1 = new Thread(waitingStateDemo);
        t1.setName("first-thread");
        t1.start();

        try {
            System.out.println("inside try block");
            // Sleep to give t1 time to enter waiting state
            Thread.sleep(500);
            System.out.println("t1 state after sleep = " + t1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WaitingStateDemo implements Runnable {
    static final Object lock = new Object();

    @Override
    public void run() {
        synchronized (lock) {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is going to wait...");
                lock.wait(2000); // Causes WAITING state
                System.out.println("Thread " + Thread.currentThread().getName() + " resumed...");
                System.out.println("lock released for " + Thread.currentThread().getName() + " ... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
