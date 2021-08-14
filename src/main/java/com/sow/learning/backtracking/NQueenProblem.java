package com.sow.learning.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution for the N queen problem using the brute force ,
 * Backtracking algorithm
 * <p>
 * Question: Place N Queens in NXN matrix
 * where queen should not attack each other
 * and nth Queen can only be placed on the nth row
 * eg: Queen1 con only be placed on the 1st row.
 * <p>
 * Problem type: Enumeration problem - where we find all the feasible solutions
 * Time Complexity: O(n^2)
 */
public class NQueenProblem {

    List<int[]> solutionList = new ArrayList<int[]>();
    int noOfQueens;


    public NQueenProblem(int noOfQueens) {
        this.noOfQueens = noOfQueens;
    }

    public static void main(String[] args) {
        int noOfQueens = 4;
        NQueenProblem nQueenProblem = new NQueenProblem(noOfQueens);
        nQueenProblem.findAllPossibleSolutions();

    }

    void solve(int N, int[] solutionArray, int rowOrQueenNumber) {
        if (rowOrQueenNumber != N) {
            for (int columnNumber = 0; columnNumber < N; columnNumber++) {
                if (isValidColumn(columnNumber, rowOrQueenNumber, solutionArray)) {
                    solutionArray[columnNumber] = rowOrQueenNumber;
                    solve(N, solutionArray, rowOrQueenNumber+1);
                    solutionArray[columnNumber] = -1;
                }
            }
        } else {
            System.out.println(Arrays.toString(solutionArray));
            solutionList.add(solutionArray);
        }

    }

    /**
     * bounding function - verifying the constraint
     *
     * @param columnNumber
     * @param rowOrQueenNumber
     * @param solutionArray
     * @return
     */
    private boolean isValidColumn(int columnNumber, int rowOrQueenNumber, int[] solutionArray) {
        if (isQueenAlreadyPresentInTheRow(columnNumber, solutionArray)) {
            return false;
        } else return !isQueenPresentInDiagonal(rowOrQueenNumber, columnNumber, solutionArray);
    }

    private boolean isQueenPresentInDiagonal(int rowOrQueenNumber, int columnNumber, int[] solutionArray) {
        int rightDiagonalIndex = rowOrQueenNumber + columnNumber;
        int leftDiagonalIndex = rowOrQueenNumber - columnNumber;
        for (int i = 0; i < solutionArray.length; i++) {
            if (solutionArray[i] != -1) {
                if (solutionArray[i] + i == rightDiagonalIndex) {
                    return true;
                } else if (solutionArray[i] - i == leftDiagonalIndex) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isQueenAlreadyPresentInTheRow(int queenNumber, int[] solutionArray) {
        return solutionArray[queenNumber] != -1;
    }

    public void findAllPossibleSolutions() {

        int[] solutionArray = new int[noOfQueens];
        Arrays.fill(solutionArray, -1);
        solve(noOfQueens, solutionArray, 0);
    }
}
