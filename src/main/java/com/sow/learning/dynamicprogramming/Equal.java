package com.sow.learning.dynamicprogramming;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Equal {


    public static void main(String[] args) {

        Equal equal = new Equal();
        int solve = equal.solve(Arrays.asList(53,361,188,665,786,898,447,562,272,123,229,629,670,848,994,54,822,46,208,17,449,302,466,832,931,778,156,39,31,777,749,436,138,289,453,276,539,901,839,811,24,420,440,46,269,786,101,443,832,661,460,281,964,278,465,247,408,622,638,440,751,739,876,889,380,330,517,919,583,356,83,959,129,875,5,750,662,106,193,494,120,653,128,84,283,593,683,44,567,321,484,318,412,712,559,792,394,77,711,977,785,146,936,914,22,942,664,36,400,857));
        System.out.println("solve = " + solve);
    }




    private int solve(List<Integer> arr) {
        int min = arr.stream().mapToInt(v->v).min().orElse(0);
        int totalValue= 0;
        int[] x= new int[]{0,0,0,0,0};
        for (Integer integer : arr) {
            for (int i = 0; i < x.length; i++) {
                int val = integer - (min-i);
                x[i]+=val/5+ (val%5)/2+(val%5)%2;
            }
        }
        return  IntStream.of(x).min().orElse(0);
    }
}
