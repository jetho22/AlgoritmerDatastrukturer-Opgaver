package com.jeppe.mst;

import java.util.*;

public class KruskalAlgorithm {

    // Class to represent an edge
    static class Edge implements Comparable<Edge> {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    // Class to represent a disjoint set (union-find)
    static class DisjointSet {
        private int[] parent, rank;

        public DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Find the root of a node with path compression
        public int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        // Union by rank
        public boolean union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2) {
                return false; // Already connected
            }

            if (rank[root1] > rank[root2]) {
                parent[root2] = root1;
            } else if (rank[root1] < rank[root2]) {
                parent[root1] = root2;
            } else {
                parent[root2] = root1;
                rank[root1]++;
            }

            return true;
        }
    }

    // Kruskal's Algorithm to find the MST
    public static List<Edge> kruskalMST(int vertices, List<Edge> edges) {
        // Sort edges by weight
        Collections.sort(edges);

        // Disjoint set to manage connected components
        DisjointSet disjointSet = new DisjointSet(vertices);

        // Resultant MST
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            // If adding this edge doesn't form a cycle
            if (disjointSet.union(edge.source, edge.destination)) {
                mst.add(edge);

                // Stop if we've added enough edges
                if (mst.size() == vertices - 1) {
                    break;
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int vertices = 7; // Number of vertices (A-G -> 0-6)
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 7), // A-B
                new Edge(0, 3, 5), // A-D
                new Edge(1, 2, 8), // B-C
                new Edge(1, 3, 9), // B-D
                new Edge(1, 4, 7), // B-E
                new Edge(2, 4, 5), // C-E
                new Edge(3, 4, 15), // D-E
                new Edge(3, 5, 6), // D-F
                new Edge(4, 5, 8), // E-F
                new Edge(4, 6, 9), // E-G
                new Edge(5, 6, 11) // F-G
        );

        List<Edge> mst = kruskalMST(vertices, edges);

        System.out.println("Minimum Spanning Tree (Kruskal's Algorithm):");
        for (Edge edge : mst) {
            System.out.println((char) ('A' + edge.source) + " - " + (char) ('A' + edge.destination) + " : " + edge.weight);
        }
    }
}
