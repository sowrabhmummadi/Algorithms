package com.sow.learning.graphs;

public class UnionFind {

    private final int[][] edges;
    private final int noOfVertices;

    public UnionFind(int noOfVertices, int[][] edges) {
        this.noOfVertices = noOfVertices;
        this.edges = edges;
    }

    public static void main(String[] args) {
        int noOfVertices = 5;
        UnionFind unionFind = new UnionFind(noOfVertices, new int[][]{
                {0, 1}, {1, 2}, {2, 3}, {3, 4}});
        final boolean cyclicGraph = unionFind.isCyclicGraph();
        System.out.println("cyclicGraph = " + cyclicGraph);
    }

    int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        } else {
            return find(parent, parent[i]);
        }
    }

    void union(int[] parent, int i, int j) {
        int representativeA = find(parent, i);
        int representativeB = find(parent, j);
        parent[representativeA] = representativeB;
    }

    public boolean isCyclicGraph() {
        int[] parent = new int[noOfVertices];
        for (int i = 0; i < noOfVertices; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int x = find(parent, edges[i][0]);
            int y = find(parent, edges[i][1]);
            if (x == y) {
                return true;
            }
            union(parent, x, y);
        }
        return false;
    }
}
