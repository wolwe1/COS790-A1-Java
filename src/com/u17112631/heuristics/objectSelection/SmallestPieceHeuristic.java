package com.u17112631.heuristics.objectSelection;

import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;

import java.util.Comparator;

public class SmallestPieceHeuristic  implements IObjectSelectorHeuristic{
    @Override
    public Piece choose(ProblemSpecification specification) {
        var pieces = specification.getPieces();

        return pieces.stream().min(Comparator.comparing(Piece::getRectangularArea)).get();
    }
}
