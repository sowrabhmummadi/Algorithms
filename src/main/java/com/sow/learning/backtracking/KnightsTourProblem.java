package com.sow.learning.backtracking;

import com.sow.learning.helper.Coordinate;
import com.sow.learning.helper.MatrixPrettyPrint;

import java.util.Arrays;
import java.util.List;

/**
 * Solution for Knight tour problem using brute force backtracking
 * <br>
 * <u>Question:</u> Given a N X N chess board and an initial position of the knight k
 * the night should visit all the cells of the chess board given a knight can visit each cell only once.
 * <br>
 * Problem type: Enumeration problem - where we find all the feasible solutions
 * Time Complexity: O(8^n)
 */
public class KnightsTourProblem {

    int[][] chessBoard;
    int chessBoardSize;

    public KnightsTourProblem(int chessBoardSize) {
        this.chessBoardSize = chessBoardSize;
        chessBoard = new int[chessBoardSize][chessBoardSize];
        for (int i = 0; i < chessBoardSize; i++) {
            for (int j = 0; j < chessBoardSize; j++) {
                chessBoard[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) {
        int chessBoardSize = 5;
        int initialPositionX = 1;
        int initialPositionY = 1;
        KnightsTourProblem knightsTourProblem = new KnightsTourProblem(chessBoardSize);
        knightsTourProblem.findAllSolutions(initialPositionX, initialPositionY);

    }

    private void findAllSolutions(int initialPositionX, int initialPositionY) {
        chessBoard[initialPositionX][initialPositionY] = 0;
        solve(initialPositionX, initialPositionY, 1);
    }

    private void solve(int initialPositionX, int initialPositionY, int filledNumber) {

        if (filledNumber == chessBoardSize * chessBoardSize) {
            new MatrixPrettyPrint(System.out).print(chessBoard);
            System.out.println();
            System.out.println();
            System.out.println();
        } else {
            List<Coordinate> possiblePositions = getPossiblePositions(initialPositionX, initialPositionY);
            for (Coordinate possiblePosition : possiblePositions) {
                if (isValidPosition(possiblePosition)) {
                    chessBoard[possiblePosition.x][possiblePosition.y] = filledNumber;
                    solve(possiblePosition.x, possiblePosition.y, filledNumber + 1);
                    chessBoard[possiblePosition.x][possiblePosition.y] = -1;
                }
            }
        }
    }

    private boolean isValidPosition(Coordinate possiblePosition) {
        if (possiblePosition.x < 0
                || possiblePosition.y < 0
                || possiblePosition.x > chessBoardSize-1
                || possiblePosition.y > chessBoardSize-1
        ) {
            return false;
        }
        return chessBoard[possiblePosition.x][possiblePosition.y] == -1;
    }

    public List<Coordinate> getPossiblePositions(int x, int y) {
        return Arrays.asList(new Coordinate(x - 2, y - 1),
                new Coordinate(x - 2, y + 1),
                new Coordinate(x + 2, y + 1),
                new Coordinate(x + 2, y - 1),
                new Coordinate(x + 1, y + 2),
                new Coordinate(x - 1, y + 2),
                new Coordinate(x - 1, y - 2),
                new Coordinate(x + 1, y - 2));
    }


}
