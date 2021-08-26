package com.u17112631.ProblemDomain;

import com.u17112631.Solution.BinPackingSolution;
import com.u17112631.fitnessFunctions.IFitnessFunction;
import com.u17112631.simulation.SimulationBuilder;
import initialsoln.InitialSoln;
import problemdomain.ProblemDomain;

public class BinPackingProblemDomain extends ProblemDomain {

    private final IFitnessFunction fitnessFunction;
    private final SimulationBuilder builder;

    public BinPackingProblemDomain(IFitnessFunction function, SimulationBuilder builder){
        this.fitnessFunction = function;
        this.builder = builder;
    }

    @Override
    public InitialSoln evaluate(String s) {
        var solution = new BinPackingSolution(fitnessFunction, builder);
        solution.setHeuCom(s);

        return solution;
    }

}
