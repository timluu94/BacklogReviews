package com.talentpath.backlog.services;

import com.talentpath.backlog.daos.RecentGamesDao;
import com.talentpath.backlog.models.Game;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class GamesService {

    @Autowired
    RecentGamesDao recentDao;

    private final RestTemplate template;

    private String url = "https://api.igdb.com/v4/games";
    // TODO: hide this someplace else
    private String CLIENT_ID = "96c0dewsf4abvsouhn368cynmfelms";
    private String AUTHORIZATION = "Bearer ohvo3ciq9v81r2jyrgat5d8hwrbluo";

    public GamesService(RecentGamesDao recentDao) {
        this.template = new RestTemplate();
        this.recentDao = recentDao;
    }

    public Game[] searchGames(String term, Integer page) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AUTHORIZATION);
        headers.add("Client-ID", CLIENT_ID);
        headers.add("Content-Type", "text/plain");

        Integer offset = 10 * (page - 1);
        String searchTerm = "fields name, summary, cover.url; where name ~ *\"" + term + "\"*; limit 11; offset " + offset + ";";

        HttpEntity<String> entity = new HttpEntity<>(searchTerm, headers);

        ResponseEntity<Game[]> response = template.exchange(url, HttpMethod.POST, entity, Game[].class);
        Game[] games = response.getBody();
        addImageData(games);
        return games;
    }

    public Game getGameById(Integer gameId) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", AUTHORIZATION);
        headers.add("Client-ID", CLIENT_ID);
        headers.add("Content-Type", "text/plain");

        String searchTerm = "fields name, summary, cover.url; where id = " + gameId + ";";

        HttpEntity<String> entity = new HttpEntity<>(searchTerm, headers);

        ResponseEntity<Game[]> response = template.exchange(url, HttpMethod.POST, entity, Game[].class);
        Game[] games = response.getBody();
        if (games.length > 0) {
            addImageData(games);
            return games[0];
        }

        return null;
    }

    public List<Game> getMostRecentGames() throws IOException {
        List<Integer> mostRecentGameIds = recentDao.getMostRecent();
        List<Game> mostRecentGames = new ArrayList<>();

        for (int i = 0; i < mostRecentGameIds.size(); i++) {
            mostRecentGames.add(getGameById(mostRecentGameIds.get(i)));
        }

        return mostRecentGames;
    }

    private void addImageData(Game[] games) throws IOException {
        for (int i = 0; i < games.length; i++) {
            InputStream in = null;
            if (games[i].getCover() != null) {
                String largeImage = games[i].getCover().getUrl().replaceAll("t_thumb", "t_cover_big_2x");
                URL url = new URL("https:" + largeImage);
                in = new BufferedInputStream(url.openStream());
            } else {
                in = new ClassPathResource("/static/unavailable.jpg").getInputStream();
            }
            byte[] encoded = IOUtils.toByteArray(in);
            encoded = Base64.getEncoder().encode(encoded);
            games[i].setImageData(new String(encoded));
        }
    }
}
