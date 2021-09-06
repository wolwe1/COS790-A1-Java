package com.u17112631.drawing;

public class Board {
    private final String[][] board;

    public Board(int height,int width) {
        this.board = new String[height][width];
        clean();
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
                builder.append(column);
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

    public void setSubBoard(Board subBoard, int bottomLeftX, int bottomLeftY) {

        for (int y = bottomLeftY; y < board.length; y++) {
            var row = board[y];
            for (int x = bottomLeftX; x < row.length; x++) {
                var subX = x - bottomLeftX;
                var subY = y - bottomLeftY;

                if(subBoard.validIndex(subX,subY)){
                    var item = subBoard.get(subX,subY);
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
