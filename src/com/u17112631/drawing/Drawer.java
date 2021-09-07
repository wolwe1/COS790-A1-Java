package com.u17112631.drawing;

import com.u17112631.helpers.Coordinate;
import com.u17112631.models.Piece;
import com.u17112631.simulation.Board;

import java.util.ArrayList;

public class Drawer {
    private DisplayBoard displayBoard;

    public Drawer(){
        displayBoard = new DisplayBoard(100,100);
    }

    public Drawer createBoard(int height,int width){
        displayBoard = new DisplayBoard(height,width);
        return this;
    }

    public void draw(){
        displayBoard.draw();
    }

    public Drawer addPiece(Piece p,int bottomLeftX,int bottomLeftY) {

        var pieceBoard = createPieceBoard(p);

        pieceBoard.draw();
        displayBoard.setSubBoard(pieceBoard,bottomLeftX,bottomLeftY);

        return this;
    }

    public Drawer addPiece(Piece p) {

        var middleX = displayBoard.getWidth() / 2;
        var middleY = displayBoard.getHeigh() / 2;

        return addPiece(p,middleX,middleY);
    }

    private DisplayBoard createPieceBoard(Piece p){

        int pieceHeight = p.getHeight() + 1;
        int pieceWidth = p.getWidth() + 1;

        var pieceBoard = new DisplayBoard(pieceHeight,pieceWidth);

        ArrayList<Coordinate> coords = p.getVertices();
        for (int i = 0, coordsSize = coords.size(); i < coordsSize; i++) {
            Coordinate point = coords.get(i);
            Coordinate nextPoint = coords.get( (i + 1) % coordsSize);

            int differenceInX;
            int differenceInY;
            int currentY;

            if(point.getX() < nextPoint.getX())
                differenceInX = nextPoint.getX() - point.getX();
            else
                differenceInX = point.getX() - nextPoint.getX();


            if(point.getY() < nextPoint.getY()){
                differenceInY = nextPoint.getY() - point.getY();
                currentY = point.getY();

            }else{
                differenceInY = point.getY() - nextPoint.getY();
                currentY = point.getY();
            }

            var distance = 2 * differenceInY - differenceInX;

            pieceBoard = drawLine(pieceBoard,point,nextPoint,differenceInX,differenceInY,distance,currentY);

        }
        return pieceBoard;
    }
    private DisplayBoard drawLine(DisplayBoard pieceDisplayBoard, Coordinate point, Coordinate nextPoint, int differenceInX, int differenceInY, int distance, int currentY){
        if(point.getX() == nextPoint.getX()){
            //vertical draw

            return verticalDraw(pieceDisplayBoard, point, nextPoint, currentY,point.getX());
        }else{
            //Horizontal draw
            return horizontalDraw(pieceDisplayBoard, point, nextPoint, differenceInX, differenceInY, distance, currentY);
        }
    }
    private DisplayBoard horizontalDraw(DisplayBoard pieceDisplayBoard, Coordinate point, Coordinate nextPoint, int differenceInX, int differenceInY, int distance, int currentY) {

        Coordinate smallerXPoint = point.getX() < nextPoint.getX() ? point : nextPoint;
        Coordinate biggerXPoint = point.getX() > nextPoint.getX() ? point : nextPoint;

        boolean increasing = smallerXPoint.getY() < biggerXPoint.getY();

        int yStep = increasing ? 1 : -1;

        //int yStep = 1;

        for (int x = smallerXPoint.getX(); x < biggerXPoint.getX(); x++) {
            pieceDisplayBoard.set(x, currentY,"*");

            if ( distance > 0){
                currentY = currentY + yStep;
                distance = distance - 2 * differenceInX;
            }

            distance = distance + 2 * differenceInY;
        }

        return pieceDisplayBoard;
    }

    private DisplayBoard verticalDraw(DisplayBoard pieceDisplayBoard, Coordinate point, Coordinate nextPoint, int currentY, int x) {

        int smaller = Math.min(point.getY(), nextPoint.getY());
        int bigger = Math.max(point.getY(), nextPoint.getY());

        for (int y = smaller; y < bigger; y++) {
            pieceDisplayBoard.set(x, y,"*");
        }

        return pieceDisplayBoard;
    }

    public void displayBoard(Board board) {
        DisplayBoard displayBoard = new DisplayBoard(board);
        displayBoard.draw();
    }

    public void displayBoard(Board board,String colour) {
        DisplayBoard displayBoard = new DisplayBoard(board);
        displayBoard.draw(colour);
    }
}
