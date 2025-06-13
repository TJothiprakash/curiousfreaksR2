package graphs;

import java.util.*;

public class Test {

    //practice bfs
    // what are need queue, vis array, list to store the bfs
    public List<Integer> bfs(int V, List<List<Integer>> adjlist) {
        List<Integer> bfs = new ArrayList<>();
        Boolean[] visited = new Boolean[V];
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            Integer node = q.poll();
            System.out.print(node + " ");
            bfs.add(node);
            for (int i : adjlist.get(node)) {
                if (!visited[i] == false) {
                    visited[i] = true;
                    q.add(i);
                }

            }

        }
        return bfs;

    }

    public List<Integer> dfs(Integer v, List<List<Integer>> adjlist) {
        List<Integer> dfs = new ArrayList<>();
        Boolean[] visited = new Boolean[v];
        Arrays.fill(visited, false);
        dfshelper(0, dfs, visited, adjlist);
        return dfs;

    }

    private static void dfshelper(int i, List<Integer> dfs, Boolean[] visited, List<List<Integer>> adjlist) {
        visited[i] = true;
        dfs.add(i);
        for (int j : adjlist.get(i)) {
            if (!visited[j]) {
                dfshelper(j, dfs, visited, adjlist);
            }
        }
    }

}
