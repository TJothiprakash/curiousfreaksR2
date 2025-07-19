package concurrency.july_19.executorframework;

import org.testng.util.TimeUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

//    Thread reusability, in particular, is very important. In a large-scale
//application, allocating and deallocating many thread objects creates a
//significant memory management overhead.
//With worker threads, we minimize the overhead caused by thread creation.

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(() -> {
            new TimeUtils.Task() {
                @Override
                public void execute() {
                    System.out.println("hello ");
                }
            };
        });

    }
}
