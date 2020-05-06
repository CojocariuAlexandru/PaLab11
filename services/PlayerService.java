package com.gomokumanager.GomokuManager.services;

import com.gomokumanager.GomokuManager.models.Player;
import com.gomokumanager.GomokuManager.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Services for players which are called from controllers
 */

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;


    /**
     * Gets all the players from the repository
     * If no players are found, returns an empty ArrayList
     * @return
     */
    public List<Player> getAllPlayers() {
        List<Player> allPlayers = playerRepository.findAll();
        if (allPlayers.size() > 0) {
            return allPlayers;
        } else {
            return new ArrayList<>();
        }
    }


    /**
     * Puts a player into the repository and assigns an ID
     *
     * @param player
     * @return
     */
    public Player createOrUpdatePlayer(Player player) {
        player.setId(UUID.randomUUID());
        player = playerRepository.save(player);
        return player;
    }

    /**
     * Deletes a player from the repository
     *
     * @param player
     */
    public void deletePlayer(Player player) {
        playerRepository.delete(player);
    }
}
