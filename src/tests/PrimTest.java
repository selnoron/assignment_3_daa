// PrimTest.java
// --------------------------------------------------
// Unit tests for Prim's algorithm implementation.
// --------------------------------------------------

package tests;

import org.junit.jupiter.api.Test;
import model.Edge;
import prim.PrimAlgorithm;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PrimTest {

    @org.junit.Test
    public void testSmallGraphMST() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 1));
        edges.add(new Edge("B", "C", 2));
        edges.add(new Edge("C", "D", 3));
        edges.add(new Edge("A", "C", 4));
        edges.add(new Edge("B", "D", 5));

        PrimAlgorithm.Result result = PrimAlgorithm.runPrim(nodes, edges);

        // Total cost should be 6 (1 + 2 + 3)
        assertEquals(6, result.totalCost);
        assertEquals(nodes.size() - 1, result.mstEdges.size());
        assertTrue(result.timeMs >= 0);
        assertTrue(result.operations > 0);
    }

    @org.junit.Test
    public void testDisconnectedGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 2));
        // 'C' is disconnected

        PrimAlgorithm.Result result = PrimAlgorithm.runPrim(nodes, edges);

        // MST should not cover all nodes
        assertTrue(result.mstEdges.size() < nodes.size() - 1);
    }
}
