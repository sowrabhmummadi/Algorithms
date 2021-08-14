package com.sow.learning.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class SamAndSubstrings {


    public static void main(String[] args) {
        SamAndSubstrings samAndSubstrings = new SamAndSubstrings();
        int sum = samAndSubstrings.getSum("972698438521");
        System.out.println("sum = " + sum);
    }

    private int getSum(String s) {
        double mod = 1000000007;
        List<Long> a = new ArrayList<>();
        long sum = 0;
        for (int i = 0; i < s.length(); i++) {
            long numericValue = Character.getNumericValue(s.charAt(i));
            sum += numericValue;
            a.add(numericValue);
            int j = a.size() - 2;
            for (int k = 0; k < i; k++, j--) {
                long value = a.get(j) * 10 + numericValue;
                a.add(value);
                sum += value;
            }
        }
        return (int) (sum % mod);
    }
}
