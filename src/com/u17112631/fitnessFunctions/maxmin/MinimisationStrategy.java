package com.u17112631.fitnessFunctions.maxmin;

public class MinimisationStrategy implements IFitnessStrategy{
    @Override
    public int Fitter(double fitness, double otherFitness) {
        return Double.compare(otherFitness, fitness);
    }
}
