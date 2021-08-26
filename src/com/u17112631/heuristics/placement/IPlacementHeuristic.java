package com.u17112631.heuristics.placement;

import com.u17112631.helpers.Tuple;
import com.u17112631.models.Bin;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.Piece;

public interface IPlacementHeuristic {

    BinPackingSimulation apply(BinPackingSimulation simulation, Tuple<Piece, Bin> selectedItems);
}



