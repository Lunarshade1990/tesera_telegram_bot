package ru.tesera.bot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tesera_id", nullable = false, unique = true)
    private Long teseraId;

    @Column(name = "title", nullable = false)
    private String title;
} 