package com.jeppe.mst;

import java.util.*;

public class PrimAlgorithm {

    // Class to represent an edge
    static class Edge {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Prim's Algorithm implementation
    public static List<Edge> primMST(int vertices, List<Edge> edges) {
        // Priority queue to select the edge with the minimum weight
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));

        // Adjacency list to represent the graph
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (Edge edge : edges) {
            graph.get(edge.source).add(edge);
            graph.get(edge.destination).add(new Edge(edge.destination, edge.source, edge.weight)); // Undirected graph
        }

        // Resultant MST
        List<Edge> mst = new ArrayList<>();

        // Array to keep track of visited vertices
        boolean[] visited = new boolean[vertices];

        // Start from vertex 0 (arbitrary choice)
        visited[0] = true;
        pq.addAll(graph.get(0));

        while (!pq.isEmpty() && mst.size() < vertices - 1) {
            Edge currentEdge = pq.poll();

            if (visited[currentEdge.destination]) {
                continue;
            }

            // Add edge to MST
            mst.add(currentEdge);
            visited[currentEdge.destination] = true;

            // Add all edges from the newly visited vertex
            for (Edge edge : graph.get(currentEdge.destination)) {
                if (!visited[edge.destination]) {
                    pq.add(edge);
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int vertices = 10; // Number of vertices (A-J -> 0-9)
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1,  3), // A-B
                new Edge(0, 3,  4), // A-D
                new Edge(0, 4,  4), // A-E
                new Edge(1, 4,  2), // B-E
                new Edge(1, 5,  3), // B-F
                new Edge(1, 2, 10), // B-C
                new Edge(2, 5,  6), // C-F
                new Edge(2, 6,  1), // C-G
                new Edge(3, 4,  5), // D-E
                new Edge(3, 7,  6), // D-H
                new Edge(4, 5, 11), // E-F
                new Edge(4, 7,  2), // E-H
                new Edge(4, 8,  1), // E-I
                new Edge(5, 6,  2), // F-G
                new Edge(5, 8,  3), // F-I
                new Edge(5, 9, 11), // F-J
                new Edge(6, 9,  8), // G-J
                new Edge(7, 8,  4), // H-I
                new Edge(8, 9,  7)  // I-J
        );

        List<Edge> mst = primMST(vertices, edges);

        System.out.println("Minimum Spanning Tree (Prim's Algorithm):");
        for (Edge edge : mst) {
            System.out.println((char) ('A' + edge.source) + " - " + (char) ('A' + edge.destination) + " : " + edge.weight);
        }
    }
}

