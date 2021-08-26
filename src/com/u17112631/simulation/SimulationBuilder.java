package com.u17112631.simulation;

import com.u17112631.heuristics.placement.IPlacementHeuristic;
import com.u17112631.heuristics.selector.ISelectorHeuristic;
import com.u17112631.models.BinPackingSimulation;
import com.u17112631.models.ProblemSpecification;

import java.util.List;

public class SimulationBuilder {

    private HeuristicMapper mapper;
    private ProblemSpecification specification;

    public SimulationBuilder(HeuristicMapper mapper) {
        this.mapper = mapper;
    }

    public BinPackingSimulation build(String heuristicComb) {

        List<ISelectorHeuristic> selectorHeuristicList = mapper.getSelectionHeuristics(heuristicComb);
        List<IPlacementHeuristic> placementHeuristicList = mapper.getPlacementHeuristics(heuristicComb);

        var simulation = new BinPackingSimulation();

        for (int i = 0, heuristicListSize = selectorHeuristicList.size(); i < heuristicListSize; i++) {
            ISelectorHeuristic selectorHeuristic = selectorHeuristicList.get(i);
            IPlacementHeuristic insertionHeuristic = placementHeuristicList.get(i);

            var selectedItems = selectorHeuristic.choose(specification);

            simulation = insertionHeuristic.apply(simulation,selectedItems);
        }

        return simulation;
    }
}
