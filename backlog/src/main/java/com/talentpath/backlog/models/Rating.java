package com.talentpath.backlog.models;

public class Rating {
    private Integer id;
    private Integer rating;
    private Integer gameId;

    public Rating() {}

    public Rating(Rating copy) {
        this.id = copy.id;
        this.rating = copy.rating;
        this.gameId = copy.gameId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }
}
