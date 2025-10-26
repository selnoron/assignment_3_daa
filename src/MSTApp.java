// MSTApp.java
// --------------------------------------------------
// Main application for Assignment 3 - MST
// Reads graphs from JSON, runs Prim and Kruskal algorithms,
// collects results, and writes them to output.json and CSV.
// --------------------------------------------------

import model.Edge;
import model.GraphModel;
import model.InputData;
import model.OutputData;
import prim.PrimAlgorithm;
import kruskal.KruskalAlgorithm;
import util.IOUtils;

import java.util.*;
import java.nio.file.*;

public class MSTApp {

    public static void main(String[] args) {
        String inputPath = "results/assign_3_input.json";
        String outputPath = "results/assign_3_output.json";
        String csvPath = "results/summary.csv";

        // Step 1. Read input data
        InputData input = IOUtils.readInput(inputPath);
        if (input == null || input.graphs == null) {
            System.err.println("No input graphs found!");
            return;
        }

        List<OutputData> allResults = new ArrayList<>();

        // Step 2. Process each graph
        for (GraphModel graph : input.graphs) {
            System.out.println("Running MST for Graph ID " + graph.id + " ...");

            // --- Prim ---
            PrimAlgorithm.Result primResult = PrimAlgorithm.runPrim(graph.nodes, graph.edges);

            // --- Kruskal ---
            KruskalAlgorithm.Result kruskalResult = KruskalAlgorithm.runKruskal(graph.nodes, graph.edges);

            // --- Prepare OutputData ---
            OutputData result = new OutputData();
            result.graph_id = graph.id;

            Map<String, Integer> stats = new HashMap<>();
            stats.put("vertices", graph.nodes.size());
            stats.put("edges", graph.edges.size());
            result.input_stats = stats;

            // Prim results
            OutputData.AlgorithmResult primOut = new OutputData.AlgorithmResult();
            primOut.mst_edges = primResult.mstEdges;
            primOut.total_cost = primResult.totalCost;
            primOut.operations_count = primResult.operations;
            primOut.execution_time_ms = primResult.timeMs;

            // Kruskal results
            OutputData.AlgorithmResult kruskalOut = new OutputData.AlgorithmResult();
            kruskalOut.mst_edges = kruskalResult.mstEdges;
            kruskalOut.total_cost = kruskalResult.totalCost;
            kruskalOut.operations_count = kruskalResult.operations;
            kruskalOut.execution_time_ms = kruskalResult.timeMs;

            result.prim = primOut;
            result.kruskal = kruskalOut;

            allResults.add(result);
        }

        // Step 3. Write output JSON and CSV
        try {
            Files.createDirectories(Paths.get("results"));
        } catch (Exception ignored) {}

        IOUtils.writeOutputJson(outputPath, allResults);
        IOUtils.writeCsv(csvPath, allResults);

        System.out.println("✅ All computations completed successfully!");
    }
}
