// OutputData.java
// ----------------------------------------------
// Represents the structure of results (output.json).
// Each OutputData object stores MST results for one graph,
// including Prim and Kruskal metrics (edges, cost, operations, time).
// ----------------------------------------------

package model;

import java.util.List;
import java.util.Map;

public class OutputData {

    // Graph information
    public int graph_id;
    public Map<String, Integer> input_stats;

    // Algorithm results
    public AlgorithmResult prim;
    public AlgorithmResult kruskal;

    // Nested class for algorithm-specific results
    public static class AlgorithmResult {
        public List<Edge> mst_edges;
        public int total_cost;
        public long operations_count;
        public double execution_time_ms;
    }

    public OutputData() {}
}
