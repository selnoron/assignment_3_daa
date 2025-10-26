// Edge.java
// ----------------------------------------------
// Simple data class representing an edge in the graph.
// Each edge connects two nodes (from → to) and has a weight (cost).
// ----------------------------------------------

package model;

public class Edge {
    public String from;
    public String to;
    public int weight;

    // Default constructor (needed for JSON parsing)
    public Edge() {}

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return from + " - " + to + " (" + weight + ")";
    }
}
