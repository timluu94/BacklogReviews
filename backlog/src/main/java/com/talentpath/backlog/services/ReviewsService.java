package com.talentpath.backlog.services;

import com.talentpath.backlog.daos.ReviewsDao;
import com.talentpath.backlog.models.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsService {

    @Autowired
    ReviewsDao dao;

    public ReviewsService(ReviewsDao dao) { this.dao = dao; }

    public Review getByGameId(Integer gameId) {
        return dao.getByGameId(gameId);
    }

    public void add(Review gameReview) {
        dao.add(gameReview);
    }

    public void edit(Integer gameId, Review gameReview) {
        if (gameId.equals(gameReview.getGameId())) {
            dao.edit(gameReview);
        }
    }

    public void delete(Integer gameId) {
        dao.delete(gameId);
    }
}
