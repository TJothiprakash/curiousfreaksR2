package graphs.practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// bfs
public class Practice {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(0).add(4);
        adj.get(4).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(1).add(3);
        adj.get(3).add(1);

        Practice sl = new Practice();
        ArrayList<Integer> ans = sl.bfs(adj, 0);
        int n = ans.size();
        for (int i = 0; i < n; i++) {
            System.out.print(ans.get(i) + " ");
        }
    }

    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adjlist, int source) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjlist.size()];
        ArrayList<Integer> bfs = new ArrayList<>();
        visited[source] = true;
        queue.add(source);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfs.add(node);
            /* System.out.print(node + " ");*/
            for (int neighbour : adjlist.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        return bfs;
    }
}
