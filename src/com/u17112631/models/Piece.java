package com.u17112631.models;

import com.u17112631.helpers.Coordinate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Piece
{
    private final ArrayList<Coordinate> vertices;
    private int[][] shape;
    private final int id;

    public Piece(int id)
    {
        vertices = new ArrayList<>();
        this.id = id;
    }

    public Piece addCoord(int x, int y)
    {
        vertices.add(new Coordinate(x,y));
        return this;
    }

    public void addCoord(Coordinate coord)
    {
        vertices.add(coord);
    }

    public String getInfo()
    {
        var builder = new StringBuilder();

        for (var i = 0; i < vertices.size(); i++)
        {
            var coords = this.vertices.get(i);
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
        var maxXcoord = vertices.stream().max(Comparator.comparing(Coordinate::getX)).get();
        var minXcoord = vertices.stream().min(Comparator.comparing(Coordinate::getX)).get();

        return maxXcoord.getX() - minXcoord.getX();
    }

    public int getHeight() {
        var maxYcoord = vertices.stream().max(Comparator.comparing(Coordinate::getY)).get();
        var minYcoord = vertices.stream().min(Comparator.comparing(Coordinate::getY)).get();

        return maxYcoord.getY() - minYcoord.getY();
    }

    public ArrayList<Coordinate> getVertices() {
        return vertices;
    }

    public Piece getCopy() {
        var newPiece = new Piece(id);

        for (var coord : vertices){
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

    public int getNumberOfVertices() {
        return vertices.size();
    }

    public List<Integer> getXCoordinates() {
        var xCoords = new ArrayList<Integer>();

        for (var coord : vertices){
            xCoords.add(coord.getX());
        }
        return xCoords;
    }

    public List<Integer> getYCoordinates() {
        var yCoords = new ArrayList<Integer>();

        for (var coord : vertices){
            yCoords.add(coord.getY());
        }
        return yCoords;
    }

    public void moveDown(int i) {
        for (var coord : vertices){
            coord.setY( coord.getY() - i);
        }
    }

    public void moveUp(int i) {
        for (var coord : vertices){
            coord.setY( coord.getY() + i);
        }
    }

    public void moveLeft(int i) {
        for (var coord : vertices){
            coord.setX( coord.getX() - i);
        }
    }

    public void moveRight(int i) {
        for (var coord : vertices){
            coord.setX( coord.getX() + i);
        }
    }

    public int getLowestXCoord() {
        return vertices.stream().min(Comparator.comparing(Coordinate::getX)).get().getX();
    }

    public int getHighestXCoord() {
        return vertices.stream().max(Comparator.comparing(Coordinate::getX)).get().getX();
    }

    public int getLowestYCoord() {
        return vertices.stream().min(Comparator.comparing(Coordinate::getY)).get().getY();
    }

    public int getHighestYCoord() {
        return vertices.stream().max(Comparator.comparing(Coordinate::getY)).get().getY();
    }
}