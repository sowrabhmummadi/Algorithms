package com.sow.learning.dynamicprogramming;

import com.sow.learning.helper.MatrixPrettyPrint;

public class SubsetSum {

    private boolean canSubSet(int expectedSum, int[] values){
        boolean[][] table= new boolean[values.length+1][expectedSum+1];
        for (int valueIndex = 0; valueIndex < table.length; valueIndex++) {
            for (int sum = 0; sum < expectedSum+1; sum++) {
                if(valueIndex==0){
                    //if the sum is 0 we can always pick the empty subset true
                    //value 0 will not affect the sum so always true
                    table[valueIndex][sum]=false;
                }
                else if(sum==0){
                    table[valueIndex][sum]=true;
                }
                else {
                    if(values[valueIndex-1]<=sum){
                       table[valueIndex][sum]= table[valueIndex-1][sum]||table[valueIndex-1][sum-values[valueIndex-1]];
                    }
                    else {
                        table[valueIndex][sum]= table[valueIndex-1][sum];
                    }
                }

            }
        }
        for (int i = 0; i < table.length; i++) {
            System.out.print(i==0? " ":values[i-1] + " ");
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j]+" ");
            }
            System.out.println();
        }
        return true;
    }



    public static void main(String[] args) {
        SubsetSum subsetSum = new SubsetSum();
        subsetSum.canSubSet(9, new int[]{3,34,4,12,5,2});
    }
}
