package com.sow.learning.dynamicprogramming;

import java.util.stream.IntStream;

/**
 * Given when a binary matrix find the maximum square matrix.
 */
public class MaximumSquareSubMatrix {

    public static void main(String[] args) {
        MaximumSquareSubMatrix maximumSquareSubMatrix = new MaximumSquareSubMatrix();
        int maximumSquareMatrix = maximumSquareSubMatrix.getMaximumSquareMatrix(new int[][]{
                {0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}
        });
        System.out.println("maximumSquareMatrix = " + maximumSquareMatrix);
    }

    public int getMaximumSquareMatrix(int[][] matrix) {
        int[][] solutionMatrix = new int[matrix.length][matrix[0].length];
        int maxValue = 0;

        for (int rowId = 0; rowId < matrix.length; rowId++) {
            for (int columnId = 0; columnId < matrix[rowId].length; columnId++) {
                if (rowId == 0 || columnId == 0) {
                    solutionMatrix[rowId][columnId] = 0;
                } else {
                    if (matrix[rowId][columnId] == 0) {
                        solutionMatrix[rowId][columnId] = 0;
                    } else {
                        int i = IntStream.of(solutionMatrix[rowId - 1][columnId],
                                solutionMatrix[rowId - 1][columnId - 1],
                                solutionMatrix[rowId - 1][columnId]).min().orElse(0);
                        solutionMatrix[rowId][columnId] = i + 1;
                        if (solutionMatrix[rowId][columnId] > maxValue) {
                            maxValue = solutionMatrix[rowId][columnId];
                        }
                    }
                }
            }
        }
        return maxValue;
    }
}
