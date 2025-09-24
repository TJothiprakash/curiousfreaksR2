package practicesessions.sept_24.concurrency;

import java.util.concurrent.ExecutionException;

public class ThreadJoinExample {
    public void startAndJoinThreads() throws InterruptedException {
        // TODO: Create and start multiple threads, then join them in main thread
        Thread t1 = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " starts execution");
                    System.out.println("hihello howa re you!!!");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "  finishes execution");
                }
        );

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starts execution");
            System.out.println("hey thsi is GOPAL !!!");
            System.out.println(Thread.currentThread().getName() + "  finishes execution");
        });
        t1.start();
        t1.join();
        t2.start();


    }

    static void main() throws InterruptedException {
        new ThreadJoinExample().startAndJoinThreads();;
    }
}

