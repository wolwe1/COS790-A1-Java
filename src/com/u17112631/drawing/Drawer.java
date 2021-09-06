package com.u17112631.drawing;

import com.u17112631.helpers.Coordinate;
import com.u17112631.models.Piece;

import java.util.ArrayList;

public class Drawer {
    private Board board;

    public Drawer(){
        board = new Board(100,100);
    }

    public Drawer createBoard(int height,int width){
        board = new Board(height,width);
        return this;
    }

    public void draw(){
        board.draw();
    }

    public Drawer addPiece(Piece p,int bottomLeftX,int bottomLeftY) {

        var pieceBoard = createPieceBoard(p);

        pieceBoard.draw();
        board.setSubBoard(pieceBoard,bottomLeftX,bottomLeftY);

        return this;
    }

    public Drawer addPiece(Piece p) {

        var middleX = board.getWidth() / 2;
        var middleY = board.getHeigh() / 2;

        return addPiece(p,middleX,middleY);
    }

    private Board createPieceBoard(Piece p){

        int pieceHeight = p.getHeight() + 1;
        int pieceWidth = p.getWidth() + 1;

        var pieceBoard = new Board(pieceHeight,pieceWidth);

        ArrayList<Coordinate> coords = p.getCoords();
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
    private Board drawLine(Board pieceBoard, Coordinate point, Coordinate nextPoint, int differenceInX, int differenceInY, int distance, int currentY){
        if(point.getX() == nextPoint.getX()){
            //vertical draw

            return verticalDraw(pieceBoard, point, nextPoint, currentY,point.getX());
        }else{
            //Horizontal draw
            return horizontalDraw(pieceBoard, point, nextPoint, differenceInX, differenceInY, distance, currentY);
        }
    }
    private Board horizontalDraw(Board pieceBoard, Coordinate point, Coordinate nextPoint, int differenceInX, int differenceInY, int distance, int currentY) {

        Coordinate smallerXPoint = point.getX() < nextPoint.getX() ? point : nextPoint;
        Coordinate biggerXPoint = point.getX() > nextPoint.getX() ? point : nextPoint;

        boolean increasing = smallerXPoint.getY() < biggerXPoint.getY();

        int yStep = increasing ? 1 : -1;

        //int yStep = 1;

        for (int x = smallerXPoint.getX(); x < biggerXPoint.getX(); x++) {
            pieceBoard.set(x, currentY,"*");

            if ( distance > 0){
                currentY = currentY + yStep;
                distance = distance - 2 * differenceInX;
            }

            distance = distance + 2 * differenceInY;
        }

        return pieceBoard;
    }

    private Board verticalDraw(Board pieceBoard, Coordinate point, Coordinate nextPoint, int currentY, int x) {

        int smaller = Math.min(point.getY(), nextPoint.getY());
        int bigger = Math.max(point.getY(), nextPoint.getY());

        for (int y = smaller; y < bigger; y++) {
            pieceBoard.set(x, y,"*");
        }

        return pieceBoard;
    }
}
