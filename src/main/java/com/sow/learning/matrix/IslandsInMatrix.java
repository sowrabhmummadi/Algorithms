package com.sow.learning.matrix;

import java.util.ArrayList;
import java.util.List;

public class IslandsInMatrix {

    public static void main(String[] args) {
        final IslandsInMatrix islandsInMatrix = new IslandsInMatrix();
        final int noOfIslands = islandsInMatrix.findNoOfIslands(new int[][]{
                {1, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1}
        });
        System.out.println("noOfIslands = " + noOfIslands);
    }

    private int findNoOfIslands(int[][] matrix) {
        if (matrix[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    findIsland(i, j, matrix, visited);
                    count++;
                }
            }
        }
        return count;
    }

    private void findIsland(int i, int j, int[][] matrix, boolean[][] visited) {
        List<int[]> solutionList = new ArrayList<>();
        dfs(i, j, matrix, visited, solutionList);
        for (int[] coordinate : solutionList) {
            System.out.print(" " + coordinate[0] + "," + coordinate[1] + "--> ");
        }
        System.out.println();
    }

    void dfs(int i, int j, int[][] matrix, boolean[][] visited, List<int[]> solutionList) {
        visited[i][j] = true;
        solutionList.add(new int[]{i, j});
        for (int[] p : getAllPossibliites(i, j, matrix, visited)) {
            dfs(p[0], p[1], matrix, visited, solutionList);
        }
    }

    private List<int[]> getAllPossibliites(int i, int j, int[][] matrix, boolean[][] visited) {
        List<int[]> possibilityList = new ArrayList<>();
        if ((i - 1) >= 0 && !visited[i - 1][j] && matrix[i - 1][j] == 1) {
            possibilityList.add(new int[]{i - 1, j});
        }

        if (i + 1 < matrix.length && !visited[i + 1][j] && matrix[i + 1][j] == 1) {
            possibilityList.add(new int[]{i + 1, j});
        }

        if ((j - 1) >= 0 && !visited[i][j - 1] && matrix[i][j - 1] == 1) {
            possibilityList.add(new int[]{i, j - 1});
        }

        if (j + 1 < matrix[0].length && !visited[i][j + 1] && matrix[i][j + 1] == 1) {
            possibilityList.add(new int[]{i, j + 1});
        }
        return possibilityList;
    }
}
