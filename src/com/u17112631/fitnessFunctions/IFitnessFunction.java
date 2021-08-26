package com.u17112631.fitnessFunctions;

import com.u17112631.models.BinPackingSimulation;
import com.u17112631.Solution.BinPackingSolution;
import initialsoln.InitialSoln;

public interface IFitnessFunction {

    double Evaluate(BinPackingSimulation solution);

    int Fitter(BinPackingSolution fitness, InitialSoln otherFitness);
}
