package com.u17112631;

import com.u17112631.ProblemDomain.BinPackingProblemDomain;
import com.u17112631.fitnessFunctions.UtilizedSpaceFitnessFunction;
import com.u17112631.fitnessFunctions.maxmin.MinimisationStrategy;
import com.u17112631.simulation.HeuristicMapper;
import com.u17112631.simulation.SimulationBuilder;
import genalg.GenAlg;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Hello world");
        UtilizedSpaceFitnessFunction usff = new UtilizedSpaceFitnessFunction(new MinimisationStrategy());

        HeuristicMapper mapper = new HeuristicMapper()
                .addMapping('a',)

        SimulationBuilder builder = new SimulationBuilder(mapper);

        BinPackingProblemDomain problemDomain = new BinPackingProblemDomain(usff, builder);

        GenAlg alg = new GenAlg(0,"abc");
        alg.setParameters("GeneticAlgParams.txt");
        alg.setProblem(problemDomain);
        alg.evolve();
    }
}
