package com.gomokumanager.GomokuManager.serverApplication;

import com.gomokumanager.GomokuManager.controllers.GameController;
import com.gomokumanager.GomokuManager.models.Game;

/**
 * Creates a playing board and does operations on it to manage the logic of the game
 */
public class GameLogic {
    private Board gameBoard;
    private int winner;
    private GameController gameController;


    public GameLogic(){
        gameBoard = new Board();
    }

    /**
     * Manages a move - puts the piece, prints the board and finally checks for a winner
     * @param player
     * @param x
     * @param y
     */
    public void manageMove(int player, int x, int y){
        gameBoard.putPiece(player, x, y);
        gameBoard.printBoard();
        winner = gameBoard.checkForWinner();
        if(winner != 0){
            System.out.println("Good job player " + player + " you won!");
            saveGameToDB();
            gameBoard.resetPieces();
        }
    }

    /**
     * After a winner is found, the game is saved into the database
     */
    private void saveGameToDB(){
        Game game = new Game();
        game.setBoardState(this.getBoardState());
        game.setWinner(this.winner);
        //The previous lab didn't require to have a log-in for players so I just passed null here
        game.setPlayer1(null);
        game.setPlayer2(null);
        gameController.createOrUpdateGame(game);
    }

    /**
     * Get the current state of the board
     * @return
     */
    public String getBoardState(){
        String boardState;
        boardState = gameBoard.getCurrentConfiguration();
        return boardState;
    }
}
