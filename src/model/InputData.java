// InputData.java
// ----------------------------------------------
// Represents the entire input JSON file structure.
// Example:
// {
//   "graphs": [ {...}, {...}, ... ]
// }
// ----------------------------------------------

package model;

import java.util.List;

public class InputData {
    public List<GraphModel> graphs;

    public InputData() {}

    public InputData(List<GraphModel> graphs) {
        this.graphs = graphs;
    }
}
