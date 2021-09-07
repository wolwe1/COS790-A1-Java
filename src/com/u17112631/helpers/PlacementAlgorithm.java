package com.u17112631.helpers;

import com.u17112631.models.Bin;
import com.u17112631.models.Piece;
import com.u17112631.simulation.Board;

public class PlacementAlgorithm {

    public static boolean canFitPiece(Bin bin, Piece objectToPlace) {

        Piece shiftedPiece = shiftPieceToTopRight(bin,objectToPlace);

        return canMoveDown(shiftedPiece,bin);
    }

    public static Piece shiftPieceToTopRight(Bin bin, Piece objectToPlace) {
        var top = bin.getHeight() - 1;
        var right = bin.getWidth() - 1;

        var topDiff = top - objectToPlace.getHighestYCoord();
        var rightDiff = right - objectToPlace.getHighestXCoord();

        return shiftPiece(objectToPlace,rightDiff,topDiff);
    }

    private static Piece shiftPiece(Piece object,int xDiff, int yDiff){
        var shiftedPiece = new Piece(object.getId());

        for (var coord : object.getVertices()){
            var shiftedCoord = coord.shift(xDiff,yDiff);
            shiftedPiece.addCoord(shiftedCoord);
        }
        return shiftedPiece;
    }

    public static Board createBoard(Bin bin) {
        Board displayBoard = new Board(bin.getHeight(), bin.getWidth());

        for (var piece : bin.getPieces()){
            displayBoard.placePiece(piece);
        }
        return displayBoard;
    }

    public static boolean canMoveDown(Piece piece, Bin bin) {

        var lowestYPoint = piece.getLowestYCoord();

        //Border check
        if(lowestYPoint - 1 < 0) return false;

        if(bin.getNumberOfPiecesInside() == 0)
            return true;

        //Collision with other objects check
        var binBoard = createBoard(bin);

        var lowestXPoint = piece.getLowestXCoord();
        var highestXPoint = piece.getHighestXCoord();

        //Get the line that the piece would be moving into
        var coordinateLineInBin = binBoard.getHorizontalLine(lowestYPoint - 1);

        return !pieceInTheWayVertically(lowestXPoint, highestXPoint, coordinateLineInBin);

    }

    private static boolean pieceInTheWayVertically(int movingPieceStartX, int movingPieceEndX, int[] coordinateLineInBin) {

        for (int x = movingPieceStartX; x < movingPieceEndX; x++) {
            if(coordinateLineInBin[x] == 1)
                return true;
        }
        return false;
    }

    public static boolean canMoveLeft(Piece piece, Bin bin) {

        var lowestXPoint = piece.getLowestXCoord();

        //Border check
        if(lowestXPoint - 1 < 0) return false;

        if(bin.getNumberOfPiecesInside() == 0)
            return true;

        //Collision with other objects check
        var binBoard = createBoard(bin);

        var lowestYPoint = piece.getLowestYCoord();
        var highestYPoint = piece.getHighestYCoord();

        //Get the line that the piece would be moving into
        var coordinateLineInBin = binBoard.getVerticalLine(lowestXPoint - 1);

        return !pieceInTheWayHorizontally(lowestYPoint, highestYPoint, coordinateLineInBin);
    }

    private static boolean pieceInTheWayHorizontally(int movingPieceStartY, int movingPieceEndY, int[] coordinateLineInBin) {
        for (int y = movingPieceStartY; y < movingPieceEndY; y++) {
            if(coordinateLineInBin[y] == 1)
                return true;
        }
        return false;
    }

    public static Piece simulatePlacement(Bin bin,Piece objectToPlace){

        var shiftedPiece = shiftPieceToTopRight(bin,objectToPlace);

        if(!canFitPiece(bin,shiftedPiece)) return null;

        boolean canMove = true;

        while (canMove){

            if(canMoveDown(shiftedPiece,bin)){
                simulateDownwardsMovement(shiftedPiece, bin);
            }else if(canMoveLeft(shiftedPiece,bin)){
                simulateLeftwardsMovement(shiftedPiece, bin);
            }else{
                canMove = false;
            }
        }
        return shiftedPiece;
    }

    private static void simulateLeftwardsMovement(Piece object, Bin bin) {

        int distanceLeft = getDistanceToObjectToTheLeft( object,  bin);

        object.moveLeft(distanceLeft);
    }

    private static int getDistanceToObjectToTheLeft(Piece object, Bin bin) {
        var binBoard = createBoard(bin);

        var lowestXPoint = object.getLowestXCoord();
        var currentXPoint = lowestXPoint;

        var lowestYPoint = object.getLowestYCoord();
        var highestYPoint = object.getHighestYCoord();

        //Get the line that the piece would be moving into
        boolean collision = false;

        while(!collision && lowestXPoint > 0){
            var coordinateLineInBin = binBoard.getVerticalLine(--lowestXPoint);

            for (int y = lowestYPoint; y < highestYPoint; y++) {
                if (coordinateLineInBin[y] == 1) {
                    collision = true;
                    break;
                }
            }
        }
        return currentXPoint - lowestXPoint;
    }

    private static int getDistanceToObjectBelow(Piece object, Bin bin) {

        var lowestYPoint = object.getLowestYCoord();

        var currentYPoint = lowestYPoint;
        //Collision with other objects check
        var binBoard = createBoard(bin);

        var lowestXPoint = object.getLowestXCoord();
        var highestXPoint = object.getHighestXCoord();

        //Get the line that the piece would be moving into
        boolean collision = false;

        while(!collision && lowestYPoint > 0){
            var coordinateLineInBin = binBoard.getHorizontalLine(--lowestYPoint);

            for (int x = lowestXPoint; x < highestXPoint; x++) {
                if (coordinateLineInBin[x] == 1) {
                    collision = true;
                    break;
                }
            }
        }
        return currentYPoint - lowestYPoint;
    }

    private static void simulateDownwardsMovement(Piece object, Bin bin) {

        int distanceLeft = getDistanceToObjectBelow( object,  bin);
        object.moveDown(distanceLeft);

    }

    public static int calculateLeftoverSpace(Bin bin) {
        Board binBoard = createBoard(bin);

        return binBoard.getUnutilisedSpots();
    }
}
