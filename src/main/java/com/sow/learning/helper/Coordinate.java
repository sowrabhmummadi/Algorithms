package com.sow.learning.helper;

public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Coordinate){
            return ((Coordinate) obj).x == x && ((Coordinate) obj).y == y;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", x, y);
    }
}
