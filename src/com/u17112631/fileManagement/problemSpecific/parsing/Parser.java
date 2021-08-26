package com.u17112631.fileManagement.problemSpecific.parsing;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.helpers.Tuple;
import com.u17112631.models.Piece;

public abstract class Parser
{
    protected static Piece ParsePiece(String pieceInfo)
    {
        var items = pieceInfo.split(" ");
        var numberOfVertices = Integer.parseInt(items[0]);

        var piece = new Piece();
        for (int i = 1; i < numberOfVertices * 2; i+=2)
        {
            var x = Integer.parseInt(items[i]);
            var y = Integer.parseInt(items[i+1]);

            piece.AddCoord(x,y);
        }

        return piece;
    }

    protected static Tuple<Integer,Integer> GetBinDimensions(FileContents contents)
    {
        var binDimensions = contents.Get(1).split(" ");
        var binX = Integer.parseInt(binDimensions[0]);
        var binY = Integer.parseInt(binDimensions[1]);

        return new Tuple<>(binX, binY);
    }
}
