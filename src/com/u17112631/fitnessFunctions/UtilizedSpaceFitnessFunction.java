package com.u17112631.fitnessFunctions;

import com.u17112631.models.Bin;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.Solution.BinPackingSolution;
import com.u17112631.fitnessFunctions.maxmin.IFitnessStrategy;
import com.u17112631.models.Piece;
import initialsoln.InitialSoln;

public class UtilizedSpaceFitnessFunction implements IFitnessFunction {

    private IFitnessStrategy strategy;

    public UtilizedSpaceFitnessFunction(IFitnessStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double Evaluate(BinPackingSimulation solution) {
        var binsUsed = solution.getBins();

        var totalSpaceWasted = 0;
        for (var bin : solution.getBins()){
            totalSpaceWasted += getWastedSpaceInBin(bin);
        }

        return totalSpaceWasted;
    }

    private double getWastedSpaceInBin(Bin bin) {
        var totalSpace = bin.getHeight() * bin.getWidth();

        var spaceOccupiedByPieces = 0;

        for (var piece : bin.getPieces()){
            spaceOccupiedByPieces += getSpaceOccupiedByPiece(piece);
        }
        return 0;
    }

    private double getSpaceOccupiedByPiece(Piece piece) {
        return 0; //TODO: Figure out math
    }

    @Override
    public int Fitter(BinPackingSolution base, InitialSoln opponent) {
        return strategy.Fitter(base.getFitness(),opponent.getFitness());
    }
}
