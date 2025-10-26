// GraphModel.java
// ----------------------------------------------
// Represents one graph (city transportation network).
// Contains a unique ID, list of nodes (districts),
// and list of possible roads (edges with costs).
// ----------------------------------------------

package model;

import java.util.List;

public class GraphModel {
    public int id;
    public List<String> nodes;
    public List<Edge> edges;

    public GraphModel() {}

    public GraphModel(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    public String toString() {
        return "Graph " + id + " (" + nodes.size() + " nodes, " + edges.size() + " edges)";
    }
}
