package com.u17112631.heuristics.objectSelection;

import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;

import java.util.Comparator;

public class LeastComplexPieceHeuristic implements IObjectSelectorHeuristic{

    @Override
    public Piece choose(ProblemSpecification specification) {
        return specification.getPieces().stream().min(Comparator.comparing(Piece::getNumberOfVertices)).get();
    }
}
