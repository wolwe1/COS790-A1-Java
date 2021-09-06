package com.u17112631.simulation;

import com.u17112631.heuristics.binSelection.IBinSelectionHeuristic;
import com.u17112631.heuristics.objectSelection.IObjectSelectorHeuristic;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.ProblemSpecification;

import java.util.List;

public class SimulationBuilder {

    private final HeuristicMapper mapper;
    private ProblemSpecification specification;

    public SimulationBuilder(HeuristicMapper mapper, ProblemSpecification specification) {
        this.mapper = mapper;
        this.specification = specification;
    }

    public BinPackingSimulation build(String heuristicComb) {

        List<IObjectSelectorHeuristic> selectorHeuristicList = mapper.getSelectionHeuristics(heuristicComb);
        List<IBinSelectionHeuristic> placementHeuristicList = mapper.getPlacementHeuristics(heuristicComb);

        var simulation = new BinPackingSimulation();

        for (int i = 0, heuristicListSize = selectorHeuristicList.size(); i < heuristicListSize; i++) {
            IObjectSelectorHeuristic selectorHeuristic = selectorHeuristicList.get(i);
            IBinSelectionHeuristic insertionHeuristic = placementHeuristicList.get(i);

            var objectToPlace = selectorHeuristic.choose(specification);

            specification = specification.removePiece(objectToPlace);

            simulation = insertionHeuristic.apply(simulation,objectToPlace);
        }

        return simulation;
    }
}
