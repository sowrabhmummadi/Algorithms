package com.sow.learning.graphs;

import java.util.*;

/**
 * HierHolzers algorithm
 * Given a directed Eulerian graph, Print Euler circuit.
 * Euler Circuit: A path that traverses every edge of the graph, and path ends on the starting vertex.
 * <p>
 * Eulerian graph:
 * --> all vertices with non-zero degrees belong to a single strongly connected graph
 * --> In degree and out degree of every vertex is the same
 */
public class HierHolzer {
    List<List<Integer>> adjacencyList;

    public HierHolzer(int noOfVertices, int[][] edges) {
        adjacencyList = createAdjacencyList(noOfVertices, edges);
    }

    public static void main(String... args) {
        HierHolzer hierHolzer = new HierHolzer(5, new int[][]{
                {0, 1}, {1, 3}, {1, 2}, {2, 0}, {3, 4}, {4, 1}
        });
        hierHolzer.printEulerCircuit();
    }

    private List<List<Integer>> createAdjacencyList(int noOfVertices, int[][] edges) {
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < noOfVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            adjacencyList.get(edges[i][0]).add(edges[i][1]);
        }
        return adjacencyList;
    }

    private void printEulerCircuit() {
        Stack<Integer> currentPath = new Stack<>();
        List<Integer> circuit = new ArrayList<>();
        int currentIndex = new Random().nextInt(adjacencyList.size());
        currentPath.push(currentIndex);
        while (true) {
            if (adjacencyList.get(currentIndex).isEmpty()) {
                circuit.add(currentPath.pop());
                try {
                    currentIndex = currentPath.peek();
                } catch (EmptyStackException e) {
                    break;
                }
            } else {
                currentIndex = adjacencyList.get(currentIndex).remove(0);
                currentPath.push(currentIndex);
            }
        }
        for (int i = circuit.size() - 1; i >= 0; i--) {
            System.out.print("--> " + circuit.get(i) + " ");
        }
    }

}
