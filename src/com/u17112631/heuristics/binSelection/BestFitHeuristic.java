package com.u17112631.heuristics.binSelection;

import com.u17112631.helpers.PlacementAlgorithm;
import com.u17112631.models.Bin;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.Piece;

public class BestFitHeuristic  implements IBinSelectionHeuristic {
    @Override
    public BinPackingSimulation placeInBin(BinPackingSimulation simulation, Piece objectToPlace) {

        var leastSpaceRemaining = Integer.MAX_VALUE;
        var bestBin = -1;

        for (var bin : simulation.getBins()){
            int leftoverSpace =getLeftoverSpaceInBinAferPlacement(bin,objectToPlace);

            if(leftoverSpace < leastSpaceRemaining){
                leastSpaceRemaining = leftoverSpace;
                bestBin = bin.getId();
            }
        }

        //No appropriate bin
        if(leastSpaceRemaining == Integer.MAX_VALUE){
            var newBinId = simulation.addBin();
            simulation.placePieceInBin(newBinId,objectToPlace);
            simulation.placePieceInNewBin(objectToPlace);
        }else{
            simulation.placePieceInBin(bestBin,objectToPlace);
        }

        return simulation;
    }

    private int getLeftoverSpaceInBinAferPlacement(Bin bin, Piece objectToPlace) {

        var pieceAfterPlacement = PlacementAlgorithm.simulatePlacement(bin,objectToPlace);

        if(pieceAfterPlacement == null)
            return Integer.MAX_VALUE;

        var testBin = bin.getCopy();
        testBin.addPiece(pieceAfterPlacement);

        return PlacementAlgorithm.calculateLeftoverSpace(testBin);
    }
}
