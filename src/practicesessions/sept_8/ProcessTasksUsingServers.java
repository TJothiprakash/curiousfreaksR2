package practicesessions.sept_8;

import java.util.*;

public class ProcessTasksUsingServers {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length, m = tasks.length;
        int[] result = new int[m];

        // Available servers: (weight, index)
        PriorityQueue<int[]> available = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]
        );
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        // Busy servers: (endTime, weight, index)
        PriorityQueue<int[]> busy = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? (a[1] == b[1] ? a[2] - b[2] : a[1] - b[1]) : a[0] - b[0]
        );

        long time = 0;  // current time
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i); // tasks arrive at i

            // Free up servers whose endTime ≤ time
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] s = busy.poll();
                available.offer(new int[]{s[1], s[2]});
            }

            if (available.isEmpty()) {
                // no server free, wait for earliest
                int[] s = busy.poll();
                time = s[0]; // jump time
                available.offer(new int[]{s[1], s[2]});
                // recheck freeing others at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    int[] s2 = busy.poll();
                    available.offer(new int[]{s2[1], s2[2]});
                }
            }

            // Assign task
            int[] chosen = available.poll();
            result[i] = chosen[1];
            busy.offer(new int[]{(int)(time + tasks[i]), chosen[0], chosen[1]});
        }

        return result;
    }

    // Runner
    public static void main(String[] args) {
        ProcessTasksUsingServers obj = new ProcessTasksUsingServers();
        int[] servers = {3, 3, 2};
        int[] tasks = {1, 2, 3, 2, 1, 2};
        System.out.println(Arrays.toString(obj.assignTasks(servers, tasks)));
    }
}
