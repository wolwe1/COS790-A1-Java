package com.u17112631.models;

import java.util.ArrayList;
import java.util.List;

public class Bin
{
    private final int width;
    private final int height;
    private List<Piece> pieces;

    public Bin(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public int GetNumberOfPiecesInside()
    {
        return pieces.size();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setPieces(List<Piece> piecesInBin) {
        pieces = piecesInBin;
    }

    public List<Piece> getPieces() {
        var newPieces = new ArrayList<Piece>();

        for(var piece : pieces){
            newPieces.add(piece.getCopy());
        }
        return newPieces;
    }

    public Bin GetCopy() {
        var newBin = new Bin(width, height);

        newBin.setPieces(getPieces());
        return newBin;
    }
}