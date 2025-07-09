package ru.tesera.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tesera.bot.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTelegramId(Long telegramId);
    Optional<User> findByTeseraNickname(String teseraNickname);
} 