package com.u17112631.models;

import java.util.List;

public class ProblemSpecification
{
    public final int BinHeight;
    public final int BinWidth;

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
}
