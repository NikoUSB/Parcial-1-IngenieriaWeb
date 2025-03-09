package com.crud.partial.services;

import com.crud.partial.entities.GamesEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamesService {

    List<GamesEntity> getAllGames();

    GamesEntity saveGame(GamesEntity game);

    Optional <GamesEntity> getGame(UUID id);

    GamesEntity updateGame(UUID id, GamesEntity newGame);

    boolean deleteGame(UUID id);

}

