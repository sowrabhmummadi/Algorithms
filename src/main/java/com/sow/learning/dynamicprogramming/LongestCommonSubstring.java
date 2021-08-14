package com.sow.learning.dynamicprogramming;

import com.sow.learning.helper.MatrixPrettyPrint;

/**
 * problem: find the longest string (or strings) that is a  substring of two string
 * <p>
 * tabular solution
 * Given X,Y are strings
 * LCSuffix[i][j] ==  LCSuffix[i][j]+1 if X[i]==Y[i]
 * | or-> 0
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        LongestCommonSubstring longestCommonSubstring = new LongestCommonSubstring();
        longestCommonSubstring.find("ABABC", "BABCA");
    }

    private void find(String seq1, String seq2) {
        final int lcs = lcs(seq1, seq2);
        System.out.println("lcs = " + lcs);
    }

    private int lcs(String seq1, String seq2) {
        int[][] lookupTable = new int[seq1.length() + 1][seq1.length() + 1];
        int maxLength = Integer.MIN_VALUE;
        for (int i = 1; i < seq1.length(); i++) {
            for (int j = 1; j < seq2.length(); j++) {
                if (seq1.charAt(i - 1) == seq1.charAt(j - 1)) {
                    lookupTable[i][j] = lookupTable[i - 1][j - 1] + 1;
                    if (lookupTable[i][j] > maxLength) {
                        maxLength = lookupTable[i][j];
                    }
                }
            }
        }
        new MatrixPrettyPrint(System.out).print(lookupTable);
        return maxLength;
    }


}
