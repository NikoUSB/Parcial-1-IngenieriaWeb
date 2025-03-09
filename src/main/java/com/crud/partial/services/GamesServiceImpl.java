package com.crud.partial.services;

import com.crud.partial.entities.GamesEntity;
import com.crud.partial.repository.GamesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;

    @PostConstruct
    public void init() {
        if (gamesRepository.count() == 0) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("games.json");

                if (inputStream == null) {
                    System.err.println("Can't find games.json.");
                    return;
                }

                List<GamesEntity> games = objectMapper.readValue(inputStream, new TypeReference<>() {});
                gamesRepository.saveAll(games);

            } catch (Exception e) {
                System.out.println("Read data failed");
            }
        }
    }

    @Override
    public List<GamesEntity> getAllGames() {
        return gamesRepository.findAll();
    }

    @Override
    public GamesEntity saveGame(GamesEntity game) {
        return gamesRepository.save(game);
    }

    @Override
    public Optional<GamesEntity> getGame(UUID id) {
        GamesEntity game = gamesRepository.findById(id).orElse(null);
        return Optional.ofNullable(game);
    }

    @Override
    public GamesEntity updateGame(UUID id, GamesEntity newGame) {
        GamesEntity game = gamesRepository.findById(id).get();
        game.setGameName(newGame.getGameName());
        game.setGameGenre(newGame.getGameGenre());
        game.setGameYear(newGame.getGameYear());
        game.setGameDev(newGame.getGameDev());
        return gamesRepository.save(game);
    }

    @Override
    public boolean deleteGame(UUID id) {
        try{
            gamesRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println("Delete failed");
            return false;
        }
    }

}
