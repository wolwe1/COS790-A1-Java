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
}
