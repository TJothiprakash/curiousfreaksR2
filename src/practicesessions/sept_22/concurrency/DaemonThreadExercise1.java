package practicesessions.sept_22.concurrency;

public class DaemonThreadExercise1 {
    public static void main(String[] args) throws Exception {
        Thread daemonThread = new Thread(() -> {
            // TODO: Print a message every second indefinitely
            while (true){
                System.out.println(" inside daemon thred method ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // TODO: Set this thread as daemon
//        daemonThread.setDaemon(true);
        daemonThread.setDaemon(false);

        daemonThread.start();

        try {
            // TODO: Main thread sleeps for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("Main thread exiting...");
    }
}
