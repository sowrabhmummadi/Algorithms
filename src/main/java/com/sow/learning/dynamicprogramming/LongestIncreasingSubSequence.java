package com.sow.learning.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubSequence {

    public int get(int[] sequence){
        int[] solutionArray = new int[sequence.length];
        Arrays.fill(solutionArray,1);
        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < i; j++) {
                if(sequence[j]<sequence[i]){
                    if(solutionArray[i]< solutionArray[j]+1){
                        solutionArray[i]= solutionArray[j]+1;
                    }
                }
            }
        }

        System.out.println("solutionArray = " + Arrays.toString(solutionArray));
        return 0;
    }

    public static void main(String[] args) {
        LongestIncreasingSubSequence l = new LongestIncreasingSubSequence();
        l.get(new int[]{10,22,9,33,21,50,41,60,80});
    }
}
