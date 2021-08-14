package com.sow.learning;

import com.sow.learning.helper.Coordinate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Problem Statement:
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1
 */
public class RottenOranges {
    public static void main(String[] args) {
        RottenOranges rottenOranges = new RottenOranges();
        final int minTimeToRot = rottenOranges.findMinTimeToRot(new int[][]{
                {0, 2}
        });
        System.out.println("minTimeToRot = " + minTimeToRot);
    }

    private int findMinTimeToRot(int[][] orangeMatrix) {
        Queue<Coordinate> rottenOrangesQueue = new LinkedList<>();
        int noOfFreshOranges = 0;
        for (int i = 0; i < orangeMatrix.length; i++) {
            for (int j = 0; j < orangeMatrix[i].length; j++) {
                if (orangeMatrix[i][j] == 2) {
                    rottenOrangesQueue.add(new Coordinate(i, j));
                } else if (orangeMatrix[i][j] == 1) {
                    noOfFreshOranges++;
                }
            }
        }
        int noOfNewlyRottenOranges = 0;
        int noOfMinutes = 0;
        while (!rottenOrangesQueue.isEmpty()) {
            //   new MatrixPrettyPrint(System.out).print(orangeMatrix);
            List<Coordinate> newlyRottenOrangesList = new ArrayList<>();
            for (Coordinate coordinate : rottenOrangesQueue) {
                newlyRottenOrangesList.addAll(rotAdjacentOranges(coordinate, orangeMatrix));
            }
            rottenOrangesQueue.clear();
            rottenOrangesQueue.addAll(newlyRottenOrangesList);
            noOfNewlyRottenOranges += newlyRottenOrangesList.size();
            noOfMinutes++;
        }
        if (noOfNewlyRottenOranges == noOfFreshOranges) {
            return noOfMinutes - 1;
        }
        return -1;
    }

    private List<Coordinate> rotAdjacentOranges(Coordinate rottenOrange, int[][] orangeMatrix) {
        return Stream.of(
                        new Coordinate(rottenOrange.x + 1, rottenOrange.y),
                        new Coordinate(rottenOrange.x - 1, rottenOrange.y),
                        new Coordinate(rottenOrange.x, rottenOrange.y + 1),
                        new Coordinate(rottenOrange.x, rottenOrange.y - 1)
                ).filter(c -> c.x >= 0 && c.x < orangeMatrix.length && c.y >= 0 && c.y < orangeMatrix[0].length)
                .filter(c -> orangeMatrix[c.x][c.y] == 1)
                .peek(c -> {
                    orangeMatrix[c.x][c.y] = 2;
                }).collect(Collectors.toList());
    }
}
