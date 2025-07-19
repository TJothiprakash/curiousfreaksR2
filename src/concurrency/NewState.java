package concurrency;

public class NewState implements Runnable {
    @Override
    public void run() {
        System.out.println("runnning from new state class ");
    }
}
