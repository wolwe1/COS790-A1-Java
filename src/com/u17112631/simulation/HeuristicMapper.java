package com.u17112631.simulation;

import com.u17112631.heuristics.placement.IPlacementHeuristic;
import com.u17112631.heuristics.selector.ISelectorHeuristic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class HeuristicMapper {

    Hashtable<Character, ISelectorHeuristic> selectorHeuristicTable = new Hashtable<>();
    Hashtable<Character, IPlacementHeuristic> placementHeuristicTable = new Hashtable<>();

    public List<ISelectorHeuristic> getSelectionHeuristics(String heuristic) {

        List<ISelectorHeuristic> heuristicList = new ArrayList<>();

        for (int i = 0; i < heuristic.length(); i += 2){
            char selectionHeuristicIndicator = heuristic.charAt(i);
            heuristicList.add(selectorHeuristicTable.get(selectionHeuristicIndicator));
        }

        return heuristicList;
    }

    public List<IPlacementHeuristic> getPlacementHeuristics(String heuristic) {

        List<IPlacementHeuristic> heuristicList = new ArrayList<>();

        for (int i = 1; i < heuristic.length(); i += 2){
            char placementHeuristicIndicator = heuristic.charAt(i);

            heuristicList.add(placementHeuristicTable.get(placementHeuristicIndicator));
        }

        return heuristicList;
    }

    public HeuristicMapper addMapping(char character, ISelectorHeuristic selectorHeuristic,IPlacementHeuristic placementHeuristic){

        addMapping(character,selectorHeuristic);
        addMapping(character,placementHeuristic);

        return this;
    }

    public HeuristicMapper addMapping(char character, ISelectorHeuristic heuristic){

        selectorHeuristicTable.put(character,heuristic);
        return this;
    }

    public HeuristicMapper addMapping(char character, IPlacementHeuristic heuristic){

        placementHeuristicTable.put(character,heuristic);
        return this;
    }

}
