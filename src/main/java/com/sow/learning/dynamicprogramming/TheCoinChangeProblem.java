package com.sow.learning.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an amount and the denominations of coins available, determine how many ways change can be made for amount. There is a limitless supply of each coin type.
 * Example
 * <p>
 * <p>
 * There are  ways to make change for : , , and .
 * Function Description
 * Complete the getWays function in the editor below.
 * getWays has the following parameter(s):
 * int n: the amount to make change for
 * int c[m]: the available coin denominations
 * Returns
 * int: the number of ways to make change
 * Input Format
 * The first line contains two space-separated integers  and , where:
 * is the amount to change
 * is the number of coin types
 * The second line contains  space-separated integers that describe the values of each coin type.
 * Constraints
 * <p>
 * <p>
 * <p>
 * Each  is guaranteed to be distinct.
 */
public class TheCoinChangeProblem {

    public static void main(String[] args) {
        TheCoinChangeProblem coinChangeProblem = new TheCoinChangeProblem();
        coinChangeProblem.findAllTabSolutions(4, new long[]{3, 2, 1});
//      coinChangeProblem.findAllTabSolutions(10, new long[]{2, 5, 3, 6});
//       coinChangeProblem.findAllTabSolutions(166, new long[]{5,37,8,39,33,17,22,32,13,7,10,35,40,2,43,49,46,19,41,1,12,11,28});
    }

    public void findAllSolutions(int value, int[] coinDenominations) {
        ArrayList<List<Integer>> allSolutionsList = new ArrayList<>();
        solve(value, coinDenominations, new ArrayList<>(), allSolutionsList);
        System.out.println("allSolutionsList = " + allSolutionsList);

    }

    private void solve(int value, int[] coinDenominations, List<Integer> solutionList, List<List<Integer>> allSolutionsList) {
        if (value == 0) {
            allSolutionsList.add(new ArrayList<>(solutionList));
        } else {
            for (int coinDenomination : coinDenominations) {
                int reducedValue = value - coinDenomination;
                if (reducedValue >= 0) {
                    solutionList.add(coinDenomination);
                    solve(reducedValue, coinDenominations, solutionList, allSolutionsList);
                    solutionList.remove(solutionList.size() - 1);
                }
            }
        }
    }


    public void findAllTabSolutions(int value, long[] coinDenominations) {
        long i = solveTab(value, coinDenominations);
        System.out.println("i = " + i);
    }

    private long solveTab(int value, long[] coinDenominations) {

        long[] table = new long[value+1];

        // Initialize all table values as 0
        Arrays.fill(table, 0);   //O(value)

        // Base case (If given value is 0)
        table[0] = 1;

        for (int i=0; i<coinDenominations.length; i++)
            for (int j = (int) coinDenominations[i]; j<=value; j++)
            {table[j] += table[(int) (j-coinDenominations[i])];
                System.out.println("denomination = " + coinDenominations[i] +" value = " + j);
                System.out.println("table = " + Arrays.toString(table));
            }

        return table[value];
    }
}
