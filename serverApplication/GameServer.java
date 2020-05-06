package com.gomokumanager.GomokuManager.serverApplication;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Accepts the clients and prints messages according to the reqeust received
 */

public class GameServer {
    // Adapted from course's slides
    // https://profs.info.uaic.ro/~acf/java/slides/en/networking_slide_en.pdf

    public static final int PORT = 8100;
    private int returnedStatus;
    public boolean isGameStarted;
    private static int currentPlayer;
    GameLogic gameLogicSolver = new GameLogic();

    /**
     * Creates the socket which accepts the clients
     * @throws IOException
     */
    public GameServer() throws IOException{
        ServerSocket serverSocket = null;
        currentPlayer = 1;
        try{
            serverSocket = new ServerSocket(PORT);
            manageClientActions(serverSocket);
        }
        catch(IOException e){
            System.err.println("Error: " + e);
        }
        finally{
            serverSocket.close();
        }
    }

    /**
     * Accepts clients
     * Prints on screen information based on the returned code given by the client thread
     * @param serverSocket
     */
    private void manageClientActions(ServerSocket serverSocket){
        try{
            while(true){
                System.out.println("Waiting for a client...");
                Socket socket = serverSocket.accept();
                returnedStatus = new ClientThread(socket).start(this);
                if(returnedStatus == -1){
                    System.out.println("Server was stopped.");
                    break;
                }
                else if(returnedStatus == 0){
                    System.out.println("Client entered invalid coordinates");
                }
                else if(returnedStatus == 2){
                    System.out.println("Game was started on the server");
                    this.isGameStarted = true;
                }
                else{
                    System.out.println("Requested move finished succesfully");
                }
            }
        }
        catch(IOException e){
            System.err.println("Error: " + e);
        }
    }

    /**
     * Changes beetween first and second player
     */
    private void changePlayer(){
        if(currentPlayer == 1){
            currentPlayer = 2;
        }
        else{
            currentPlayer = 1;
        }
    }

    /**
     * Checks if the game started and prints/sends according messages
     * @param coordinates
     * @return
     */
    public String manageValidMove(int coordinates){
        String messageBackToClient;
        if(isGameStarted == true){
            gameLogicSolver.manageMove(currentPlayer, coordinates%10, coordinates/10);
            System.out.println("Player " + currentPlayer + " successfuly put a piece at coordinates: " + coordinates%10 + ", " + coordinates/10);
            messageBackToClient = "Player " + currentPlayer + " successfuly put a piece at coordinates: " + coordinates%10 + " , " + coordinates/10;
            changePlayer();
            return messageBackToClient;
        }
        else{
            System.out.println("I can't make a move since the game is not started yet.");
            messageBackToClient = "You can't move yet, the game has not started";
            return  messageBackToClient;
        }
    }
}
