package com.u17112631.models;

import java.util.ArrayList;
import java.util.List;

public class ProblemSpecification
{
    private final int BinHeight;
    private final int BinWidth;

    public final List<Piece> Pieces;

    public ProblemSpecification(int binHeight, int binWidth, List<Piece> pieces)
    {
        BinHeight = binHeight;
        BinWidth = binWidth;
        Pieces = pieces;
    }


    public void Summarise()
    {
        System.out.println(GetInfo());
    }

    public String GetInfo()
    {
        var builder = new StringBuilder();
        builder.append("\n");
        builder.append("\n**Problem**");
        builder.append("\nBin: " + BinHeight + "x" + BinWidth);
        builder.append("\nPieces: " + GetPiecesInfo());
        builder.append("\n**********");

        return builder.toString();
    }

    private String GetPiecesInfo()
    {
        var builder = new StringBuilder();

        builder.append("\n");
        for  (var piece : Pieces)
        {
            builder.append("\t{piece.GetInfo()}");
        }

        return builder.toString();
    }

    public ProblemSpecification GetCopy() {
        return new ProblemSpecification(getBinHeight(),getBinWidth(),getPieces());
    }

    public int getBinHeight() {
        return BinHeight;
    }

    public int getBinWidth() {
        return BinWidth;
    }

    public List<Piece> getPieces() {
        var newPieces = new ArrayList<Piece>();

        for (var piece : Pieces){
            newPieces.add(piece.getCopy());
        }

        return newPieces;
    }

    public ProblemSpecification removePiece(Piece pieceToRemove) {
        return new ProblemSpecification(getBinHeight(),getBinWidth(),getPiecesWithout(pieceToRemove));
    }

    private List<Piece> getPiecesWithout(Piece pieceToRemove) {
        var newPieces = new ArrayList<Piece>();

        for (var piece : Pieces){
            if(piece.getId() != pieceToRemove.getId())
            newPieces.add(piece.getCopy());
        }

        return newPieces;
    }

    public Piece getPiece(int largestPieceId) {
        for (var piece : Pieces){
            if(largestPieceId == piece.getId())
                return piece.getCopy();
        }
        throw new RuntimeException("Piece with ID " + largestPieceId + " could not be found");

    }
}
