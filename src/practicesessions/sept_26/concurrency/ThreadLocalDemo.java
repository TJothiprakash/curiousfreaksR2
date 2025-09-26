package practicesessions.sept_26.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Supplier;


/*Why people use ThreadLocal IDs

ThreadLocal IDs (or any thread-local values) are not about uniquely
identifying threads globally, they’re for thread-specific data or context.

Examples:

Use Case	Why ThreadLocal ID / value is needed
Request tracing in web servers	Each thread handling a request gets a request ID stored in ThreadLocal → used in logs, exceptions, and tracing
Caching	Each thread has its own cache object (e.g., database connections, buffers, SimpleDateFormat) to avoid synchronization
Session context	Each thread handling a user request stores user info (like UserID, locale, roles) in ThreadLocal so downstream code can access it without passing it everywhere
Metrics / counters	Per-thread counters for statistics, e.g., counting processed tasks per thread

Key idea:

It’s not about the thread itself, it’s about data the thread “owns” during execution.

You want this data to be isolated to that thread, so no other thread can modify it.*/
public class ThreadLocalDemo {
}

// ---------------- Task 1 ----------------
// ThreadLocal variable that stores a unique ID per thread
class ThreadLocalId {
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<>();

    public static void setId(int id) {
        threadId.set(id);
    }

    public static int getId() {
        return threadId.get();
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            int id = (int) (Math.random() * 1000);
            ThreadLocalId.setId(id);
            System.out.println(Thread.currentThread().getName() + " has ID: " + ThreadLocalId.getId());
        };

        new Thread(task, "Worker-1").start();
        new Thread(task, "Worker-2").start();
    }
}

// ---------------- Task 2 ----------------
// Use ThreadLocal to store SimpleDateFormat instances to avoid concurrency issues
class ThreadLocalDateFormat {
    private static final ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static String formatDate(Date date) {
        return dateFormat.get().format(date);
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            String formatted = ThreadLocalDateFormat.formatDate(new Date());
            System.out.println(Thread.currentThread().getName() + " -> " + formatted);
        };

        new Thread(task, "T1").start();
        new Thread(task, "T2").start();
    }
}

// ---------------- Task 3 ----------------
// Implement a ThreadLocal cache for expensive object reuse per thread
 class ThreadLocalCache<T> {
    private final ThreadLocal<T> cache;

    public ThreadLocalCache(Supplier<T> supplier) {
        cache = ThreadLocal.withInitial(supplier);
    }

    public T get() {
        return cache.get();
    }

    public static void main(String[] args) {
        ThreadLocalCache<StringBuilder> cache = new ThreadLocalCache<>(StringBuilder::new);

        Runnable task = () -> {
            StringBuilder sb = cache.get();
            sb.append(Thread.currentThread().getName()).append(" cached object");
            System.out.println(sb.toString());
        };

        new Thread(task, "Thread-A").start();
        new Thread(task, "Thread-B").start();
    }
}

// ---------------- Task 4 ----------------
// Demonstrate memory leak risk and fix with proper cleanup
class ThreadLocalMemoryLeakDemo {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void set(String value) {
        threadLocal.set(value);
    }

    public static void clear() {
        threadLocal.remove(); // important cleanup
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            ThreadLocalMemoryLeakDemo.set("Data for " + Thread.currentThread().getName());
            System.out.println("Using: " + threadLocal.get());
            ThreadLocalMemoryLeakDemo.clear();
        };

        new Thread(task, "Worker-1").start();
        new Thread(task, "Worker-2").start();
    }
}

// ---------------- Task 5 ----------------
// Create a ThreadLocal with inheritance using InheritableThreadLocal
class InheritableThreadLocalExample {
    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void set(String value) {
        inheritableThreadLocal.set(value);
    }

    public static String get() {
        return inheritableThreadLocal.get();
    }

    public static void main(String[] args) {
        InheritableThreadLocalExample.set("Parent-Value");

        Thread child = new Thread(() -> {
            System.out.println("Child thread inherited value: " + InheritableThreadLocalExample.get());
        });

        child.start();
    }
}
