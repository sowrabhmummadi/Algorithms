package com.sow.learning.backtracking;

import com.sow.learning.helper.MatrixPrettyPrint;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuSolver {
    private final int sudokuEmptyId = 0;
    private final int[][] sudoku;
    private final int sudokuSubSize;

    public SudokuSolver(int[][] sudoku) {
        this.sudoku = sudoku;
        this.sudokuSubSize = (int) Math.sqrt(sudoku.length);
    }

    public static void main(String[] args) {
       SudokuSolver sudokuSolver = new SudokuSolver(new int[][]{
                {0, 0, 7, 5, 0, 0, 0, 0, 3},
                {0, 0, 8, 0, 0, 0, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 8, 0, 7},
                {0, 0, 0, 2, 0, 0, 4, 0, 0},
                {9, 2, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 3, 0, 0, 5, 0},
                {0, 0, 0, 3, 8, 7, 0, 0, 0},
                {4, 0, 1, 0, 0, 5, 0, 0, 0},
                {6, 0, 0, 0, 0, 4, 0, 0, 0}
        });
        sudokuSolver.findAllSolutions();
    }

    public void findAllSolutions() {
        solve(0, 0);
    }

    private void solve(int rowId, int columnId) {
        if (columnId == sudoku.length) {
            rowId += 1;
            columnId = 0;
        }
        if (isFilled()) {
            System.out.println();
            new MatrixPrettyPrint(System.out).print(sudoku);
            System.out.println();
            System.out.println("------------------------------------------");
        } else {
            if (sudoku[rowId][columnId] == sudokuEmptyId) {
                List<Integer> allPossibilities = findAllPossibilities(rowId, columnId);
                for (Integer possibility : allPossibilities) {
                    sudoku[rowId][columnId] = possibility;
                    solve(rowId, columnId + 1);
                    sudoku[rowId][columnId] = sudokuEmptyId;
                }
            } else {
                solve(rowId, columnId + 1);
            }
        }
    }

    private List<Integer> findAllPossibilities(int rowId, int columnId) {
        return IntStream.range(1, sudoku.length + 1)
                .filter(value -> !isInRow(rowId, value))
                .filter(value -> !isInColumn(columnId, value))
                .filter(value -> !isInCube(rowId, columnId, value))
                .boxed()
                .collect(Collectors.toList());
    }

    private boolean isFilled() {
        for (int[] ints : sudoku) {
            for (int j = 0; j < sudoku.length; j++) {
                if (ints[j] == sudokuEmptyId) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isInRow(int rowId, int value) {
        int[] row = sudoku[rowId];
        for (int element : row) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInColumn(int columnId, int value) {
        for (int[] ints : sudoku) {
            if (ints[columnId] == value) {
                return true;
            }
        }
        return false;
    }

    private boolean isInCube(int rowId, int columnId, int value) {
        int subsectionRowStart = (rowId / sudokuSubSize) * sudokuSubSize;
        int subsectionRowEnd = subsectionRowStart + sudokuSubSize;

        int subsectionColumnStart = (columnId / sudokuSubSize) * sudokuSubSize;
        int subsectionColumnEnd = subsectionColumnStart + sudokuSubSize;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (sudoku[r][c] == value) {
                    return true;
                }
            }
        }
        return false;
    }
}
