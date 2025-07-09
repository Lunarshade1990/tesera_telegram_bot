package ru.tesera.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tesera.bot.entity.UserGame;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Long> {
    List<UserGame> findByUserId(Long userId);
    List<UserGame> findByGameId(Long gameId);
} 