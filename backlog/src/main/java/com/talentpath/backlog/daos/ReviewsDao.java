package com.talentpath.backlog.daos;

import com.talentpath.backlog.models.Rating;
import com.talentpath.backlog.models.Review;
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
public class ReviewsDao {

    @Autowired
    private JdbcTemplate template;

    public Review getByGameId(Integer gameId) {
        String sql = "select * from \"Reviews\" " +
                "where \"gameId\" = "+ gameId + ";";

        List<Review> gameReviews = template.query(sql, new ReviewMapper());
        Review gameReview = gameReviews.get(0);
        return gameReview;
    }

    public void add(Review gameReview) {
        Object[] params = new Object[]{ gameReview.getReview(), gameReview.getGameId() };
        String sql = "insert into \"Reviews\" (\"review\", \"gameId\") values (?, ?);";

        template.update(sql, params);
    }

    public void edit(Review gameReview) {
        Object[] params = new Object[]{ gameReview.getReview(), gameReview.getGameId() };
        String sql = "update \"Reviews\" set \"review\" = ? where \"gameId\" = ?;";

        template.update(sql, params);
    }

    public void delete(Integer gameId) {
        String sql = "delete from \"Reviews\"" +
                "where \"gameId\" = " + gameId + ";";

        template.update(sql);
    }

    class ReviewMapper implements RowMapper<Review> {

        @Override
        public Review mapRow(ResultSet resultSet, int i) throws SQLException {
            Integer id = resultSet.getInt("id");
            String review = resultSet.getString("review");
            Integer gameId = resultSet.getInt("gameId");

            Review gameReview = new Review();
            gameReview.setId(id);
            gameReview.setReview(review);
            gameReview.setGameId(gameId);

            return gameReview;
        }
    }
}
