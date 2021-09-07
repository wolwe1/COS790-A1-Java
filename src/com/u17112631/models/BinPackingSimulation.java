package com.u17112631.models;

import com.u17112631.helpers.PlacementAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BinPackingSimulation {
    private final List<Bin> bins;
    private final int binHeight;
    private final int binWidth;

    public BinPackingSimulation(ProblemSpecification specification) {
        this.bins = new ArrayList<>();
        binHeight = specification.getBinHeight();
        binWidth = specification.getBinWidth();
    }

    public List<Bin> getBins() {
        return bins;
    }

    public int addBin() {
        var newBin = new Bin(binWidth,binHeight);
        newBin.setId(bins.size());

        bins.add(newBin);

        return newBin.getId();
    }

    public void placePieceInBin(int binId, Piece objectToPlace) {
        var binToPlaceIn = getBin(binId);

        var placedPiece = PlacementAlgorithm.simulatePlacement(binToPlaceIn,objectToPlace);

        if(placedPiece == null)
            throw new RuntimeException("Could not place piece in bin");

        binToPlaceIn.addPiece(placedPiece);
    }

    private Bin getBin(int binId) {
        var bin = bins.stream().filter(b -> b.getId() == binId).collect(Collectors.toList()).get(0);

        if(bin == null)
            throw new RuntimeException("Bin of ID: " + binId + " does not exist");

        return bin;
    }

    public void placePieceInNewBin(Piece objectToPlace) {
        var newBinId = addBin();
        placePieceInBin(newBinId,objectToPlace);
    }
}
