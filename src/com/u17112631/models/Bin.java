package com.u17112631.models;

import com.u17112631.helpers.PlacementAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Bin
{
    private final int width;
    private final int height;
    private List<Piece> pieces;
    private int id;

    public Bin(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.pieces = new ArrayList<>();
    }

    public int getNumberOfPiecesInside()
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

    public Bin getCopy() {
        var newBin = new Bin(width, height);

        newBin.setPieces(getPieces());
        return newBin;
    }

    public boolean canFitPiece(Piece objectToPlace) {
        return PlacementAlgorithm.canFitPiece(getCopy(),objectToPlace);
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void addPiece(Piece newPiece) {
        pieces.add(newPiece);
    }
}