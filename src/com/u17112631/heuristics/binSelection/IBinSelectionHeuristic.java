package com.u17112631.heuristics.binSelection;

import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.Piece;

public interface IBinSelectionHeuristic {

    BinPackingSimulation placeInBin(BinPackingSimulation simulation, Piece objectToPlace);
}



