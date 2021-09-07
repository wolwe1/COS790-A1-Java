package com.u17112631.drawing;

import com.u17112631.helpers.PlacementAlgorithm;
import com.u17112631.models.Bin;
import com.u17112631.models.Piece;
import com.u17112631.simulation.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GUIControl {

    private final Drawer drawer;
    private final Bin bin;
    private Board binBoard;

    private final List<Piece> pieces;
    private int currentPieceIndex;

    private final Random numgen;
    private boolean binFull;

    public GUIControl(){
        drawer = new Drawer();
        bin = new Bin(25,25);
        binBoard = PlacementAlgorithm.createBoard(bin);
        pieces = new ArrayList<>();
        currentPieceIndex = 0;
        numgen = new Random(0);
        binFull = false;
    }

    public void createBoard(){
       var firstPiece = createRectangle();
       pieces.add(firstPiece);
       bin.setPieces(pieces);
        currentPieceIndex = 0;

       updateState();
    }

    public void updateState(){
        resetBoard();
        draw();
    }

    public void animate() {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 10;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long lastFpsTime = 0;

        createBoard();

        while (!binFull) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;

            lastFpsTime += updateLength;
            if (lastFpsTime >= 1000000000) {
                lastFpsTime = 0;
            }

            System.out.print('\r');
            System.out.println("\n\n");
            this.updateBoard();
            System.out.println("\n\n");
            this.draw();

            try {
                var animTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;

                Thread.sleep( animTime);
            } catch (Exception e) {
            }
        }
    }

    private void updateBoard() {

        var currentPiece = pieces.get(currentPieceIndex);

        if(PlacementAlgorithm.canMoveDown(currentPiece,bin))
            currentPiece.moveDown(1);
        else if(PlacementAlgorithm.canMoveLeft(currentPiece,bin))
            currentPiece.moveLeft(1);
        else{
            var newPiece = createRectangle();
            currentPieceIndex++;

            binFull = !PlacementAlgorithm.canFitPiece(bin,newPiece);
            pieces.add(newPiece);
        }

        if(!binFull){

            bin.setPieces(pieces);
            updateState();
        }

    }

    public Piece createRectangle(){

        var randomLength = numgen.nextInt((10 - 2) + 1) + 2;
        var randomHeight = numgen.nextInt((10 - 2) + 1) + 2;

        Piece piece = new Piece(1)
                .addCoord(0,0)
                .addCoord(0,randomHeight)
                .addCoord(randomLength,randomHeight)
                .addCoord(randomLength,0);

        return PlacementAlgorithm.shiftPieceToTopRight(bin,piece);
    }

    public void resetBoard(){
        binBoard = PlacementAlgorithm.createBoard(bin);
    }

    public void draw(){
        drawer.displayBoard(binBoard);
    }
}
