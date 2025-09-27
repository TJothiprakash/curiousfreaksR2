package practicesessions.sept_26.dsa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoursesSchedule {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prereq : prerequisites) {
            int from = prereq[1]; // prerequisite
            int to = prereq[0];   // dependent
            graph.get(from).add(to);
        }

        Set<Integer>[] reachable = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            reachable[i] = new HashSet<>();
        }

        for (int i = 0; i < numCourses; i++) {
            dfs(i, graph, reachable,visited);
        }
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            result.add(reachable[u].contains(v));
        }
        return result;

    }

    void dfs(int cur, List<List<Integer>> graph, Set<Integer>[] reachable, boolean[] visited) {
        if (visited[cur]) return; // already computed
        visited[cur] = true;

        for (int next : graph.get(cur)) {
            reachable[cur].add(next);
            dfs(next, graph, reachable, visited);
            reachable[cur].addAll(reachable[next]);
        }
    }


}
