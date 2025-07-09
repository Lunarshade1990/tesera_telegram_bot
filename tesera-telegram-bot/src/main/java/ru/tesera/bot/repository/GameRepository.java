package ru.tesera.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tesera.bot.entity.Game;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByTeseraId(Long teseraId);
    Optional<Game> findByTitle(String title);
} 