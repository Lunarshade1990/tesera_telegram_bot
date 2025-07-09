package ru.tesera.bot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
} 