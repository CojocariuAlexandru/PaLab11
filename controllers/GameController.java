package com.gomokumanager.GomokuManager.controllers;

import com.gomokumanager.GomokuManager.models.Game;
import com.gomokumanager.GomokuManager.services.GameService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getGames(){
        List<Game> games = gameService.getAllGames();
        return new ResponseEntity<List<Game>>(games, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    @PutMapping
    public ResponseEntity<Game> createOrUpdateGame(@RequestBody Game game){
        Game gameCreated = gameService.createOrUpdateGame(game);
        return new ResponseEntity<Game>(gameCreated, new HttpHeaders(), HttpStatus.CREATED);
    }

}
