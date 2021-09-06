package com.u17112631.simulation;

import com.u17112631.heuristics.binSelection.IBinSelectionHeuristic;
import com.u17112631.heuristics.objectSelection.IObjectSelectorHeuristic;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class HeuristicMapper {

    Hashtable<Character, IObjectSelectorHeuristic> selectorHeuristicTable = new Hashtable<>();
    Hashtable<Character, IBinSelectionHeuristic> placementHeuristicTable = new Hashtable<>();

    public List<IObjectSelectorHeuristic> getSelectionHeuristics(String heuristic) {

        List<IObjectSelectorHeuristic> heuristicList = new ArrayList<>();

        for (int i = 0; i < heuristic.length(); i += 2){
            char selectionHeuristicIndicator = heuristic.charAt(i);
            heuristicList.add(selectorHeuristicTable.get(selectionHeuristicIndicator));
        }

        return heuristicList;
    }

    public List<IBinSelectionHeuristic> getPlacementHeuristics(String heuristic) {

        List<IBinSelectionHeuristic> heuristicList = new ArrayList<>();

        for (int i = 1; i < heuristic.length(); i += 2){
            char placementHeuristicIndicator = heuristic.charAt(i);

            heuristicList.add(placementHeuristicTable.get(placementHeuristicIndicator));
        }

        return heuristicList;
    }

    public HeuristicMapper addMapping(char character, IObjectSelectorHeuristic selectorHeuristic, IBinSelectionHeuristic placementHeuristic){

        addMapping(character,selectorHeuristic);
        addMapping(character,placementHeuristic);

        return this;
    }

    public HeuristicMapper addMapping(char character, IObjectSelectorHeuristic heuristic){

        selectorHeuristicTable.put(character,heuristic);
        return this;
    }

    public HeuristicMapper addMapping(char character, IBinSelectionHeuristic heuristic){

        placementHeuristicTable.put(character,heuristic);
        return this;
    }

}
