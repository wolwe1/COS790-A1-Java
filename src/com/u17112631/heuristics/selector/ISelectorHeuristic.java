package com.u17112631.heuristics.selector;

import com.u17112631.helpers.Tuple;
import com.u17112631.models.Bin;
import com.u17112631.models.Piece;
import com.u17112631.models.ProblemSpecification;

public interface ISelectorHeuristic {

    Tuple<Piece, Bin> choose(ProblemSpecification specification);
}
