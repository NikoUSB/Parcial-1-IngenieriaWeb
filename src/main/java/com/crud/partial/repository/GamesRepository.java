package com.crud.partial.repository;

import com.crud.partial.entities.GamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GamesRepository extends JpaRepository<GamesEntity, UUID> {

}
