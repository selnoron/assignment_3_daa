// PrimAlgorithm.java
// ----------------------------------------------
// Implementation of Prim's algorithm for finding
// the Minimum Spanning Tree (MST) in an undirected,
// weighted graph.
//
// Author: [Your Name]
// ----------------------------------------------

package prim;

import model.Edge;
import java.util.*;

/**
 * This class implements Prim's algorithm.
 * It builds a tree by always picking the minimum-weight edge
 * that connects a visited vertex to an unvisited one.
 */
public class PrimAlgorithm {

    // Helper class to store the result of MST computation
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public long operations = 0;   // counts number of key algorithm steps
        public double timeMs = 0.0;   // execution time in milliseconds
    }

    /**
     * Run Prim's algorithm on a given graph.
     * @param nodes - list of vertex names
     * @param edges - list of graph edges
     * @return Result object with MST info
     */
    public static Result runPrim(List<String> nodes, List<Edge> edges) {
        Result result = new Result();

        if (nodes == null || nodes.isEmpty()) {
            return result;
        }

        // Step 1. Build adjacency list (for fast neighbor lookups)
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : nodes) adj.put(node, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight)); // add reverse
        }

        // Step 2. Initialize structures
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        String start = nodes.get(0);  // arbitrary start node
        visited.add(start);
        result.operations++;

        // Add all edges from start vertex
        for (Edge e : adj.get(start)) {
            minHeap.add(e);
            result.operations++;
        }

        // Step 3. Main loop
        long startTime = System.nanoTime();

        while (!minHeap.isEmpty() && visited.size() < nodes.size()) {
            Edge smallest = minHeap.poll();
            result.operations++;

            if (visited.contains(smallest.to)) {
                result.operations++;
                continue;
            }

            // Add edge to MST
            visited.add(smallest.to);
            result.mstEdges.add(smallest);
            result.totalCost += smallest.weight;
            result.operations++;

            // Add new edges to heap
            for (Edge next : adj.get(smallest.to)) {
                if (!visited.contains(next.to)) {
                    minHeap.add(next);
                    result.operations++;
                }
            }
        }

        long endTime = System.nanoTime();
        result.timeMs = (endTime - startTime) / 1_000_000.0;

        return result;
    }
}
