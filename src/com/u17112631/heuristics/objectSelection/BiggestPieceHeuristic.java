package com.u17112631.heuristics.objectSelection;

import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;

import java.util.Comparator;

public class BiggestPieceHeuristic  implements IObjectSelectorHeuristic{
    @Override
    public Piece choose(ProblemSpecification specification) {

        var pieces = specification.getPieces();

        return pieces.stream().max(Comparator.comparing(Piece::getRectangularArea)).get();

    }
}
