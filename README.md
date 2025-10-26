#  Report — Assignment 3  
## Minimum Spanning Tree (MST)

###  Topic
Optimization of a city transportation network using Prim’s and Kruskal’s algorithms.

---

###  Purpose
The goal of this work was to find the best and cheapest way to connect all city districts  
with roads. Each district should be connected to every other district,  
and the total construction cost should be as small as possible.

---

###  Task
The city map was represented as a **graph**:
- Vertices (nodes) — city districts  
- Edges — possible roads between them  
- Weights — cost of building the road  

We used two algorithms:
1. **Prim’s Algorithm**  
2. **Kruskal’s Algorithm**

Both find a **Minimum Spanning Tree (MST)** —  
the smallest set of roads that connects all districts.

---

###  Work Process
1. Created an input JSON file with graph data.  
2. Implemented Prim’s and Kruskal’s algorithms in Java.  
3. Each algorithm found its MST and calculated:
   - Total cost  
   - Number of operations  
   - Execution time  
4. Results were saved to `assign_3_output.json` and `summary.csv`.  
5. Two graphs were made:
   - `plot_time.png` – shows execution time  
   - `plot_ops.png` – shows number of operations  

---

###  Results

| Graph ID | Vertices | Edges | Prim Cost | Kruskal Cost | Prim Ops | Kruskal Ops | Prim Time (ms) | Kruskal Time (ms) |

|-----------|-----------|--------|------------|---------------|------------|----------------|--------------------|

| 1 | 5 | 7 | 16 | 16 | 18 | 42 | 0.018 | 0.027 |

| 2 | 10 | 13 | 33 | 33 | 36 | 97 | 0.018 | 0.014 |

 Both algorithms found the same MST cost, so they are correct.

---

###  Analysis
- **Prim’s Algorithm** works faster when there are many roads between districts (dense graph).  
- **Kruskal’s Algorithm** works faster when there are fewer roads (sparse graph).  
- The total cost of the MST is the same in both algorithms.  
- The number of operations was smaller for Prim’s algorithm.

---

###  Graphs
Two visual charts were created:
- **plot_time.png** — shows the time each algorithm needed to finish.  
- **plot_ops.png** — shows how many operations each algorithm did.

From the graphs:
- Prim’s algorithm had fewer operations.
- Kruskal’s algorithm was a bit faster for the second graph.

---

###  Conclusion
Both algorithms solve the problem correctly.  
They can help to plan and build roads between districts  
with the **lowest construction cost**.

- **Prim’s Algorithm** — better for dense networks.  
- **Kruskal’s Algorithm** — better for sparse networks.

This project helped to understand how algorithms can optimize  
real-world problems like city transportation planning.
