package practicesessions.sept_26.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileStateMachine {
    private volatile int state = 0; // INIT

    public void changeState(int newState) {
        state = newState;
        System.out.println("State changed to: " + state);
    }

    public int getState() {
        return state;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileStateMachine machine = new VolatileStateMachine();

        Thread t1 = new Thread(() -> {
            machine.changeState(1); // RUNNING
        });

        Thread t2 = new Thread(() -> {
            machine.changeState(2); // STOPPED
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Final state: " + machine.getState());
    }
}
