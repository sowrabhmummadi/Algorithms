package com.sow.learning.search;

/**
 * Problem Statement
 * Suggest Edit
 * Aahad and Harshit always have fun by solving problems.
 * Harshit took a sorted array and rotated it clockwise by an unknown amount.
 * For example, he took a sorted array = [1, 2, 3, 4, 5] and if he rotates it by 2, then the array becomes: [4, 5, 1, 2, 3].
 * After rotating a sorted array, Aahad needs to answer Q queries asked by Harshit,
 * each of them is described by one integer Q[i] which Harshit wanted him to search in the array.
 * For each query, if he founds it, he had to shout the index of the number, otherwise, he had to shout -1.
 * <p>
 * Notes: because we have to complete this is o(log n) time complexity we have to do a binary search
 */
public class RotatedSortedArray {
    public static void main(String[] args) {
        RotatedSortedArray rotatedSortedArray = new RotatedSortedArray();
        final int position = rotatedSortedArray.findValue(new int[]{4, 5, 1, 2, 3}, 2);
        System.out.println("position = " + position);

    }

    private int findValue(int[] rotatedSortedArrayInput, int elementToFind) {
        //find the pivot
        int left = 0;
        int right = rotatedSortedArrayInput.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (rotatedSortedArrayInput[mid] > rotatedSortedArrayInput[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int start = left;
        left = 0;
        right = rotatedSortedArrayInput.length - 1;
        if (elementToFind > rotatedSortedArrayInput[start] && elementToFind <= rotatedSortedArrayInput[right]) {
            left = start;
        } else {
            right = start;
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (rotatedSortedArrayInput[mid] == elementToFind) {
                return mid;
            }
            if (rotatedSortedArrayInput[mid] > elementToFind) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
