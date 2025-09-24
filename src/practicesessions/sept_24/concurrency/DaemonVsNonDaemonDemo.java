package practicesessions.sept_24.concurrency;

public class DaemonVsNonDaemonDemo {

    // ========== 1. Daemon vs Non-Daemon Threads ==========
    static class DaemonThreadExample {
        public static void runExample() {
            Thread daemon = new Thread(() -> {
                while (true) {
                    System.out.println("[Daemon] Running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            daemon.setDaemon(true);

            Thread nonDaemon = new Thread(() -> {
                while (true) {
                    System.out.println("[Non-Daemon] Running...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });

            daemon.start();
            nonDaemon.start();

            try {
                Thread.sleep(3000); // Let them run a bit
            } catch (InterruptedException ignored) {}
            System.out.println("Main thread finished Daemon vs Non-Daemon example.\n");
        }
    }

    // ========== 2. Daemon Monitoring Main Thread ==========
    static class ThreadMonitorDaemon extends Thread {
        private final Thread threadToMonitor;

        public ThreadMonitorDaemon(Thread thread) {
            this.threadToMonitor = thread;
            setDaemon(true);
        }

        @Override
        public void run() {
            while (threadToMonitor.isAlive()) {
                System.out.println("[Monitor Daemon] Main thread state: " + threadToMonitor.getState());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    break;
                }
            }
            System.out.println("[Monitor Daemon] Main thread finished. Exiting...");
        }
    }

    // ========== 3. Daemon Ends When Main Finishes ==========
    static class DaemonEndsExample {
        public static void runExample() throws InterruptedException {
            Thread daemon = new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("[Daemon Worker] Still running...");
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException ignored) {}
            });
            daemon.setDaemon(true);
            daemon.start();

            Thread.sleep(3000);
            System.out.println("Main finishes → Daemon ends abruptly.\n");
        }
    }

    // ========== 4. Non-Daemon Worker Continues ==========
    static class NonDaemonWorkerExample {
        public static void runExample() {
            Thread worker = new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    System.out.println("[Non-Daemon Worker] Processing task " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ignored) {}
                }
                System.out.println("[Non-Daemon Worker] Finished all tasks.");
            });
            worker.start();
            System.out.println("Main thread finished, but non-daemon keeps working.\n");
        }
    }

    // ========== 5. Exception Handling in Daemon vs Non-Daemon ==========
    static class ExceptionHandlingDaemon {
        public static void runExample() {
            Thread daemon = new Thread(() -> {
                try {
                    throw new RuntimeException("Daemon thread exception!");
                } catch (Exception e) {
                    System.out.println("[Daemon] Caught exception: " + e.getMessage());
                }
            });
            daemon.setDaemon(true);

            Thread nonDaemon = new Thread(() -> {
                try {
                    throw new RuntimeException("Non-Daemon thread exception!");
                } catch (Exception e) {
                    System.out.println("[Non-Daemon] Caught exception: " + e.getMessage());
                }
            });

            daemon.start();
            nonDaemon.start();

            try {
                daemon.join();
                nonDaemon.join();
            } catch (InterruptedException ignored) {}
            System.out.println("Exception handling demo finished.\n");
        }
    }

    // ========== MAIN ==========
    public static void main(String[] args) throws Exception {
        System.out.println("1️⃣ Daemon vs Non-Daemon Threads:");
        DaemonThreadExample.runExample();

        System.out.println("2️⃣ Daemon Monitoring Main Thread:");
        Thread mainThread = Thread.currentThread();
        ThreadMonitorDaemon monitor = new ThreadMonitorDaemon(mainThread);
        monitor.start();
        Thread.sleep(5000); // Let monitor observe for a while

        System.out.println("3️⃣ Daemon Ends When Main Finishes:");
        DaemonEndsExample.runExample();

        System.out.println("4️⃣ Non-Daemon Worker Example:");
        NonDaemonWorkerExample.runExample();
        Thread.sleep(6000); // Wait for worker to finish

        System.out.println("5️⃣ Exception Handling in Daemon vs Non-Daemon:");
        ExceptionHandlingDaemon.runExample();

        System.out.println("✅ All demos completed.");
    }
}
