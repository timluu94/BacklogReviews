package com.talentpath.backlog.services;

import com.talentpath.backlog.daos.RatingsDao;
import com.talentpath.backlog.daos.RecentGamesDao;
import com.talentpath.backlog.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingsService {

    @Autowired
    RatingsDao dao;

    @Autowired
    RecentGamesDao recentDao;

    public RatingsService(RatingsDao dao, RecentGamesDao recentDao) {
        this.dao = dao;
        this.recentDao = recentDao;
    }

    public Rating getByGameId(Integer gameId) {
        return dao.getByGameId(gameId);
    }

    public void add(Rating gameRating) {
        dao.add(gameRating);
        recentDao.add(gameRating.getGameId());
    }

    public void edit(Integer gameId, Rating gameRating) {
        if (gameId.equals(gameRating.getGameId())) {
            dao.edit(gameRating);
            recentDao.add(gameRating.getGameId());
        }
    }

    public void delete(Integer gameId) {
        dao.delete(gameId);
    }

    public void _hardReset() {
        dao._hardReset();
    }

}
