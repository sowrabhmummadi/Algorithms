package com.sow.learning.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Solution for Hamiltonian cycle problem  using brute force backtracking
 * <p>
 * Question: Given a undirected graph with V vertices and E edges. Traverse the graph
 * such that each vertex is visited exactly once from  starting point and ending point\
 * where staring point is same as ending point
 * <br>
 * constraint: each vertex is visited exactly once, starting point and ending point of the graph is the same;
 * </p>
 * <p>
 * Problem type: Enumeration- find all possible solutions
 * <p>
 * Time complexity:
 */
public class HamiltonianCycle {
    private final int numberOfVertices;
    private final int[][] adjacentMatrix;

    public HamiltonianCycle(int numberOfVertices,int[][] edges) {
        this.numberOfVertices = numberOfVertices;
        adjacentMatrix = new int[numberOfVertices][numberOfVertices];
        for (int[] edge : edges) {
            adjacentMatrix[edge[0]][edge[1]] = -1;
            adjacentMatrix[edge[1]][edge[0]] = -1;
        }
    }

    public void findAllSolutions(int startVertexId){
        List<Integer> solutionList = new ArrayList<>();
        solutionList.add(startVertexId);
        solve(solutionList,startVertexId,0);
    }
    private void solve(List<Integer> solutionList, int startVertexId, int vertexId){
        if(solutionList.size()==numberOfVertices){
            if(adjacentMatrix[vertexId][startVertexId]==-1){
                solutionList.add(startVertexId);
                System.out.println(solutionList);
                solutionList.remove(solutionList.size()-1);
            }
        }
        else{
            List<Integer> validVertices = getValidVertices(solutionList, vertexId);
            for (int validVertex : validVertices) {
                solutionList.add(validVertex);
                solve(solutionList, startVertexId, validVertex);
                solutionList.remove(solutionList.size()-1);
            }
        }
    }

    private List<Integer>getValidVertices(List<Integer> solutionList, int vertexId) {
        int[] vertexRow = this.adjacentMatrix[vertexId];
        return  IntStream.range(0, vertexRow.length)
                        .filter(i -> vertexRow[i] != 0)
                        .filter(i->!solutionList.contains(i))
                        .boxed()
                        .collect(Collectors.toList());
    }

    public static void main(String[] args) {
      /*  HamiltonianCycle hamiltonianCycle = new HamiltonianCycle(6,
                new int[][]{{0,1},{1,2},{2,3},{3,4},{4,5},{5,0},{5,1},{4,2},{0,2}});*/
        HamiltonianCycle hamiltonianCycle = new HamiltonianCycle(6,
                new int[][]{{0,1},{0,2},{1,3},{2,3},{2,4},{2,5},{4,5}});
        hamiltonianCycle.findAllSolutions(1);
    }
}
