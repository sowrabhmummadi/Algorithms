package com.sow.learning.rangequery;

/**
 * Range Minimum query
 *
 * Problem : give an array/list and the index range  , find the minimum value within the range.
 *  there can be multiple ranges to work with
 *
 * Preparation complexity: O(1)
 * Retrieval complexity: O(n)
 * Retrieval for 'M' queries: O(MxN)
 */

public class RangeQueryBacktracking {

    private final int[] array;

    public RangeQueryBacktracking(int[] array) {
        this.array =  array;
    }

    public static void main(String[] args) {
        RangeQueryBacktracking rangeQueryBacktracking = new RangeQueryBacktracking(new int[]{7,6,1,3,4,2,9});
        int minimumVal = rangeQueryBacktracking.findMinimumInRange(3, 6);
        System.out.println("minimumVal = " + minimumVal);
        minimumVal = rangeQueryBacktracking.findMinimumInRange(1, 4);
        System.out.println("minimumVal = " + minimumVal);
         minimumVal = rangeQueryBacktracking.findMinimumInRange(2, 5);
        System.out.println("minimumVal = " + minimumVal);
    }

    private int findMinimumInRange(int startIndex, int endIndex) {
        int minNumber= Integer.MAX_VALUE;
        for (int i = startIndex; i <=endIndex; i++) {
            if(array[i]<minNumber)
            {
                minNumber = array[i];
            }
        }
        return minNumber;
    }
}
