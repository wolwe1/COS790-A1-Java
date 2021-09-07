package com.u17112631.drawing;

import com.u17112631.simulation.Board;

import java.util.Objects;


public class DisplayBoard {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private final String[][] board;

    public DisplayBoard(int height, int width) {
        this.board = new String[height][width];
        clean();
    }

    public DisplayBoard(Board board) {
        this.board = new String[board.getHeight()][board.getWidth()];
        setBoard(board);
    }

    private void setBoard(Board board) {
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[0].length; x++) {
                this.board[y][x] = Integer.toString(board.get(x,y));
            }
        }
    }

    public void clean(){
        for (int i = 0, boardLength = board.length; i < boardLength; i++) {
            String[] row = board[i];
            for (int j = 0, rowLength = row.length; j < rowLength; j++) {
                String column = row[j];

                board[i][j] = ".";

            }
        }
    }

    public void draw(){
        var builder = new StringBuilder();

        for (String[] row : board) {
            for (String column : row) {
                if(Objects.equals(column, ".") || Objects.equals(column, "0"))
                    builder.append(ANSI_BLACK + column + ANSI_RESET);
                else
                    builder.append(ANSI_RED + column + ANSI_RESET);
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

    public void draw(String colour){
        var builder = new StringBuilder();

        for (String[] row : board) {
            for (String column : row) {
                if(Objects.equals(column, ".") || Objects.equals(column, "0"))
                    builder.append(ANSI_BLACK + column + ANSI_RESET);
                else
                    builder.append(colour + column + ANSI_RESET);
            }
            builder.append("\n");
        }

        System.out.println(builder);
    }

    public void set(int x, int y, String s) {

        //invert y for correct coordinates
        int yInvert = (board.length - 1) - y;
        board[yInvert][x] = s;
    }

    public void setSubBoard(DisplayBoard subDisplayBoard, int bottomLeftX, int bottomLeftY) {

        for (int y = bottomLeftY; y < board.length; y++) {
            var row = board[y];
            for (int x = bottomLeftX; x < row.length; x++) {
                var subX = x - bottomLeftX;
                var subY = y - bottomLeftY;

                if(subDisplayBoard.validIndex(subX,subY)){
                    var item = subDisplayBoard.get(subX,subY);
                    if(item != ".")
                        board[y][x] = item;
                }

            }
        }
    }

    private boolean validIndex(int subX, int subY) {
        return subX >= 0 && subX < getWidth() && subY >= 0 && subY < getHeigh();
    }

    private String get(int x, int y) {
        return board[y][x];
    }

    public int getWidth() {
        return board[0].length;
    }

    public int getHeigh() {
        return board.length;
    }
}
