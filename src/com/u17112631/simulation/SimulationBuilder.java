package com.u17112631.simulation;

import com.u17112631.heuristics.binSelection.IBinSelectionHeuristic;
import com.u17112631.heuristics.objectSelection.IObjectSelectorHeuristic;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.ProblemSpecification;

import java.util.List;

public class SimulationBuilder {

    private final HeuristicMapper mapper;
    private final ProblemSpecification originalSpecification;

    public SimulationBuilder(HeuristicMapper mapper, ProblemSpecification specification) {
        this.mapper = mapper;
        this.originalSpecification = specification;
    }

    public BinPackingSimulation build(String heuristicComb) {

        List<IObjectSelectorHeuristic> selectorHeuristicList = mapper.getSelectionHeuristics(heuristicComb);
        List<IBinSelectionHeuristic> placementHeuristicList = mapper.getPlacementHeuristics(heuristicComb);

        var specification = originalSpecification.getCopy();
        var simulation = new BinPackingSimulation(specification);

        int heuristicIndex = 0;
        while (!simulationComplete(simulation,specification)){
            IObjectSelectorHeuristic selectorHeuristic = selectorHeuristicList.get(heuristicIndex);
            IBinSelectionHeuristic binSelectionHeuristic = placementHeuristicList.get(heuristicIndex);

            var objectToPlace = selectorHeuristic.choose(specification);
            specification = specification.removePiece(objectToPlace);

            var binToPlace =

                    simulation = binSelectionHeuristic.placeInBin(simulation,objectToPlace);

            heuristicIndex = (heuristicIndex + 1) % heuristicComb.length();
        }

        return simulation;
    }

    private boolean simulationComplete(BinPackingSimulation simulation,ProblemSpecification specification) {
        return specification.getNumberOfPieces() == 0;
    }
}
