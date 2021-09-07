package com.u17112631.fitnessFunctions;

import com.u17112631.Solution.BinPackingSolution;
import com.u17112631.fitnessFunctions.maxmin.IFitnessStrategy;
import com.u17112631.helpers.PlacementAlgorithm;
import com.u17112631.models.Bin;
import com.u17112631.models.BinPackingSimulation;
import initialsoln.InitialSoln;

public class UtilizedSpaceFitnessFunction implements IFitnessFunction {

    private IFitnessStrategy strategy;

    public UtilizedSpaceFitnessFunction(IFitnessStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public double Evaluate(BinPackingSimulation solution) {
        var binsUsed = solution.getBins();

        var totalSpaceWasted = 0d;
        for (var bin : solution.getBins()){
            totalSpaceWasted += (getWastedSpaceInBin(bin) * 0.01 );
        }

        return totalSpaceWasted;
    }

    private double getWastedSpaceInBin(Bin bin) {
        return PlacementAlgorithm.calculateLeftoverSpace(bin);
    }


    @Override
    public int Fitter(BinPackingSolution base, InitialSoln opponent) {
        return strategy.Fitter(base.getFitness(),opponent.getFitness());
    }
}
