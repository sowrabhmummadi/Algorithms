package com.sow.learning.backtracking;

import com.sow.learning.helper.Coordinate;
import com.sow.learning.helper.MatrixPrettyPrint;

import java.util.Arrays;
import java.util.List;

/**
 * Give a maze of size M X N and the rat is always placed in the
 * source cell (0,0) and the rat is supposed to reach the destination cell (n-1,m-1)
 * constraint: the rat cannot only move right or down
 *
 *
 * Problem type: Enumeration problem (find all possible solutions)
 *
 * complexity: O(2^n)
 */
public class RatInTheMaze {

    int mazeLength;
    int mazeHeight;
    int[][] maze;

    public RatInTheMaze(int mazeLength, int mazeHeight) {
        this.mazeLength = mazeLength;
        this.mazeHeight = mazeHeight;
        maze = new int[mazeLength][mazeHeight];
    }

    public void findAllSolutions(){
        solve(0,0,1);
    }

    private void solve(int x,int y, int stepNumber){
        if (x == mazeLength-1 && y == mazeHeight -1){
            System.out.println();
            new MatrixPrettyPrint(System.out).print(maze);
            System.out.println();
        }
        for (Coordinate possiblePosition : getPossiblePositions(x, y)) {
            if(isValidPosition(possiblePosition)){
                maze[possiblePosition.x][possiblePosition.y]=stepNumber;
                solve(possiblePosition.x,possiblePosition.y,stepNumber+1);
                maze[possiblePosition.x][possiblePosition.y]= -1;
            }
        }


    }

    private boolean isValidPosition(Coordinate possiblePosition) {
        return possiblePosition.x <= mazeLength-1 && possiblePosition.y<=mazeHeight-1;
    }

    public List<Coordinate> getPossiblePositions(int x, int y) {
        return Arrays.asList(new Coordinate(x +1, y),
                new Coordinate(x, y+1));
    }

    public static void main(String[] args) {
        RatInTheMaze ratInTheMaze= new RatInTheMaze(5,4);
        ratInTheMaze.findAllSolutions();
    }
}
