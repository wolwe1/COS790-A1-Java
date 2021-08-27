package com.u17112631.models;

import com.u17112631.helpers.Coordinate;

import java.util.ArrayList;
import java.util.Comparator;

public class Piece
{
    private final ArrayList<Coordinate> _coords;
    private int[][] shape;

    public Piece()
    {
        _coords = new ArrayList<>();
    }

    public void AddCoord(int x, int y)
    {
        _coords.add(new Coordinate(x,y));
    }
    public String GetInfo()
    {
        var builder = new StringBuilder();

        for (var i = 0; i < _coords.size(); i++)
        {
            var coords = _coords.get(i);
            builder.append(i).append("-").append(coords.getX()).append(":").append(coords.getY());
        }

        return builder.toString();
    }

    public Piece FillShape() {
        var height = getHeight();
        var width = getWidth();

        shape = new int[width][height];

        return this;
    }

    private int getWidth() {
        var maxXcoord = _coords.stream().max(Comparator.comparing(Coordinate::getX)).get();
        var minXcoord = _coords.stream().min(Comparator.comparing(Coordinate::getX)).get();

        return maxXcoord.getY() - minXcoord.getY();
    }

    private int getHeight() {
        var maxYcoord = _coords.stream().max(Comparator.comparing(Coordinate::getY)).get();
        var minYcoord = _coords.stream().min(Comparator.comparing(Coordinate::getY)).get();

        return maxYcoord.getY() - minYcoord.getY();
    }

    public ArrayList<Coordinate> getCoords() {
        return _coords;
    }
}