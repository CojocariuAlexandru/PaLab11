package com.gomokumanager.GomokuManager.serverApplication;

/**
 * Does different operations on the board of the game
 * Here lies the logic of the game
 */
public class Board {
    private final int boardDimension = 10;
    private int[][] boardPieces;

    /**
     * Firstly, fill the board with 0s, meaning it's empty
     */
    public Board() {
        int i, j;

        //Create the board and set all its spaces to 0 meaning no piece was put here
        boardPieces = new int[boardDimension][boardDimension];
        for (i = 0; i < boardPieces.length; i++) {
            for (j = 0; j < boardPieces[i].length; j++) {
                boardPieces[i][j] = 0;
            }
        }
    }

    /**
     * Place a piece based on coordinates and player
     * @param player
     * @param xCoordinate
     * @param yCoordinate
     */
    public void putPiece(int player, int xCoordinate, int yCoordinate) {
        boardPieces[xCoordinate][yCoordinate] = player;
    }

    /**Having a matrix of 10x10 and checks if there are 5 identical pieces on a straight line (horizontal, vertical, diagonal)
     * Since the board is not big, I can naively iterate through all possible lines
     */
    public int checkForWinner() {
        int winner = 0;
        winner = checkForHorizontal();
        if (winner != 0)
            return winner;

        winner = checkForVertical();
        if (winner != 0)
            return winner;

        /*
        X 0 0 0 0
        0 X 0 0 0
        0 0 X 0 0
        0 0 0 X 0
        0 0 0 0 X
         */
        winner = checkForPrimaryDiagonal();
        if (winner != 0)
            return winner;

        /*
        0 0 0 0 X
        0 0 0 X 0
        0 0 X 0 0
        0 X 0 0 0
        X 0 0 0 0
         */
        winner = checkForSecondaryDiagonal();
        if (winner != 0)
            return winner;
        return 0;
    }

    /**
     * Checks for a horizontal line
     * @return
     */
    private int checkForHorizontal() {
        int i, j, t, currentPiece;
        boolean foundLine;
        for (i = 0; i <= 9; i++) {
            for (j = 0; j <= 5; j++) {
                foundLine = true;
                currentPiece = boardPieces[i][j];
                for (t = j + 1; t <= j + 4; t++)
                    if (boardPieces[i][t] != currentPiece){
                        foundLine = false;
                        break;
                    }
                if(foundLine == true){
                    return currentPiece;
                }
            }
        }
        return 0;
    }

    /**
     * Checks for a vertical line
     * @return
     */
    private int checkForVertical() {
        int i, j, t, currentPiece;
        boolean foundLine;

        for (i = 0; i <= 5; i++) {
            for (j = 0; j <= 9; j++) {
                foundLine = true;
                currentPiece = boardPieces[i][j];
                for (t = i + 1; t <= i + 4; t++) {
                    if (boardPieces[t][j] != currentPiece){
                        foundLine = false;
                        break;
                    }
                }
                if(foundLine == true){
                    return currentPiece;
                }
            }
        }
        return 0;
    }

    /**
     * Checks for a primary diagonal
     * @return
     */
    private int checkForPrimaryDiagonal(){
        int i, j, t, currentPiece;
        boolean foundLine;

        for(i=0; i<=5; i++){
            for(j=0; j<=5; j++){
                foundLine = true;
                currentPiece = boardPieces[i][j];
                for(t=1; t<=4; t++){
                    if(boardPieces[i+t][j+t] != currentPiece){
                        foundLine = true;
                        break;
                    }
                }
                if(foundLine == true){
                    return currentPiece;
                }
            }
        }
        return 0;
    }

    /**
     * Checks for a secondary diagonal
     * @return
     */
    private int checkForSecondaryDiagonal(){
        int i, j, t, currentPiece;
        boolean foundLine;

        for(i=9; i>=4; i--){
            for(j=9; j>=4; j--){
                foundLine = true;
                currentPiece = boardPieces[i][j];
                for(t=1; t<=4; t++){
                    if(boardPieces[i-t][j-t] != currentPiece)
                        break;
                }
                if(foundLine == true){
                    return currentPiece;
                }
            }
        }
        return 0;
    }

    /**
     * Refill the board with 0s
     */
    public void resetPieces(){
        int i, j;
        for (i = 0; i < boardPieces.length; i++) {
            for (j = 0; j < boardPieces[i].length; j++) {
                boardPieces[i][j] = 0;
            }
        }
    }

    /**
     * Print on standard output the state of the board
     */
    public void printBoard(){
        int i, j;
        for (i = 0; i < boardPieces.length; i++) {
            for (j = 0; j < boardPieces[i].length; j++) {
                System.out.print(boardPieces[i][j]-32 + ' ');
            }
            System.out.println(" ");
        }
    }

    /**
     * Get the current state of the board as a string to be printed later
     * @return
     */
    public String getCurrentConfiguration(){
        int i, j;
        String currentConfiguration = "";
        for (i = 0; i < boardPieces.length; i++) {
            for (j = 0; j < boardPieces[i].length; j++) {
                if(boardPieces[i][j] == 0){
                    currentConfiguration = currentConfiguration + "0";
                }
                else if(boardPieces[i][j] == 1){
                    currentConfiguration = currentConfiguration + "1";
                }
                else{
                    currentConfiguration = currentConfiguration + "2";
                }
                currentConfiguration = currentConfiguration + ' ';
            }
            currentConfiguration = currentConfiguration + '\n';
        }
        return currentConfiguration;
    }
}
