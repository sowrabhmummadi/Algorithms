package com.sow.learning.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Solution for m-color problem using brute force backtracking
 * <p>
 * Question: Given 'm' colors to color a undirected graph with V vertices and E edges
 * <br>
 * constraint: Adjacent vertices should not be colored with same color.
 * </p>
 * <p>
 * Problem type: Enumeration- find all possible solutions
 * <p>
 * Time complexity: O(m^n) m- number of colors provided , n number of vertices available
 */
public class MColorProblem {
    private final int[][] adjacentMatrix;

    public MColorProblem(int noOfVertices, int[][] edges) {
        adjacentMatrix = new int[noOfVertices][noOfVertices];
        for (int[] edge : edges) {
            adjacentMatrix[edge[0]][edge[1]] = -1;
            adjacentMatrix[edge[1]][edge[0]] = -1;
        }
    }

    public static void main(String[] args) {
        MColorProblem mColorProblem = new MColorProblem(4, new int[][]{{0, 1}, {1, 3}, {3, 2}, {2, 0}});
        mColorProblem.findAllSolutions(3);
    }

    public void findAllSolutions(int noOfColors) {
        final int[] solution = new int[adjacentMatrix.length];
        Arrays.fill(solution, -1);
        solve(noOfColors, 0, solution);
    }

    private void solve(int noOfColors, int vertexId, int[] solution) {
        if (vertexId == adjacentMatrix.length) {
            System.out.println(Arrays.toString(solution));
        } else {
            for (int validColor : getValidColors(noOfColors, vertexId, solution)) {
                solution[vertexId] = validColor;
                solve(noOfColors, vertexId + 1, solution);
                solution[vertexId] = -1;
            }
        }
    }

    private int[] getValidColors(int noOfColors, int vertexId, int[] solution) {
        int[] vertexRow = this.adjacentMatrix[vertexId];
        List<String> validColors = new ArrayList<>();
        return IntStream.range(0, noOfColors).filter(colorId -> {
            for (int i = 0; i < vertexRow.length; i++) {
                if (vertexRow[i] != 0 && solution[i] != -1) {
                    if (colorId == solution[i]) {
                        return false;
                    }
                }
            }
            return true;
        }).toArray();

    }


}
