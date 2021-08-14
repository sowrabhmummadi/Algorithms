package com.sow.learning.rangequery;

import com.sow.learning.helper.MatrixPrettyPrint;

/**
 * Range Minimum query using dynamic programming
 *
 * Problem : give an array/list and the index range  , find the minimum value within the range.
 *  there can be multiple ranges to work with
 *
 * Preparation complexity: O(N^2)
 * Retrieval complexity: O(1)
 * Retrieval for 'M' queries: O(M)
 */

public class RangeQueryDP {

        private final int[] array;
        private final int[][] solutionArray;

        public RangeQueryDP(int[] array) {
            this.array =  array;
            this.solutionArray = new int[array.length][array.length];
            computeSolutionArray();
            new MatrixPrettyPrint(System.out).print(solutionArray);
        }

    private void computeSolutionArray() {
        for (int i = 0; i <solutionArray.length; i++) {
            for (int j = i; j < solutionArray.length; j++) {
                if(i==j){
                    solutionArray[i][j]= array[i];
                }
                else{
                    solutionArray[i][j] = Math.min(solutionArray[i][j-1],array[j]);
                }
            }
        }
    }

    public static void main(String[] args) {
            RangeQueryDP rangeQueryBacktracking = new RangeQueryDP(new int[]{7,6,1,3,4,2,9});
            int minimumVal = rangeQueryBacktracking.findMinimumInRange(3, 6);
            System.out.println("minimumVal = " + minimumVal);
            minimumVal = rangeQueryBacktracking.findMinimumInRange(1, 4);
            System.out.println("minimumVal = " + minimumVal);
            minimumVal = rangeQueryBacktracking.findMinimumInRange(2, 5);
            System.out.println("minimumVal = " + minimumVal);
        }

        private int findMinimumInRange(int startIndex, int endIndex) {
           return solutionArray[startIndex][endIndex];
        }
    }
