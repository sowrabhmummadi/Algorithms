package com.sow.learning.dynamicprogramming;

public class InterleavingString {
    public static void main(String[] args) {
        InterleavingString interleavingString = new InterleavingString();
        final boolean interleaving = interleavingString.isInterleaving("ABC", "ACD", "ACDABC");
        System.out.println("interleaving = " + interleaving);
    }

    private boolean isInterleaving(String seq1, String seq2, String interleavedString) {
        if (seq1.isEmpty()) {
            if (seq2.isEmpty()) {
                return interleavedString.isEmpty();
            } else {
                return seq2.equals(interleavedString);
            }
        } else if (seq2.isEmpty()) {
            if (seq1.isEmpty()) {
                return interleavedString.isEmpty();
            } else {
                return seq1.equals(interleavedString);
            }
        }

        if (seq1.charAt(0) == interleavedString.charAt(0) &&
                seq2.charAt(0) == interleavedString.charAt(0)) {
            return isInterleaving(seq1.substring(1), seq2, interleavedString.substring(1)) ||
                    isInterleaving(seq1, seq2.substring(1), interleavedString.substring(1));
        } else if (seq1.charAt(0) == interleavedString.charAt(0)) {
            return isInterleaving(seq1.substring(1), seq2, interleavedString.substring(1));
        } else if (seq2.charAt(0) == interleavedString.charAt(0)) {
            return isInterleaving(seq1, seq2.substring(1), interleavedString.substring(1));
        } else {
            return false;
        }
    }


}
