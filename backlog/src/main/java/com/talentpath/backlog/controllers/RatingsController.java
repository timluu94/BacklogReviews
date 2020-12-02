package com.talentpath.backlog.controllers;

import com.talentpath.backlog.models.Rating;
import com.talentpath.backlog.services.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins="http://localhost:4200")
public class RatingsController {
    @Autowired
    RatingsService service;

    @GetMapping("/{gameId}")
    public Rating getRatingByGameId(@PathVariable Integer gameId) {
        return service.getByGameId(gameId);
    }

    @PostMapping("")
    public void addRating(@RequestBody Rating gameRating) {
        service.add(gameRating);
    }

    @PutMapping("/{gameId}")
    public void editRating(@PathVariable Integer gameId, @RequestBody Rating gameRating) {
        service.edit(gameId, gameRating);
    }

    @DeleteMapping("/{gameId}")
    public void deleteRating(@PathVariable Integer gameId) {
        service.delete(gameId);
    }

    @DeleteMapping("/hard_reset")
    public void _hardReset() {
        service._hardReset();
    }
}
