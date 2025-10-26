import pandas as pd
import matplotlib.pyplot as plt

# Read CSV file
data = pd.read_csv("results/summary.csv")

# --- Execution Time Chart ---
plt.figure(figsize=(8,5))
plt.plot(data["Graph ID"], data["Prim Time (ms)"], marker='o', label="Prim")
plt.plot(data["Graph ID"], data["Kruskal Time (ms)"], marker='s', label="Kruskal")
plt.title("Execution Time Comparison (Prim vs Kruskal)")
plt.xlabel("Graph ID")
plt.ylabel("Execution Time (ms)")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("plot_time.png")
print("✅ Saved plot_time.png")

# --- Operations Count Chart ---
plt.figure(figsize=(8,5))
plt.plot(data["Graph ID"], data["Prim Ops"], marker='o', label="Prim Operations")
plt.plot(data["Graph ID"], data["Kruskal Ops"], marker='s', label="Kruskal Operations")
plt.title("Operation Count Comparison")
plt.xlabel("Graph ID")
plt.ylabel("Number of Operations")
plt.legend()
plt.grid(True)
plt.tight_layout()
plt.savefig("plot_ops.png")
print("✅ Saved plot_ops.png")
