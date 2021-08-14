package com.sow.learning.graphs;

import java.util.*;

class Graph {
    private final int noOfVertices;
    private final List<Edge> edgesList;

    Graph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.edgesList = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        edgesList.add(e);
    }

    public List<List<Integer>> getAdjacencyList() {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < noOfVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (Edge edge : edgesList) {
            adjacencyList.get(edge.src).add(edge.dest);
        }
        return adjacencyList;
    }

    public int[][] getAdjacencyMatrix(int edgeSpecifier) {
        int[][] adjacentMatrix = new int[noOfVertices][noOfVertices];
        for (Edge edge : edgesList) {
            adjacentMatrix[edge.src][edge.dest] = edgeSpecifier;
            adjacentMatrix[edge.src][edge.dest] = edgeSpecifier;
        }
        return adjacentMatrix;
    }
}

class Edge {
    final int src;
    final int dest;

    Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}

public class TopologicalSorting {
    private final Graph graph;

    public TopologicalSorting(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(new Edge(0, 2));
        graph.addEdge(new Edge(1, 2));
        graph.addEdge(new Edge(1, 3));
        graph.addEdge(new Edge(2, 4));
        graph.addEdge(new Edge(4, 7));
        graph.addEdge(new Edge(4, 5));
        graph.addEdge(new Edge(5, 6));
        graph.addEdge(new Edge(3, 5));
    }

    public void getTopologicalOrder() {
        Set visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();


    }
}


