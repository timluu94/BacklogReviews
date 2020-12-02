package com.talentpath.backlog.controllers;

import com.talentpath.backlog.models.Review;
import com.talentpath.backlog.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins="http://localhost:4200")
public class ReviewsController {

    @Autowired
    ReviewsService service;

    @GetMapping("/{gameId}")
    public Review getReviewByGameId(@PathVariable Integer gameId) {
        return service.getByGameId(gameId);
    }

    @PostMapping("")
    public void addReview(@RequestBody Review gameReview) {
        service.add(gameReview);
    }

    @PutMapping("/{gameId}")
    public void editReview(@PathVariable Integer gameId, @RequestBody Review gameReview) {
        service.edit(gameId, gameReview);
    }

    @DeleteMapping("/{gameId}")
    public void deleteReview(@PathVariable Integer gameId) { service.delete(gameId); }
}
