// KruskalAlgorithm.java
// ----------------------------------------------
// Implementation of Kruskal's algorithm for finding
// the Minimum Spanning Tree (MST) in an undirected,
// weighted graph.
//
// Author: [Your Name]
// ----------------------------------------------

package kruskal;

import model.Edge;
import java.util.*;

/**
 * Kruskal's algorithm sorts all edges by weight
 * and connects components using the Union-Find structure.
 */
public class KruskalAlgorithm {

    // Helper class for result storage
    public static class Result {
        public List<Edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public long operations = 0;
        public double timeMs = 0.0;
    }

    // Union-Find data structure
    private static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> rank = new HashMap<>();
        private long operations = 0;

        public UnionFind(List<String> nodes) {
            for (String n : nodes) {
                parent.put(n, n);
                rank.put(n, 0);
            }
        }

        public String find(String node) {
            operations++;
            if (!parent.get(node).equals(node)) {
                parent.put(node, find(parent.get(node))); // path compression
                operations++;
            }
            return parent.get(node);
        }

        public boolean union(String a, String b) {
            operations++;
            String rootA = find(a);
            String rootB = find(b);

            if (rootA.equals(rootB)) {
                operations++;
                return false; // already connected
            }

            // union by rank
            if (rank.get(rootA) < rank.get(rootB)) {
                parent.put(rootA, rootB);
            } else if (rank.get(rootA) > rank.get(rootB)) {
                parent.put(rootB, rootA);
            } else {
                parent.put(rootB, rootA);
                rank.put(rootA, rank.get(rootA) + 1);
            }

            operations++;
            return true;
        }

        public long getOps() { return operations; }
    }

    /**
     * Run Kruskal's algorithm on a given graph.
     * @param nodes - list of vertex names
     * @param edges - list of edges
     * @return Result with MST info
     */
    public static Result runKruskal(List<String> nodes, List<Edge> edges) {
        Result result = new Result();
        if (nodes == null || nodes.isEmpty()) return result;

        // Step 1. Sort edges by weight
        List<Edge> sorted = new ArrayList<>(edges);
        sorted.sort(Comparator.comparingInt(e -> e.weight));
        result.operations += sorted.size();

        // Step 2. Initialize Union-Find
        UnionFind uf = new UnionFind(nodes);

        long startTime = System.nanoTime();

        // Step 3. Iterate through sorted edges
        for (Edge e : sorted) {
            result.operations++;
            if (uf.union(e.from, e.to)) {
                result.mstEdges.add(e);
                result.totalCost += e.weight;
                result.operations++;
            }
            if (result.mstEdges.size() == nodes.size() - 1)
                break;
        }

        long endTime = System.nanoTime();
        result.timeMs = (endTime - startTime) / 1_000_000.0;
        result.operations += uf.getOps();

        return result;
    }
}
