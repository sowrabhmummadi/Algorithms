package com.sow.learning.rangequery;

import com.sow.learning.helper.MatrixPrettyPrint;

/**
 * Range Minimum query using dynamic sparse table
 * <p>
 * Problem : give an array/list and the index range  , find the minimum value within the range.
 * there can be multiple ranges to work with
 * <p>
 * Preparation complexity: O(N^2)
 * Retrieval complexity: O(1)
 * Retrieval for 'M' queries: O(M)
 * <p>
 * notes:
 * -> solution array size
 * --> n rows (input size), floor[log(n)]+1 columns
 */

public class RangeQuerySparseTable {

    private final int[] array;
    private final int[][] sparseTable;
    private final int noOfColoumsInSparseTable;

    public RangeQuerySparseTable(int[] array) {
        this.array = array;
        this.noOfColoumsInSparseTable = (int) (Math.floor(log2(array.length)) + 1);
        this.sparseTable = new int[array.length][noOfColoumsInSparseTable];
        computeSolutionArray();
        new MatrixPrettyPrint(System.out).print(sparseTable);
    }

    public static void main(String[] args) {
        RangeQuerySparseTable rangeQueryBacktracking = new RangeQuerySparseTable(new int[]{7, 6, 1, 3, 4, 2, 9});
        int minimumVal = rangeQueryBacktracking.findMinimumInRange(3, 6);
        System.out.println("minimumVal = " + minimumVal);
        minimumVal = rangeQueryBacktracking.findMinimumInRange(1, 4);
        System.out.println("minimumVal = " + minimumVal);
        minimumVal = rangeQueryBacktracking.findMinimumInRange(2, 5);
        System.out.println("minimumVal = " + minimumVal);
    }

    private double log2(int value) {
        return Math.log(value) / Math.log(2);
    }

    private void computeSolutionArray() {
        for (int i = 0; i < array.length; i++) {
            sparseTable[i][0] = i;
        }
        for (int column = 1; column < noOfColoumsInSparseTable; column++) {
            for (int row = 0; row + Math.pow(2, column) - 1 < array.length; row++) {
                if (array[sparseTable[row][column - 1]] < array[sparseTable[(int) (row + Math.pow(2, column - 1))][column - 1]]) {
                    sparseTable[row][column] = sparseTable[row][column - 1];
                } else {
                    sparseTable[row][column] = sparseTable[(int) (row + Math.pow(2, column - 1))][column - 1];
                }
            }
        }
    }

    private int findMinimumInRange(int startIndex, int endIndex) {
        int noOfElementsInTheRange = endIndex - startIndex + 1;
        int k = (int) log2(noOfElementsInTheRange);
        int val1 = array[sparseTable[startIndex][k]];
        int remainingElements = (int) (noOfElementsInTheRange - Math.pow(2, k));
        int val2 = array[sparseTable[startIndex + remainingElements][k]];
        return Math.min(val1, val2);
    }
}
