package graphs.july_20;

import java.util.*;

public class ToposortPractice {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("args = " + args);
        System.out.println("args = " + Arrays.toString(args));
        System.out.printf("inside toposort main ");
    }

    public List<Integer> topoSort(int V, int[][] edges) {

        // build the adjlist
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }
        // visited array
        boolean[] visited = new boolean[V];

        // stack to store toposort
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < adjList.size(); i++) {


        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, adjList, stack);
            }
        }

        List<Integer> toposort = new ArrayList<>();
        while (!stack.isEmpty()) {
            toposort.add(stack.pop());
        }

        return toposort;
    }

    private void dfs(int node, boolean[] visited, List<List<Integer>> list, Stack<Integer> stack) {
        visited[node] = true;

        for (int neighbour : list.get(node)) {
            if (!visited[neighbour]) {
                dfs(neighbour, visited, list, stack);
            }

        }
        stack.push(node);

    }

    public boolean isSchedulePossible(int edges[][], int n, int p) {
        //   build adjlest
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v);
        }
        boolean[] visited = new boolean[n];
        boolean[] inpath = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (dfs2(i, visited, inpath, adjList)) return false;
            }
        }

        return true;
    }

    private boolean dfs2(int node, boolean[] visited, boolean[] inpath, List<List<Integer>> adjList) {
        visited[node] = true;
        inpath[node] = true;

        for (int neighbour : adjList.get(node)) {
            if (!visited[node] && dfs2(neighbour, visited, inpath, adjList)) return true;
            if (inpath[neighbour]) return true;
        }
        inpath[node] = false;
        return false;
    }


    public boolean canFinish(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

        for (int[] edge : edges)
            graph.get(edge[1]).add(edge[0]);  // v → u (u depends on v)

        boolean[] visited = new boolean[n];
        boolean[] onPath = new boolean[n];  // recursion stack
        List<Integer> topo = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (hasCycle(i, graph, visited, onPath, topo)) {
                    return false;
                }
            }
        }

        Collections.reverse(topo);
        System.out.println("Topological Order: " + topo);
        return true;
    }

    private boolean hasCycle(int node, List<List<Integer>> graph, boolean[] visited,
                             boolean[] onPath, List<Integer> topo) {
        visited[node] = true;
        onPath[node] = true;

        for (int nei : graph.get(node)) {
            if (!visited[nei]) {
                if (hasCycle(nei, graph, visited, onPath, topo))
                    return true;
            } else if (onPath[nei]) {
                return true; // found a cycle
            }
        }

        onPath[node] = false;
        topo.add(node);  // reverse postorder
        return false;
    }


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());

        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
        }

        int[] visited = new int[numCourses]; // 0 = unvisited, 1 = visiting, 2 = visited
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0) {
                if (!dfs(i, adj, visited, result)) {
                    return new int[0]; // cycle detected
                }
            }
        }

        Collections.reverse(result); // reverse post-order for topo sort
        return result.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int node, List<List<Integer>> adj, int[] visited, List<Integer> result) {
        visited[node] = 1; // mark as visiting

        for (int neighbor : adj.get(node)) {
            if (visited[neighbor] == 1) return false; // cycle
            if (visited[neighbor] == 0) {
                if (!dfs(neighbor, adj, visited, result)) return false;
            }
        }

        visited[node] = 2; // mark as visited
        result.add(node); // post-order
        return true;
    }

    //     eventual safe states
    public List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        int[] state = new int[V];  // 0 = unvisited, 1 = visiting, 2 = safe
        List<Integer> safeNodes = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            if (isSafe(i, adj, state)) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }

    private boolean isSafe(int node, List<List<Integer>> adj, int[] state) {
        if (state[node] == 1) return false;  // cycle detected
        if (state[node] == 2) return true;   // already marked safe

        state[node] = 1;  // mark as visiting
        for (int neighbor : adj.get(node)) {
            if (!isSafe(neighbor, adj, state)) {
                return false;
            }
        }

        state[node] = 2;  // mark as safe
        return true;
    }

    //     alien dictionary
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Set<Character> allChars = new HashSet<>();

        // Step 1: Initialize graph
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new ArrayList<>());
                allChars.add(c);
            }
        }

        // Step 2: Build edges
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return ""; // invalid case like ["abcd", "ab"]
            }
            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                char from = word1.charAt(j);
                char to = word2.charAt(j);
                if (from != to) {
                    graph.get(from).add(to);
                    break;
                }
            }
        }

        // Step 3: DFS Topological Sort with cycle detection
        Map<Character, Boolean> visited = new HashMap<>();
        Set<Character> visiting = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (char c : graph.keySet()) {
            if (!visited.containsKey(c)) {
                if (!dfs(c, graph, visited, visiting, result)) {
                    return "";
                }
            }
        }

        return result.reverse().toString();
    }

    private boolean dfs(char node, Map<Character, List<Character>> graph,
                        Map<Character, Boolean> visited, Set<Character> visiting,
                        StringBuilder result) {
        if (visiting.contains(node)) return false; // cycle
        if (visited.containsKey(node)) return true;

        visiting.add(node);

        for (char neighbor : graph.get(node)) {
            if (!dfs(neighbor, graph, visited, visiting, result)) {
                return false;
            }
        }

        visiting.remove(node);
        visited.put(node, true);
        result.append(node);
        return true;
    }


}

