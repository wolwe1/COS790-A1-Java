package com.u17112631.fileManagement.problemSpecific.parsing;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.models.Bin;
import com.u17112631.models.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionParser extends Parser
{
    public static Solution Parse(FileContents solution)
    {
        var bins = CreateBins(solution);

        var numberOfPiecesInSolution = bins.stream().map(x -> x.GetNumberOfPiecesInside()).reduce(0,Integer::sum);

        var pieces = solution.GetLinesFrom(2, numberOfPiecesInSolution)
                .stream().map(x -> ParsePiece(x)).collect(Collectors.toList());

        for (int i = 0; i < bins.size(); i++) {
            var bin = bins.get(i);

            var startPieceIndex = i * bin.GetNumberOfPiecesInside();
            var endPieceIndex = startPieceIndex + bin.GetNumberOfPiecesInside();

            var piecesInBin = pieces.subList(startPieceIndex,endPieceIndex);
            bin.setPieces(piecesInBin);

        }
        return new Solution(bins);
    }

    private static List<Bin> CreateBins(FileContents solution)
    {
        var binInfo = solution.Get(0).split(" ");

        var coords = GetBinDimensions(solution);
        var numItemsInBin = GetNumberOfItemsInBins(binInfo);

        return CreateBins(numItemsInBin, coords.Item1, coords.Item2);
    }

    private static List<Bin> CreateBins(List<Integer> numItemsInBin, int binX, int binY)
    {
        var bins = new ArrayList<Bin>();
        for  (var numberOfItems : numItemsInBin)
        {
            bins.add(new Bin(numberOfItems, binX, binY));
        }
        return bins;
    }

    private static List<Integer> GetNumberOfItemsInBins(String[] binInfo)
    {
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