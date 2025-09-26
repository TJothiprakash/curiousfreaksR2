package practicesessions.sept_25;

public class SynchronizedTasks {

    // ---------------- Task 1 ----------------
    // Thread-safe counter
    public static class Counter {
        private int count = 0;

        public synchronized int increment() {
            count++;          // safely increment
            return count;     // return updated value
        }

        public synchronized int getCount() {
            return count;     // optional getter
        }
    }

    // ---------------- Task 2 ----------------
    // Thread-safe add to shared list
    private final Object lock = new Object();
    private java.util.List<String> sharedList = new java.util.ArrayList<>();

    public void addItem(String item) {
        synchronized (lock) {
            sharedList.add(item); // atomic add
        }
    }

    public java.util.List<String> getSharedList() {
        synchronized (lock) {
            return new java.util.ArrayList<>(sharedList); // return copy to avoid outside modification
        }
    }

    // ---------------- Task 3 ----------------
    // Synchronized block to update two related variables atomically
    private int x = 0;
    private int y = 0;

    public void updateXY(int newX, int newY) {
        synchronized (this) {
            x = newX;
            y = newY;
        }
    }

    public int[] getXY() {
        synchronized (this) {
            return new int[]{x, y};
        }
    }

    // ---------------- Task 4 ----------------
    // Swap elements safely in an array
    private final Object arrayLock = new Object();
    private int[] arr = new int[10]; // default initialized with 0

    public void swap(int i, int j) {
        synchronized (arrayLock) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public int[] getArray() {
        synchronized (arrayLock) {
            return arr.clone(); // return copy
        }
    }

    // ---------------- Task 5 ----------------
    // Synchronized bank account withdrawal
    private int balance = 1000;

    public synchronized boolean withdraw(int amount) {
        if (amount <= balance) {
            balance -= amount; // atomic check + update
            return true;
        } else {
            return false; // insufficient funds
        }
    }

    public synchronized int getBalance() {
        return balance;
    }

    // ---------------- Optional main for testing ----------------
    public static void main(String[] args) throws InterruptedException {
        SynchronizedTasks tasks = new SynchronizedTasks();

        // Task 1 Test
        Counter counter = new Counter();
        Thread t1 = new Thread(() -> System.out.println("Counter: " + counter.increment()));
        Thread t2 = new Thread(() -> System.out.println("Counter: " + counter.increment()));
        t1.start(); t2.start(); t1.join(); t2.join();

        // Task 2 Test
        tasks.addItem("A");
        tasks.addItem("B");
        System.out.println("Shared List: " + tasks.getSharedList());

        // Task 3 Test
        tasks.updateXY(5, 10);
        int[] xy = tasks.getXY();
        System.out.println("x=" + xy[0] + ", y=" + xy[1]);

        // Task 4 Test
        tasks.arr[0] = 1; tasks.arr[1] = 2;
        tasks.swap(0, 1);
        System.out.println("Array after swap: " + java.util.Arrays.toString(tasks.getArray()));

        // Task 5 Test
        System.out.println("Withdraw 500: " + tasks.withdraw(500));
        System.out.println("Remaining balance: " + tasks.getBalance());
    }
}
