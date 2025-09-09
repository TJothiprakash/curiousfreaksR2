package backtracking.mcoloring;
import java.util.*;

/*🔹 Problem: M-Coloring of a Graph

Given:

A graph with V vertices

M colors available

Goal:

Assign a color (1..M) to each vertex such that:

No two adjacent vertices have the same color.

If possible, return one valid coloring.

If not, return failure.

🔹 Intuition

This is constraint satisfaction via backtracking.

Start at vertex 0.

For each vertex:

Try each color (1..M).

If assigning this color is valid (no adjacent vertex has same color) → recurse for next vertex.

If no color works → backtrack.

If all vertices are colored → success.

This is similar to N-Queens: place, validate, recurse, backtrack.

🔹 Dry Run

Graph (adjacency matrix):

0 -- 1
|    |
3 -- 2


M = 3 colors

Vertex 0 → color 1

Vertex 1 → color 2 (not same as 0)

Vertex 2 → color 1 (not same as 1)

Vertex 3 → color 3 (not same as 0,2)

Valid solution found: [1, 2, 1, 3]*/
public class MColoringGraph {

    public static void main(String[] args) {
        int V = 4; // number of vertices
        int M = 3; // number of colors

        // adjacency matrix for square graph
        int[][] graph = {
                {0,1,0,1},
                {1,0,1,0},
                {0,1,0,1},
                {1,0,1,0}
        };

        int[] colors = new int[V];
        if (graphColoring(graph, M, colors, 0)) {
            System.out.println("Solution exists. Coloring:");
            System.out.println(Arrays.toString(colors));
        } else {
            System.out.println("No solution exists with " + M + " colors.");
        }
    }

    // Recursive backtracking function
    private static boolean graphColoring(int[][] graph, int m, int[] colors, int v) {
        if (v == graph.length) return true; // all vertices colored

        for (int c = 1; c <= m; c++) {
            if (isSafe(graph, colors, v, c)) {
                colors[v] = c;
                if (graphColoring(graph, m, colors, v + 1)) return true;
                colors[v] = 0; // backtrack
            }
        }
        return false; // no color leads to solution
    }

    // Check if color c can be assigned to vertex v
    private static boolean isSafe(int[][] graph, int[] colors, int v, int c) {
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && colors[i] == c) return false;
        }
        return true;
    }
}
/*🔹 Complexity

Time: O(M^V) in worst case (each vertex has M choices).

Backtracking + pruning makes it much faster in practice.

Space: O(V) for color assignments + recursion stack.*/