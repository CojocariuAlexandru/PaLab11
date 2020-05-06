package com.gomokumanager.GomokuManager.serverApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Depending on the cliet's action, return to him an appropriate response
 */
public class ClientThread {
    // Adapted from course's slides
    // https://profs.info.uaic.ro/~acf/java/slides/en/networking_slide_en.pdf

    private Socket socket = null;
    private String responseToClient;
    private int returnedCodeToGameServer;

    public ClientThread(Socket socket){
        this.socket = socket;
    }

    /**
     * Reads form client, and sends back the appropriate response
     * @param gameServerRunningFor
     * @return
     */
    public int start(GameServer gameServerRunningFor){
        try{
            //Get request client -> server
            BufferedReader bufferIN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter bufferOUT = new PrintWriter(socket.getOutputStream());
            String request = bufferIN.readLine();

            createResponseForRequest(gameServerRunningFor, request);

            //Send response server -> client
            bufferOUT.println(responseToClient);
            bufferOUT.flush();
            return returnedCodeToGameServer;
        }
        catch(IOException e){
            System.err.println("Communication error: " + e);
        }
        finally{
            try{
                socket.close();
            }
            catch (IOException e){
                System.err.println(e);
            }
        }
        return 1;
    }

    /**
     * Checks for coordinates to be valid (within the borders)
     * @param x
     * @param y
     * @return
     */
    private boolean validateCoordinates(int x, int y){
        if(x > 9 || y > 9)
            return false;
        if(x < 1 || y < 1)
            return false;
        return true;
    }

    /**
     * Depending on the received action, return appropriate code and message to the client
     * @param gameServerRunningFor
     * @param request
     */
    private void createResponseForRequest(GameServer gameServerRunningFor, String request){
        if(request.compareTo("stop") == 0){
            returnedCodeToGameServer = -1;
            responseToClient = "You stopped the server successfully.";
        }
        else if(request.compareTo("start game") == 0){
            returnedCodeToGameServer = 2;
            responseToClient = "You started the game successfully.";
        }
        else if(request.startsWith("submit move") == true){
            int xCoordinate = request.charAt(12) - '0';
            int yCoordinate = request.charAt(14) - '0';
            if(validateCoordinates(xCoordinate, yCoordinate) == false){
                responseToClient = "Invalid coordinates entered";
                returnedCodeToGameServer = 0;
            }
            else{
                returnedCodeToGameServer = xCoordinate*10+yCoordinate;
                responseToClient = gameServerRunningFor.manageValidMove(returnedCodeToGameServer);
            }
        }
        else{
            returnedCodeToGameServer = 0;
            responseToClient = "Invalid command entered";
        }
    }
}
