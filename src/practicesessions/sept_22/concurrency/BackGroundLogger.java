package practicesessions.sept_22.concurrency;

public class BackGroundLogger {

    public static void main(String[] args) throws InterruptedException {


        Thread daemon = new Thread(() -> {
            int count = 0;
            while (true) {
                System.out.println("this is from daemon thread  " + count++);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        daemon.setDaemon(true);
        daemon.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main : "+ i);
            Thread.sleep(700);
        }
        System.out.println("main thread exits......");
    }

}
