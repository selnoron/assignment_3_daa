// IOUtils.java
// --------------------------------------------------
// Utility class for reading and writing JSON/CSV files.
// Uses Gson for JSON serialization.
// --------------------------------------------------

package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import model.InputData;
import model.OutputData;

public class IOUtils {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Reads the input JSON file and converts it into an InputData object.
     */
    public static InputData readInput(String filePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            return gson.fromJson(reader, InputData.class);
        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return null;
        }
    }

    /**
     * Writes results (OutputData) into JSON file.
     */
    public static void writeOutputJson(String filePath, List<OutputData> results) {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            gson.toJson(results, writer);
            System.out.println("✅ Output JSON saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }

    /**
     * Writes a summary table (graph_id, total cost, time, etc.) into a CSV file.
     */
    public static void writeCsv(String filePath, List<OutputData> results) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write("Graph ID,Vertices,Edges,Prim Cost,Kruskal Cost,Prim Ops,Kruskal Ops,Prim Time (ms),Kruskal Time (ms)\n");

            for (OutputData r : results) {
                writer.write(String.format("%d,%d,%d,%d,%d,%d,%d,%.3f,%.3f\n",
                        r.graph_id,
                        r.input_stats.get("vertices"),
                        r.input_stats.get("edges"),
                        r.prim.total_cost,
                        r.kruskal.total_cost,
                        r.prim.operations_count,
                        r.kruskal.operations_count,
                        r.prim.execution_time_ms,
                        r.kruskal.execution_time_ms
                ));
            }

            System.out.println("✅ Summary CSV saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}
