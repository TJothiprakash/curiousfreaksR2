package concurrency.july_19;

public class TimedingState {
    public static void main(String[] args) throws InterruptedException {
        DemoTimeingRunnable runnable = new DemoTimeingRunnable();
        Thread t1 = new Thread(runnable);
        t1.start();
        t1.setName(" user thread");
        // The following sleep will give enough time for ThreadScheduler
        // to start processing of thread t1
        Thread.sleep(1000);
        System.out.println(t1.getState());
        System.out.println("main thread completed " + Thread.currentThread().getName());
    }
}

class DemoTimeingRunnable implements Runnable {
    @Override
    public void run() {
        try {
            long starttime = System.currentTimeMillis();
            System.out.println("starttime = " + starttime);
            ;
            Thread.sleep(5000);
            System.out.println("Use thread completed " + Thread.currentThread().getName());
            System.out.println("end time " + (System.currentTimeMillis() - starttime) / 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}