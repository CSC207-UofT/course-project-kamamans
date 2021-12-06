package entities;

import java.util.ArrayList;

/**
 * Graph class represented a directed, weighted graph.
 * This class is purely mathematical.
 * There should be no references to airports or iatacodes.
 */
public class Graph {
    private int node_count; // number of vertices in the graph
    private int[][] matrix; // Adjacency Matrix

    public Graph(int vertices) {
        this.node_count = vertices;
        this.matrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int dest) {
        this.matrix[src][dest] = 1;
    }

    /**
     * Return all possible paths from <src> to <dest>
     * @param src
     * @param dest
     * @return Arraylist of all possible paths (each individual path is an array list of visited nodes)
     */
    public ArrayList<ArrayList<Integer>> allPaths (int src, int dest) {
        // Mark all vertices as not visited
        boolean[] visited = new boolean[this.node_count];
        for (int i = 0; i < this.node_count; i++) {
            visited[i] = false;
        }

        // Current path
        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(src);

        ArrayList<ArrayList<Integer>> output = recursivePaths(src, dest, visited, path);
        return output;
    }

    private ArrayList<ArrayList<Integer>> recursivePaths (int src, int dest, boolean[] visited, ArrayList<Integer> path) {
        ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();

        // Base Case
        if (src == dest) {
            output.add(path);
            return output;
        }

        // Inductive Step
        boolean[] copy_visited = visited.clone();
        copy_visited[src] = true;

        for (int target = 0; target < this.node_count; target++) {
            if (this.matrix[src][target] != 0 && !copy_visited[target]) {
                // path from src -> target exists, and target has not been visited
                ArrayList<Integer> copy_path = (ArrayList<Integer>) path.clone();
                copy_path.add(target);

                // <paths_from_target> contains all the possible paths from <target> to <dest>
                ArrayList<ArrayList<Integer>> paths_from_target = recursivePaths(target, dest, copy_visited, copy_path);
                output.addAll(paths_from_target);
            }
        }
        return output;
    }

    // TODO: Delete this.  This method is only for development purposes
    public String toString() {
        String output = "  ";

        for (int node = 0; node < this.node_count; node++) {
            output += node + " ";
        }
        output += "\n-";
        for (int node = 0; node < this.node_count; node++) {
            output += "--";
        }
        output += "\n";

        for (int r = 0; r < this.node_count; r++) {
            String line = r + "|";
            for (int c = 0; c < this.node_count; c++) {
                line += this.matrix[r][c] + " ";
            }
            output += line + "\n";
        }
        return output;
    }
}


