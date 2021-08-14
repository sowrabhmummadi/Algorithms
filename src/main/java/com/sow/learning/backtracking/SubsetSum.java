package com.sow.learning.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * *** not efficient solution -- can be made efficient using dynamic programming.
 * Question: Given a set of N numbers and a value K find the not contagious subset of
 * give set whose sum is equal to K
 * <p>
 * for example: give set=[5,3,2,4,7] and K = 7
 * few of the possible answers is [5,2],[3,4].....
 * <p>
 * Problem type: Enumeration problem- find all subsets
 * Time complexity = 2^n
 */
public class SubsetSum {
    int[] set;
    int totalSum;

    public SubsetSum(int[] set) {
        this.set = set;
        totalSum = Arrays.stream(set).sum();
    }

    public static void main(String[] args) {
        SubsetSum subsetSum = new SubsetSum(new int[]{5, 3, 2, 4, 1});
        subsetSum.findAllSubsets(6);
    }

    private void solve(int value, int actualSum, int remainingSum,
                      List<Integer> selectedElements,
                      int setIndex) {
        if (actualSum == value) {
            System.out.println(selectedElements.stream()
                    .map(index->set[index])
                    .collect(Collectors.toList()));
        } else {
            if (setIndex < set.length) {
                if ((set[setIndex] + actualSum) <= value) {
                    selectedElements.add(setIndex);
                    solve(value, set[setIndex] + actualSum,
                            remainingSum - set[setIndex], selectedElements,
                            setIndex + 1);
                    selectedElements.remove(selectedElements.size()-1);
                }
                solve(value, actualSum, remainingSum - set[setIndex], selectedElements,
                        setIndex + 1);
            }
        }
    }

    public void findAllSubsets(int value) {
        solve(value, 0, totalSum, new ArrayList<>(), 0);
    }

}
