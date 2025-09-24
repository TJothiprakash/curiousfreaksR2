package practicesessions.sept_24.concurrency;

import java.util.concurrent.Callable;

public class CountdownThread extends Thread {
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("name = " + name);
        // TODO: Implement countdown from 10 to 0 with sleep interval
        for (int i = 0; i < 10; i++) {
            System.out.println(i + " ");
        }
    }
}

class Main {
    static void main() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println("name = " + name);
        Thread thread = new Thread(new MessagePrinter());
        thread.start();
        String name1 = Thread.currentThread().getName();
        System.out.println("name1 = " + name1);
        CountdownThread countdownThread = new CountdownThread();
        countdownThread.start();

        Callable<Integer> result = new FactorialCalculator(5);
        System.out.println("result is " + result.call());

    }
}


