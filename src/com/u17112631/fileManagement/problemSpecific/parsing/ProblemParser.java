package com.u17112631.fileManagement.problemSpecific.parsing;

import com.u17112631.fileManagement.file.FileContents;
import com.u17112631.models.ProblemSpecification;

import java.util.stream.Collectors;

public class ProblemParser extends Parser {
    public static ProblemSpecification Parse(FileContents problemContents)
    {
        var numberOfPieces = Integer.parseInt(problemContents.Get(0));
        var coords = GetBinDimensions(problemContents);

        var pieces = problemContents.GetLinesFrom(2, numberOfPieces)
                .stream().map(x -> ParsePiece(x)).collect(Collectors.toList());



        return new ProblemSpecification(coords.Item1,coords.Item2,pieces);
    }
}
