package com.u17112631.models;

import com.u17112631.helpers.Coordinate;

import java.util.ArrayList;
import java.util.Comparator;

public class Piece
{
    private final ArrayList<Coordinate> coords;
    private int[][] shape;
    private final int id;

    public Piece(int id)
    {
        coords = new ArrayList<>();
        this.id = id;
    }

    public void addCoord(int x, int y)
    {
        coords.add(new Coordinate(x,y));
    }

    public void addCoord(Coordinate coord)
    {
        coords.add(coord);
    }

    public String getInfo()
    {
        var builder = new StringBuilder();

        for (var i = 0; i < coords.size(); i++)
        {
            var coords = this.coords.get(i);
            builder.append(i).append("-").append(coords.getX()).append(":").append(coords.getY());
        }

        return builder.toString();
    }

    public Piece fillShape() {
        var height = getHeight();
        var width = getWidth();

        shape = new int[width][height];

        return this;
    }

    public int getWidth() {
        var maxXcoord = coords.stream().max(Comparator.comparing(Coordinate::getX)).get();
        var minXcoord = coords.stream().min(Comparator.comparing(Coordinate::getX)).get();

        return maxXcoord.getX() - minXcoord.getX();
    }

    public int getHeight() {
        var maxYcoord = coords.stream().max(Comparator.comparing(Coordinate::getY)).get();
        var minYcoord = coords.stream().min(Comparator.comparing(Coordinate::getY)).get();

        return maxYcoord.getY() - minYcoord.getY();
    }

    public ArrayList<Coordinate> getCoords() {
        return coords;
    }

    public Piece getCopy() {
        var newPiece = new Piece(id);

        for (var coord : coords){
            newPiece.addCoord(coord.GetCopy());
        }

        return newPiece;
    }

    public int getId() {
        return id;
    }

    public double getRectangularArea() {
        return getHeight() * getWidth();
    }
}