package ru.tesera.bot.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "meetings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "max_participants", nullable = false)
    private Integer maxParticipants;

    @Column(name = "description")
    private String description;
} 