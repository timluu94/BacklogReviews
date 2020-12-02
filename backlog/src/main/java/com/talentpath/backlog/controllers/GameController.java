package com.talentpath.backlog.controllers;

import com.talentpath.backlog.models.Game;
import com.talentpath.backlog.services.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class GameController {

    @Autowired
    GamesService service;

    @GetMapping("/search")
    public Game[] searchGames(@RequestParam String term, @RequestParam Integer page) throws IOException {
        return service.searchGames(term, page);
    }

    @GetMapping("/game/{gameId}")
    public Game getGameById(@PathVariable Integer gameId) throws IOException {
        return service.getGameById(gameId);
    }

    @GetMapping("/most_recent")
    public List<Game> getMostRecentlyRated() throws IOException {
        return service.getMostRecentGames();
    }

}
