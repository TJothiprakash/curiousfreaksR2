package practicesessions.sept_8;


import java.util.*;

public class ReconstructItinerary {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    LinkedList<String> result = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        // Step 1: Build the graph
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, k -> new PriorityQueue<>()).offer(to);
        }

        // Step 2: Start DFS from JFK
        dfs("JFK");

        return result;
    }

    private void dfs(String airport) {
        PriorityQueue<String> neighbors = graph.get(airport);
        while (neighbors != null && !neighbors.isEmpty()) {
            String next = neighbors.poll(); // always smallest
            dfs(next);
        }
        // post-order insert
        result.addFirst(airport);
    }

    public static void main(String[] args) {
        ReconstructItinerary sol = new ReconstructItinerary();
        List<List<String>> tickets = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO")
        );
        System.out.println(sol.findItinerary(tickets));
    }
}
