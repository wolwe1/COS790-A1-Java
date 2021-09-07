package com.u17112631.heuristics.binSelection;

import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.Piece;

public class FirstFitHeuristic implements IBinSelectionHeuristic{
    @Override
    public BinPackingSimulation placeInBin(BinPackingSimulation simulation, Piece objectToPlace) {

        var firstFittingBin = simulation
                .getBins()
                .stream()
                .filter(b -> b.canFitPiece(objectToPlace))
                .findFirst()
                .orElse(null);

        if(firstFittingBin == null){
            simulation.placePieceInNewBin(objectToPlace);
        }else{
            simulation.placePieceInBin(firstFittingBin.getId(),objectToPlace);
        }


        return simulation;
    }
}
