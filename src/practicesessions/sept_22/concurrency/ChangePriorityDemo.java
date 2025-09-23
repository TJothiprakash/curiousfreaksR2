package practicesessions.sept_22.concurrency;

public class ChangePriorityDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " running iteration " + i
                        + " with priority " + Thread.currentThread().getPriority());
                try { Thread.sleep(200); } catch (InterruptedException e) {}
            }
        };

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.setPriority(Thread.MIN_PRIORITY); // 1
        t2.setPriority(Thread.MAX_PRIORITY); // 10

        t1.start();
        t2.start();

        Thread.sleep(500); // Let threads run a bit

        System.out.println("Changing Thread-1 priority to MAX");
        t1.setPriority(Thread.MAX_PRIORITY); // change after start
        t2.setPriority(Thread.MIN_PRIORITY);
    }
}

/*Key points:

Thread-1 starts as low priority and then is changed to high.

Effect is not guaranteed immediately; it depends on JVM and OS scheduler.*/
