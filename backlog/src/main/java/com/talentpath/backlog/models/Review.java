package com.talentpath.backlog.models;

public class Review {
    private Integer id;
    private String review;
    private Integer gameId;

    public Review() {}

    public Review(Review copy) {
        this.id = copy.id;
        this.review = copy.review;
        this.gameId = copy.gameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
