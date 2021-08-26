package com.u17112631.fitnessFunctions.maxmin;

public class MaximisationStrategy implements IFitnessStrategy {

    @Override
    public int Fitter(double fitness, double otherFitness) {
        return fitness > otherFitness ? 1 : fitness == otherFitness ? 0 : 1;
    }
}
