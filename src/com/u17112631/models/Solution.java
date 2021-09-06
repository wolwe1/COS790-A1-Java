package com.u17112631.models;

import java.util.ArrayList;
import java.util.List;

public class Solution
{
    public final List<Bin> Bins;

    public Solution(List<Bin> bins)
    {
        Bins = bins;
    }

    public void summarise()
    {
        System.out.println(getInfo());
    }

    public String getInfo()
    {
        var builder = new StringBuilder();
        builder.append("\n**Solution**");
        builder.append("\nNumber of bins: " + Bins.size());
        builder.append("\nBins: " + getBinInfo());
        builder.append("\n**********");

        return builder.toString();
    }

    private String getBinInfo() {
        var builder = new StringBuilder();
        builder.append("\n");

        for (int i = 0, binsSize = Bins.size(); i < binsSize; i++) {
            Bin bin = Bins.get(i);
            builder.append("\tBin " + i +": ");
            builder.append("\t\tPieces: " + getPiecesInfo(bin));
        }
        return builder.toString();
    }

    private String getPiecesInfo(Bin bin)
    {
        var builder = new StringBuilder();
        builder.append("\n");

        for  (var piece : bin.getPieces())
        {
            builder.append("\t\t" + piece.getInfo() );
        }

        return builder.toString();
    }

    public Solution getCopy() {
        return new Solution(getBins());
    }

    private List<Bin> getBins() {
        var newBins = new ArrayList<Bin>();

        for (var bin : Bins){
            newBins.add(bin.GetCopy());
        }
        return newBins;
    }
}
