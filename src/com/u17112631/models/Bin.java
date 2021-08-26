package com.u17112631.models;

import java.util.List;

public class Bin
{
    private final int _numberOfObjects;
    private final int _width;
    private final int _height;
    private List<Piece> pieces;

    public Bin(int numberOfObjects, int width, int height)
    {
        _numberOfObjects = numberOfObjects;
        _width = width;
        _height = height;
    }

    public int GetNumberOfPiecesInside()
    {
        return _numberOfObjects;
    }

    public int getWidth() {
        return _width;
    }

    public int getHeight() {
        return _height;
    }

    public void setPieces(List<Piece> piecesInBin) {
        pieces = piecesInBin;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

}