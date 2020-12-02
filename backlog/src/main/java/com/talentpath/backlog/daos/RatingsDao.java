package com.talentpath.backlog.daos;

import com.talentpath.backlog.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Profile({ "production" })
public class RatingsDao {

    @Autowired
    private JdbcTemplate template;

    public Rating getByGameId(Integer gameId) {
        String sql = "select * from \"Ratings\" " +
                "where \"gameId\" = "+ gameId + ";";

        List<Rating> gameRatings = template.query(sql, new RatingMapper());
        Rating gameRating = gameRatings.get(0);
        return gameRating;
    }

    public void add(Rating gameRating) {
        String sql = "insert into \"Ratings\" (\"rating\", \"gameId\") " +
                "values (" + gameRating.getRating() + ", " + gameRating.getGameId() + ");";

        template.update(sql);
    }

    public void edit(Rating gameRating) {
        String sql = "update \"Ratings\" " +
                "set \"rating\" = " + gameRating.getRating() + " " +
                "where \"gameId\" = " + gameRating.getGameId() + ";";

        template.update(sql);
    }

    public void delete(Integer gameId) {
        String sql = "delete from \"Ratings\"" +
                "where \"gameId\" = " +gameId + ";";

        template.update(sql);
    }

    public void _hardReset() {
        String sql = "truncate \"Ratings\" restart identity;";

        template.update(sql);
    }

    class RatingMapper implements RowMapper<Rating> {
        @Override
        public Rating mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt("id");
            Integer rating = resultSet.getInt("rating");
            Integer gameId = resultSet.getInt("gameId");

            Rating gameRating = new Rating();
            gameRating.setId(id);
            gameRating.setRating(rating);
            gameRating.setGameId(gameId);

            return gameRating;
        }
    }
}
