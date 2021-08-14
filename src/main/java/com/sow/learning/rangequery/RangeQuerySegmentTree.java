package com.sow.learning.rangequery;

import java.util.Arrays;

/**
 * Range Minimum query using Segment Tree
 * <p>
 * Problem : give an array/list and the index range  , find the minimum value within the range.
 * there can be multiple ranges to work with
 * <p>
 * Preparation complexity: O(N^2)
 * Retrieval complexity: O(1)
 * Retrieval for 'M' queries: O(M)
 *
 * notes:
 *     -> storing the binary tree as array
 *          --> left child index-> 2 (current position)+1
 *          --> right child index-> 2 (current position)+2
 *     -> segment tree
 *          --> Breaking the input array into segments and finding the value
 *          --> Segment tree storage size
 *              ---> nearest power of 2 multiplied by 2 -1 (when the array size is not power of two some space is wasted)
 *     -> finding nearest power of '2'
 *          --> find the ceil of log base '2' power 2
 */

public class RangeQuerySegmentTree {

    private final int[] array;
    private final int[] solutionArray;


    public RangeQuerySegmentTree(int[] array) {
        this.array = array;
        this.solutionArray = new int[nextPowerOfTwo(array.length) * 2 - 1];
        computeSolutionArray();
        System.out.println("solutionArray = " + Arrays.toString(solutionArray));
    }

    public static void main(String[] args) {
        RangeQuerySegmentTree rangeQueryBacktracking = new RangeQuerySegmentTree(new int[]{7, 6, 1, 3, 4, 2, 9});
        int minimumVal = rangeQueryBacktracking.findMinimumInRange(3, 6);
        System.out.println("minimumVal = " + minimumVal);
        minimumVal = rangeQueryBacktracking.findMinimumInRange(1, 4);
        System.out.println("minimumVal = " + minimumVal);
        minimumVal = rangeQueryBacktracking.findMinimumInRange(2, 5);
        System.out.println("minimumVal = " + minimumVal);
    }

    private int nextPowerOfTwo(int value) {
        return (int) Math.pow(2, Math.ceil(Math.log(value) / Math.log(2)));
    }

    private void computeSolutionArray() {
        createSegmentTree(0, array.length - 1, 0);
    }

    private void createSegmentTree(int low, int high, int position) {
        if (low == high) {
            solutionArray[position] = array[low];
        } else {
            int mid = (low + high) / 2;
            createSegmentTree(low, mid, 2 * position + 1);
            createSegmentTree(mid + 1, high, 2 * position + 2);
            solutionArray[position] = Math.min(solutionArray[2 * position + 1], solutionArray[2 * position + 2]);
        }
    }

    private int findMinimumInRange(int startIndex, int endIndex) {
        return minRange(startIndex, endIndex, 0, array.length - 1, 0);
    }

    private int minRange(int queryLow, int queryHigh, int nodeStartIndex, int nodeEndIndex, int position) {
        if (queryLow <= nodeStartIndex && queryHigh >= nodeEndIndex) {
            //Total Overlap
            return solutionArray[position];
        }
        if (queryLow > nodeEndIndex || queryHigh < nodeStartIndex) {
            //no overlap
            return Integer.MAX_VALUE;
        }
        //Partial Overlap
        int mid = (nodeStartIndex + nodeEndIndex) / 2;
        int leftMin = minRange(queryLow, queryHigh, nodeStartIndex, mid, 2 * position + 1);
        int rightMin = minRange(queryLow, queryHigh, mid + 1, nodeEndIndex, 2 * position + 2);
        return Math.min(leftMin, rightMin);
    }
}
