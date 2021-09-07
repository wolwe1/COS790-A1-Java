package com.u17112631.Solution;

import com.u17112631.fitnessFunctions.IFitnessFunction;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.simulation.SimulationBuilder;
import initialsoln.InitialSoln;

public class BinPackingSolution extends InitialSoln {

    private String heuristic;
    private double fitness;

    private final IFitnessFunction fitnessFunction;
    private final SimulationBuilder simulationBuilder;

    private BinPackingSimulation simulation;

    public BinPackingSolution(IFitnessFunction function, SimulationBuilder simulationBuilder){
        this.fitnessFunction = function;
        this.simulationBuilder = simulationBuilder;
        this.fitness = Double.POSITIVE_INFINITY;
    }


    @Override
    public int fitter(InitialSoln other) {
        return this.fitnessFunction.Fitter(this, other);
    }

    @Override
    public double getFitness() {
        return this.fitness;
    }

    @Override
    public Object getSoln() {
        if(simulation == null){
            simulation = simulationBuilder.build(heuristic);
        }

        return simulation;
    }

    private BinPackingSimulation createSimulation() {
        return null;
    }

    @Override
    public void setHeuCom(String s) {

        //Must have even combinations
        if(s.length() % 2 != 0)
            this.heuristic = s + s;
    }

    @Override
    public String getHeuCom() {
        return this.heuristic;
    }

    public void evaluate() {
        this.fitness = this.fitnessFunction.Evaluate((BinPackingSimulation) this.getSoln());
    }
}
