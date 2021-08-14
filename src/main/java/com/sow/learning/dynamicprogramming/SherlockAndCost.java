package com.sow.learning.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * In this challenge, you will be given an array B and must determine an array A.
 * There is a special rule: For all i ,A[i]<=B.get(i).That is, A[i] can be any number you choose such that 1<=A[i]<=B.get(i).
 * Your task is to select a series of  given  such that the sum of the absolute difference of consecutive pairs of A is maximized.\
 * This will be the array's cost, and will be represented by the variable S below.
 * The equation can be written:
 *
 * S= i=2..n Sigma [a[i] - a[i-1]
 */
public class SherlockAndCost {

    public int get(List<Integer> B){

        int maxHi= 0;
        int maxLo= 0;

        for (int i = 1; i < B.size(); i++) {
          int loLo= maxLo;
          int loHi = Math.abs(1-B.get(i-1))+ maxHi;
          int hiLo = Math.abs(B.get(i)-1)+maxLo;
          int hiHi = Math.abs(B.get(i)-B.get(i-1))+maxHi;

          maxHi= Math.max(hiLo,hiHi);
          maxLo= loHi;
        }
        return Math.max(maxHi,maxLo);
    }

    public static void main(String[] args) {
        SherlockAndCost sherlockAndCost = new SherlockAndCost();
        int maxValue = sherlockAndCost.get(Arrays.asList(10,1,10,1,10));
        System.out.println("maxValue = " + maxValue);
    }
}
