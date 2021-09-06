package com.u17112631.heuristics.objectSelection;

import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;

public class NextPieceHeuristic  implements IObjectSelectorHeuristic {
    @Override
    public Piece choose(ProblemSpecification specification) {
        return specification.getPieces().get(0);
    }
}
