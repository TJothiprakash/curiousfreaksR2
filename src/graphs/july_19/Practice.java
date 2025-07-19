package graphs.july_19;

import java.util.*;


public class Practice {
    // shortest path algorithms

    public List<Integer> shortestPathinUnweightedGraph(int edges[][], int source, int destination) {
        // form the adj list
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        for (int[] i : edges) {
            int v = i[0];
            int u = i[1];
            adjList.computeIfAbsent(v, j -> new ArrayList<>()).add(u);
            adjList.computeIfAbsent(u, j -> new ArrayList<>()).add(v);

        }

        List<List<Integer>> adj = new ArrayList<>();
        int N = edges.length;
        for (int a = 0; a <= N; a++) {
            adj.add(new ArrayList<>());
        }

        for (int[] m : edges) {
            int u = m[0];
            int v = m[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // create parent array
        int parent[] = new int[maxNode + 1];
        Arrays.fill(parent, -1);
        parent[source] = -1;

        //use bfs
        boolean visited[] = new boolean[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            List<Integer> adjNodes = adjList.get(node);
            for (int t : adjNodes) {
                if (!visited[t]) {
                    visited[t] = true;
                    parent[t] = node;
                    queue.add(t);
                }
                if (t == destination) {
                    // You found the shortest path — can break BFS early.
                }
            }

        }

        // return the path
        int n = destination;
        List<Integer> path = new ArrayList<>();
        while (n != -1) {
            path.add(n);
            n = parent[n];
        }
        Collections.reverse(path);
        return path;
    }

    public List<Integer> directedAscyclicGraphwithWeights(int[][] edges, int source, int destination) {
        int maxNode = 0;
        for (int[] e : edges) {
            maxNode = Math.max(maxNode, Math.max(e[0], e[1]));
        }

        int parent[] = new int[maxNode + 1];
        boolean visited[] = new boolean[maxNode + 1];
        Queue<Integer> queue = new LinkedList<>();

        // Initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) {
            adjList.add(new ArrayList<>());
        }

        // Build directed graph
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjList.get(u).add(v); // Directed edge u -> v
        }

        // BFS setup
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : adjList.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    queue.add(neighbor);

                    if (neighbor == destination) {
                        // We found the destination, but continue to ensure shortest via BFS
                    }
                }
            }
        }

        // Reconstruct path from parent[]
        List<Integer> path = new ArrayList<>();
        if (!visited[destination]) {
            return path; // no path found
        }

        for (int at = destination; at != -1; at = parent[at]) {
            path.add(at);
        }

        Collections.reverse(path);
        return path;
    }


    public List<Integer> dijkstraAlgorithm(int[][] edges, int source) {
        // build the adjlist
        List<List<Pair<Number, Number>>> adjList = new ArrayList<>();

        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }
        for (int i = 0; i <= maxNode; i++) {
            adjList.add(new ArrayList<Pair<Number, Number>>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            adjList.get(u).add(new Pair<Number, Number>(v, weight));
            adjList.get(v).add(new Pair<Number, Number>(u, weight));
        }

        PriorityQueue<Pair<Integer, Integer>> pairPriorityQueue = new PriorityQueue<>();
        int[] distance = new int[maxNode + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
//using dist array , PQ and bfs find the min distance for all elemenets from the source node
        distance[source] = 0;
        pairPriorityQueue.add(new Pair<>(source, 0));
        while (!pairPriorityQueue.isEmpty()) {
            Pair temp = pairPriorityQueue.poll();
            // check distance is min if yes add else continue
            int u = temp.node;
            int distU = temp.weight;

            // Optimization: Skip processing if we already have a shorter distance recorded
            if (distU > distance[u]) continue;
            // Traverse neighbors
            for (Pair<Number, Number> neighbor : adjList.get(u)) {
                int v = neighbor.node;
                int weight = neighbor.weight;

                // Relaxation step
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pairPriorityQueue.add(new Pair<>(v, distance[v]));
                }


            }


        }
        List<Integer> result = new ArrayList<>();
        for (int d : distance) result.add(d);
        return result;

    }

    public int minKnightMoves(int[][] positions, int targetX, int targetY, int startX, int startY) {

        if (startX == targetX && startY == targetY) return 0;

        boolean[][] visited = new boolean[positions.length][positions[0].length];

        // Knight move directions
        int[] dx = {-2, -2, +2, +2, -1, -1, +1, +1};
        int[] dy = {+1, -1, +1, -1, -2, +2, -2, +2};

        class Position {
            int x, y, steps;

            public Position(int x, int y, int steps) {
                this.x = x;
                this.y = y;
                this.steps = steps;
            }
        }

        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(startX, startY, 0));
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            if (current.x == targetX && current.y == targetY) {
                return current.steps;
            }

            for (int i = 0; i < 8; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (newX >= 0 && newX < positions.length &&
                        newY >= 0 && newY < positions[0].length &&
                        positions[newX][newY] != 0 &&
                        !visited[newX][newY]) {

                    visited[newX][newY] = true;
                    queue.add(new Position(newX, newY, current.steps + 1));
                }
            }
        }

        return -1; // target is not reachable
    }


    private int shortestSourcetoDestination(int[][] grid, int targetX, int targetY) {
        // use bfs to find the shortest path queue, visited array, left, right, up, down,boundary check, if not visited
//        visit = true if src == des return else +1 add the element to the queue and continue
        int startX = 0;
        int startY = 0;
        if (startX == targetX && startY == targetY) return 0;// src = destination
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        class Position {
            int X, Y, steps;

            public Position(int x, int y, int steps) {
                this.X = x;
                this.Y = y;
                this.steps = steps;
            }
        }

        Queue<Position> queue = new LinkedList<>();
        visited[startX][startY] = true;
        queue.add(new Position(startX, startY, 0));

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int currX = current.X;
            int currY = current.Y;
            int currSteps = current.steps;

            int[] dx = {+1, -1, 0, 0};
            int[] dy = {0, 0, +1, -1};

            for (int i = 0; i < 4; i++) {
                int newX = currX + dx[i];
                int newY = currY + dy[i];
                if (newX == targetX && newY == targetY) return currSteps + 1;

                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && grid[newX][newY] != 0 && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new Position(newX, newY, currSteps + 1));

                }
            }

        }
        return -1;
    }

    public boolean isGridPathExists(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int srcX = -1, srcY = -1;
        int destX = -1, destY = -1;

        // Locate source and destination
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    srcX = i;
                    srcY = j;
                } else if (grid[i][j] == 2) {
                    destX = i;
                    destY = j;
                }
            }
        }

        if (srcX == -1 || destX == -1) return false; // no source or destination found

        boolean[][] visited = new boolean[m][n];
        class Pair {
            int x, y;

            Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(srcX, srcY));
        visited[srcX][srcY] = true;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!queue.isEmpty()) {
            Pair curr = queue.poll();

            for (int k = 0; k < 4; k++) {
                int newX = curr.x + dx[k];
                int newY = curr.y + dy[k];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (grid[newX][newY] == 2) return true; // destination reached
                    if (!visited[newX][newY] && grid[newX][newY] == 3) {
                        visited[newX][newY] = true;
                        queue.add(new Pair(newX, newY));
                    }
                }
            }
        }
        return false;
    }

    static class Cell {
        int row, col, time;

        Cell(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public static int helpaterp(int[][] hospital) {
        int R = hospital.length;
        int C = hospital[0].length;

        Queue<Cell> queue = new LinkedList<>();
        int totalUninfected = 0;
        int infectedTime = 0;

        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Step 1: Collect all initially infected cells
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (hospital[i][j] == 2) {
                    queue.offer(new Cell(i, j, 0));
                } else if (hospital[i][j] == 1) {
                    totalUninfected++;
                }
            }
        }

        // Step 2: BFS to spread infection
        int infectedCount = 0;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            infectedTime = current.time;

            for (int k = 0; k < 4; k++) {
                int newRow = current.row + dx[k];
                int newCol = current.col + dy[k];

                if (newRow >= 0 && newRow < R && newCol >= 0 && newCol < C && hospital[newRow][newCol] == 1) {
                    hospital[newRow][newCol] = 2;
                    infectedCount++;
                    queue.offer(new Cell(newRow, newCol, current.time + 1));
                }
            }
        }

        return (infectedCount == totalUninfected) ? infectedTime : -1;
    }

    public int minCostPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        class Point {
            int x, y, sum;

            Point(int x, int y, int sum) {
                this.x = x;
                this.y = y;
                this.sum = sum;
            }
        }

        int[][] minCost = new int[m][n];
        for (int[] row : minCost)
            Arrays.fill(row, Integer.MAX_VALUE);
        minCost[0][0] = grid[0][0];

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.sum));
        pq.offer(new Point(0, 0, grid[0][0]));

        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};

        while (!pq.isEmpty()) {
            Point pt = pq.poll();

            if (pt.x == m - 1 && pt.y == n - 1)
                return pt.sum;

            for (int i = 0; i < 4; i++) {
                int newX = pt.x + dx[i];
                int newY = pt.y + dy[i];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    int newCost = pt.sum + grid[newX][newY];
                    if (newCost < minCost[newX][newY]) {
                        minCost[newX][newY] = newCost;
                        pq.offer(new Point(newX, newY, newCost));
                    }
                }
            }
        }

        return -1; // shouldn't reach here
    }


    // Helper method to check if two words differ by only one character
    private static boolean canTransform(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int count = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
                if (count > 1) return false;
            }
        }
        return count == 1;
    }

    // Main method to calculate the word ladder length using BFS
    public static int wordLadderLength(String startWord, String targetWord, String[] wordList) {
        Set<String> wordSet = new HashSet<>(Arrays.asList(wordList));
        if (!wordSet.contains(targetWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(startWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of words at current level
            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                // Try changing each character
                for (int j = 0; j < word.length(); j++) {
                    char[] wordChars = word.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordChars[j] = c;
                        String newWord = new String(wordChars);
                        if (newWord.equals(targetWord)) {
                            return level + 1;
                        }
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // Remove to prevent revisiting
                        }
                    }
                }
            }
            level++;
        }

        return 0;
    }

    // word ladder II
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) return result;

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        bfs(beginWord, endWord, dict, graph, distance);
        List<String> path = new ArrayList<>();
        dfs(endWord, beginWord, graph, distance, path, result);
        return result;
    }

    private void bfs(String start, String end, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);

        for (String word : dict) {
            graph.put(word, new ArrayList<>());
        }
        graph.put(start, new ArrayList<>());

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int currDist = distance.get(curr);

            for (String neighbor : getNeighbors(curr, dict)) {
                graph.get(neighbor).add(curr);
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, currDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
    }

    private void dfs(String word, String start, Map<String, List<String>> graph,
                     Map<String, Integer> distance, List<String> path, List<List<String>> result) {
        if (word.equals(start)) {
            List<String> temp = new ArrayList<>(path);
            temp.add(start);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        path.add(word);
        for (String parent : graph.get(word)) {
            if (distance.get(parent) + 1 == distance.get(word)) {
                dfs(parent, start, graph, distance, path, result);
            }
        }
        path.remove(path.size() - 1);
    }

    private List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; ++i) {
            char oldChar = chars[i];
            for (char c = 'a'; c <= 'z'; ++c) {
                chars[i] = c;
                String newWord = new String(chars);
                if (dict.contains(newWord) && !newWord.equals(word)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = oldChar;
        }

        return neighbors;
    }

    static final int INF = 108;

    public static void shortestDistance(int[][] dist) {
        int n = dist.length;

        for (int k = 0; k < n; k++) {               // Intermediate node
            for (int i = 0; i < n; i++) {           // Source node
                for (int j = 0; j < n; j++) {       // Destination node
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }


    public static int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];
        Arrays.fill(dist, 108); // Treat as unreachable
        dist[src] = 0;

        // Relax edges (V - 1) times
        for (int i = 0; i < V - 1; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[u] != 108 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative weight cycle
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (dist[u] != 108 && dist[u] + w < dist[v]) {
                return new int[]{-1};
            }
        }

        return dist;
    }

}