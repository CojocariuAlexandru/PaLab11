package com.gomokumanager.GomokuManager.controllers;


import com.gomokumanager.GomokuManager.models.Game;
import com.gomokumanager.GomokuManager.models.Player;
import com.gomokumanager.GomokuManager.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/players")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<List<Player>>(players, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<Player> createOrUpdatePlayer(@RequestBody Player player){
        Player playerCreated = playerService.createOrUpdatePlayer(player);
        return new ResponseEntity<Player>(playerCreated, new HttpHeaders(), HttpStatus.CREATED);
    }
}
