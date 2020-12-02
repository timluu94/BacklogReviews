package com.talentpath.backlog.daos;

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
public class RecentGamesDao {

    @Autowired
    private JdbcTemplate template;

    public void add(Integer gameId) {
        String sql = "insert into \"MostRecent\" (\"gameId\") " +
                "values (" + gameId + ");";

        template.update(sql);
    }

    public List<Integer> getMostRecent() {
        String sql = "select max(\"id\"), \"gameId\" from \"MostRecent\" " +
                "group by \"gameId\" " +
                "order by max(\"id\") desc " +
                "limit 3;";

        List<Integer> mostRecentGameIds = template.query(sql, new GameIdMapper());
        return mostRecentGameIds;

    }

    class GameIdMapper implements RowMapper<Integer> {
        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("gameId");
        }
    }
}
