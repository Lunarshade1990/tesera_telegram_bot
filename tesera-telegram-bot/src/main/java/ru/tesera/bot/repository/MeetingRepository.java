package ru.tesera.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tesera.bot.entity.Meeting;

import java.time.LocalDateTime;
import java.util.List;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByDatetimeAfter(LocalDateTime dateTime);
} 