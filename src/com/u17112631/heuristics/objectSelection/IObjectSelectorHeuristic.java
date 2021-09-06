package com.u17112631.heuristics.objectSelection;

import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;

public interface IObjectSelectorHeuristic {

    Piece choose(ProblemSpecification specification);
}
