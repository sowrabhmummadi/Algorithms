package com.sow.learning.dynamicprogramming;

/**
 * Given a 2d array find the maximum snake length
 * snake sequence: a sequency of numbers where each new number is located either to right or down
 * and abs between two numbers should be 1;
 */
public class MaximumSnakeLengthSequence {

    public static void main(String[] args) {
        MaximumSnakeLengthSequence maximumSnakeLengthSequence = new MaximumSnakeLengthSequence();
        maximumSnakeLengthSequence.getMaximumLength(new int[][]{
                {7, 5, 2, 3, 1},
                {3, 4, 1, 4, 4},
                {1, 5, 6, 7, 8},
                {3, 4, 5, 8, 9},
                {3, 2, 2, 7, 6}
        });
    }

    private void getMaximumLength(int[][] matrix) {
        int[][] lookupTable = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (Math.abs(matrix[i][j] - matrix[i][j - 1]) == 1) {
                    lookupTable[i][j] = Math.max(lookupTable[i][j], lookupTable[i][j - 1] + 1);
                }
            }
        }
    }
}
