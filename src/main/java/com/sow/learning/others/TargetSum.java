package com.sow.learning.others;

import java.util.ArrayList;

/**
 * Problem statement: Given an array of integers and a value,
 * determine if there are any three integers in the array whose sum equals the given value.
 */
public class TargetSum {

    public static void main(String[] args) {
        TargetSum targetSum = new TargetSum();
        targetSum.find(new int[]{3, 7, 1, 2, 8, 4, 5}, 21, 3);
    }

    private boolean find(int[] inputArray, int sum, int noOfElementsToConsider) {
        ArrayList<Integer>[] solutionList = new ArrayList[sum + 1];
        solutionList[0] = new ArrayList<>();
        for (int i = 0; i < sum; i++) {
            if (solutionList[i] != null) {
                for (int inputValue : inputArray) {
                    final int nextIndex = i + inputValue;
                    if (nextIndex < solutionList.length) {
                        if (!solutionList[i].contains(inputValue)) {
                            final ArrayList newCombination = new ArrayList(solutionList[i]);
                            newCombination.add(inputValue);
                            if (solutionList[nextIndex] != null) {
                                if (newCombination.size() == noOfElementsToConsider) {
                                    solutionList[nextIndex] = newCombination;
                                } else {
                                    System.out.println("ignored newCombination for = " + inputValue + " --> " + newCombination);
                                }
                            } else {
                                solutionList[nextIndex] = newCombination;
                            }
                        }
                    }
                }

            }
        }
        for (int i = 0; i < solutionList.length; i++) {
            System.out.println(i + " --> " + solutionList[i]);
        }
        return false;
    }
}
