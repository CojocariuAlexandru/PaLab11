package com.gomokumanager.GomokuManager.services;

import com.gomokumanager.GomokuManager.models.Game;
import com.gomokumanager.GomokuManager.models.Player;
import com.gomokumanager.GomokuManager.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Services for games which are called from controllers
 */

@Service
public class GameService {
    //The service is linked with the repository
    @Autowired
    private GameRepository gameRepository;

    /**
     * Gets all the games from the repository
     * If no games are found, returns an empty ArrayList
     *
     * @return
     */
    public List<Game> getAllGames() {
        List<Game> allGames = gameRepository.findAll();
        if (allGames.size() > 0) {
            return allGames;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Puts a game into the repository and assigns an ID
     *
     * @param game
     * @return
     */
    public Game createOrUpdateGame(Game game) {
        game.setId(UUID.randomUUID());
        game = gameRepository.save(game);
        return game;
    }
}
