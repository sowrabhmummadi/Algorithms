package com.sow.learning.dynamicprogramming;


import com.sow.learning.helper.MatrixPrettyPrint;

import java.util.Map;

/**
 * The Longest Common Subsequence (LCS) problem is finding the longest
 * subsequence present in given two sequences in the same order
 * EG:
 * X:ABCBDAB
 * Y:BDCABA
 * the length is 4
 * LCS: BDAB,BCAB,BCBA
 * <p>
 * solution notes:
 * --> case1: X,Y end in same element
 * eg: X:ABCD, Y: CBD
 * LCS(ABCD,CBD)= LCS(ABC,CB) + D
 * --> case2: X,Y does not end in same element
 * eg: X: ABCD Y: CDAB
 * LCS(ABCD,CDAB)= Maximum(LCS(ABC,CDAB),LCS(ABCD,CDA))
 * <p>
 * and so on as recursive relation
 */
public class LongestCommonSubsequence {

    public LongestCommonSubsequence() {
    }

    public static void main(String[] args) {
        LongestCommonSubsequence LCS = new LongestCommonSubsequence();
        final int allSeq = LCS.findAllSeq("ABCBDA", "BDCABA");
        System.out.println("allSeq = " + allSeq);

    }

    private int findAllSeq(String X, String Y) {

        int returnVal = lcsTabular(X, Y);
        return returnVal;
    }

    private int lcs(String seq1, String seq2) {
        if (seq1.length() == 0 || seq2.length() == 0) {
            return 0;
        }

        if (getlastChar(seq1) == getlastChar(seq2)) {
            return lcs(removeLastChar(seq1), removeLastChar(seq2)) + 1;
        } else {
            return Math.max(lcs(removeLastChar(seq1), seq2), lcs(seq1, removeLastChar(seq2)));
        }
    }

    private char getlastChar(String s) {
        return s.charAt(s.length() - 1);
    }

    private String removeLastChar(String s) {
        return s.substring(0, s.length() - 1);
    }

    private int lcsMemoize(String seq1, String seq2, Map<String, Integer> lookupMap) {

        if (seq1.length() == 0 || seq2.length() == 0) {
            return 0;
        }
        String key = seq1.length() + "|" + seq2.length();
        if (!lookupMap.containsKey(key)) {
            if (getlastChar(seq1) == getlastChar(seq2)) {
                lookupMap.put(key, lcsMemoize(removeLastChar(seq1), removeLastChar(seq2), lookupMap) + 1);
            } else {
                lookupMap.put(key, Math.max(lcsMemoize(removeLastChar(seq1), seq2, lookupMap),
                        lcsMemoize(seq1, removeLastChar(seq2), lookupMap)));
            }
        }
        return lookupMap.get(key);
    }

    private int lcsTabular(String seq1, String seq2) {
        int[][] lookupTable = new int[seq1.length() + 1][seq2.length() + 1];

        for (int i = 1; i <= seq1.length(); i++) {
            for (int j = 1; j <= seq2.length(); j++) {
                if (seq1.charAt(i - 1) == seq2.charAt(j - 1)) {
                    lookupTable[i][j] = lookupTable[i - 1][j - 1] + 1;
                } else {
                    lookupTable[i][j] = Integer.max(lookupTable[i][j - 1], lookupTable[i - 1][j]);
                }
            }
        }
        new MatrixPrettyPrint(System.out).print(lookupTable);
        return lookupTable[seq1.length()][seq2.length()];
    }

}
