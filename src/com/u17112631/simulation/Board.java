package com.u17112631.simulation;

import com.u17112631.models.Piece;

import java.util.Comparator;

public class Board {

    private final int[][] board;

    public Board(int height, int width) {
        this.board = new int[height][width];
        //clean();
    }

    public void clean(){
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            int[] row = board[i];
            for (int j = 0, rowLength = row.length; j < rowLength; j++) {
                int column = row[j];

                board[i][j] = 0;

            }
        }
    }

    private int getYInvert(int y){
        return (board.length - 1) - y;
    }
    public void set(int x, int y) {

        //invert y for correct coordinates
        board[getYInvert(y)][x] = 1;
    }

    public int get(int x, int y) {
        return board[y][x];
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeight() {
        return board.length;
    }

    public Board placePiece(Piece piece) {
        int height = piece.getHeight();
        int width = piece.getWidth();

        int leftMostXPos = piece.getXCoordinates().stream().min(Comparator.comparing(v -> v)).get();
        int topYPos = piece.getYCoordinates().stream().max(Comparator.comparing(v -> v)).get();

        return placeBorderBoxOnBoard(leftMostXPos,topYPos,height,width);
        //return fillBorderBox(leftMostXPos,topYPos,height,width);
    }

    private Board placeBorderBoxOnBoard(int leftMostXPos, int topYPos, int height, int width) {

        //Draw top
        drawHorizontalLine(leftMostXPos,topYPos,width);
        //Draw bottom
        drawHorizontalLine(leftMostXPos,topYPos - height,width);
        //Draw left
        drawVerticalLine(leftMostXPos,topYPos - height,height);
        //Draw right
        //-1 to account for 0 indexing
        drawVerticalLine(leftMostXPos + (width - 1),topYPos - height,height);

        return this;
    }

    private Board fillBorderBox(int leftMostXPos, int topYPos, int height, int width) {

        //Draw top
        for (int i = 0; i <= height; i++) {
            drawHorizontalLine(leftMostXPos,topYPos - i,width);
        }

        return this;
    }

    private void drawVerticalLine(int xConst, int startY, int height) {

        int yInvert = getHeight();

        for(int y = 0; y <= height; y++){
            set(xConst,startY + y);
        }
    }

    private void drawHorizontalLine(int xStart, int yConst, int width) {

        for(int x = 0; x < width; x++){
            set(xStart + x,yConst);
        }

    }

    public int[] getHorizontalLine(int y) {
        return board[getYInvert(y)];
    }

    public int[] getVerticalLine(int x) {

        var row = new int[board.length];

        for (int y = 0; y < board.length; y++) {
            row[y] = board[getYInvert(y)][x];
        }
        return row;
    }

    public int getUnutilisedSpots() {
        int spaces = 0;

        for (int[] row : board) {
            for (int i : row) {
                if (i == 0)
                    spaces++;
            }
        }
        return spaces;
    }
}
