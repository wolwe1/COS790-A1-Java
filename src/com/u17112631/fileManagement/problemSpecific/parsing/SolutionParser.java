package com.u17112631.fileManagement.problemSpecific.parsing;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.helpers.Tuple;
import com.u17112631.models.Bin;
import com.u17112631.models.Piece;
import com.u17112631.models.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionParser extends Parser
{
    public static Solution Parse(FileContents solution)
    {
        var numberOfBins = getNumberOfBins(solution);

        var dimensions = GetBinDimensions(solution);
        var numItemsInBins = GetNumberOfItemsInBins(solution);

        var numberOfPiecesInSolution = numItemsInBins.stream().reduce(0,Integer::sum);

        var pieces = solution.GetLinesFrom(2, numberOfPiecesInSolution)
                .stream().map(Parser::ParsePiece).collect(Collectors.toList());

        ArrayList<Bin> newBins = createBins(numberOfBins, dimensions, numItemsInBins, pieces);
        return new Solution(newBins);
    }

    private static ArrayList<Bin> createBins(int numberOfBins, Tuple<Integer, Integer> dimensions, List<Integer> numItemsInBins, List<Piece> pieces) {
        var newBins = new ArrayList<Bin>();

        for (int i = 0; i < numberOfBins; i++) {

            var startPieceIndex = i * numItemsInBins.get(i);
            var endPieceIndex = startPieceIndex + numItemsInBins.get(i);

            var piecesInBin = pieces.subList(startPieceIndex,endPieceIndex);

            var newBin = new Bin(dimensions.Item1, dimensions.Item2);
            newBin.setPieces(piecesInBin);

            newBins.add(newBin);
        }
        return newBins;
    }

    private static int getNumberOfBins(FileContents solution){
        var binInfo = solution.Get(0).split(" ");

        return Integer.parseInt(binInfo[0]);
    }

    private static List<Integer> GetNumberOfItemsInBins(FileContents solution)
    {
        var binInfo = solution.Get(0).split(" ");
        var numBins = Integer.parseInt(binInfo[0]);

        var numItemsInBin = new ArrayList<Integer>();

        for (int i = 1; i <= numBins; i++)
        {
            var numberOfItemsInBin = Integer.parseInt(binInfo[i]);
            numItemsInBin.add(numberOfItemsInBin);
        }

        return numItemsInBin;
    }
}