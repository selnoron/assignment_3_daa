// KruskalTest.java
// --------------------------------------------------
// Unit tests for Kruskal's algorithm implementation.
// --------------------------------------------------

package tests;

import org.junit.jupiter.api.Test;
import model.Edge;
import kruskal.KruskalAlgorithm;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class KruskalTest {

    @org.junit.Test
    public void testSmallGraphMST() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 1));
        edges.add(new Edge("B", "C", 2));
        edges.add(new Edge("C", "D", 3));
        edges.add(new Edge("A", "C", 4));
        edges.add(new Edge("B", "D", 5));

        KruskalAlgorithm.Result result = KruskalAlgorithm.runKruskal(nodes, edges);

        // MST cost = 6 (1 + 2 + 3)
        assertEquals(6, result.totalCost);
        assertEquals(nodes.size() - 1, result.mstEdges.size());
        assertTrue(result.timeMs >= 0);
        assertTrue(result.operations > 0);
    }

    @org.junit.Test
    public void testEqualToPrim() {
        // simple graph to ensure both algorithms give the same cost
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 4));
        edges.add(new Edge("A", "C", 3));
        edges.add(new Edge("B", "C", 2));

        var primRes = prim.PrimAlgorithm.runPrim(nodes, edges);
        var kruskalRes = KruskalAlgorithm.runKruskal(nodes, edges);

        assertEquals(primRes.totalCost, kruskalRes.totalCost);
    }

    @org.junit.Test
    public void testDisconnectedGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<Edge> edges = Collections.singletonList(new Edge("A", "B", 1));

        KruskalAlgorithm.Result result = KruskalAlgorithm.runKruskal(nodes, edges);
        assertTrue(result.mstEdges.size() < nodes.size() - 1);
    }
}
