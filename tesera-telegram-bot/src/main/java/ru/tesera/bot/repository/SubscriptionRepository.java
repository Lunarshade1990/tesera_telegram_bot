package ru.tesera.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tesera.bot.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);
    List<Subscription> findByGameId(Long gameId);
    List<Subscription> findByDayOfWeek(String dayOfWeek);
} 