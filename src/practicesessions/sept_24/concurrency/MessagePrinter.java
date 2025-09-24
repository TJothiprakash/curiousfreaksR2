package practicesessions.sept_24.concurrency;
public class MessagePrinter implements Runnable {
    @Override
    public void run() {
        System.out.println("true = " + true);
        System.out.println(Thread.currentThread().getName());
        // TODO: Print current thread name and message 5 times with some delay
        for (int i = 0; i < 5; i++) {
            System.out.println("thsisis from message printer thread ");
        }
    }
}