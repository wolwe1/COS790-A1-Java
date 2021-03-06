package com.u17112631.helpers;

public class Coordinate {
    private final Tuple<Integer,Integer> coord;


    public Coordinate(int x, int y) {
        coord = new Tuple<>(x,y);
    }

    public int getX(){
        return coord.Item1;
    }

    public int getY(){
        return coord.Item2;
    }

    public int setX(int x){
        return coord.Item1 = x ;
    }

    public int setY(int y){
        return coord.Item2 = y;
    }

    public Coordinate GetCopy() {
        return new Coordinate(coord.Item1,coord.Item2);
    }

    public Coordinate shift(int xAmount, int yAmount) {
        return new Coordinate(getX() + xAmount,getY() + yAmount);
    }
}
