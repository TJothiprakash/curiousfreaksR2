package concurrency.july_19;

import concurrency.NewState;

public class Concurrency_Practice {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new NewState();
        Thread t = new Thread(runnable);
        t.start();
        t.join();
        System.out.println(t.getState());
    }
}
