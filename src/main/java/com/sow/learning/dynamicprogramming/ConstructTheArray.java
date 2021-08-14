package com.sow.learning.dynamicprogramming;

public class ConstructTheArray {

    public static long countArray(int n, int k, int x) {
        long count = 1;
        long kCopy = k;
        if (k <= n) {
            for (int i = 1; i < n - 1; i++,kCopy--) {
                if(kCopy <= 0){
                    count+=(k-1);
                }
                else{
                    count+=(kCopy-i);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long l = ConstructTheArray.countArray(761, 99, 1);
        System.out.println("l = " + l);
    }
}
