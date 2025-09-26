package practicesessions.sept_26.concurrency;

public class VolatileSingleton {
    private static volatile VolatileSingleton instance;

    private VolatileSingleton() {
        System.out.println("Singleton instance created.");
    }

    public static VolatileSingleton getInstance() {
        if (instance == null) { // First check (no lock)
            synchronized (VolatileSingleton.class) {
                if (instance == null) { // Second check (with lock)
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            VolatileSingleton s = VolatileSingleton.getInstance();
            System.out.println("Got instance: " + s);
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

