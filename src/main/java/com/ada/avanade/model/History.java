package com.ada.avanade.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "defender_id")
    private Character defender;

    @ManyToOne
    @JoinColumn(name = "attacker_id")
    private Character attacker;

    private Integer turn;
    private Integer attack;
    private Integer defense;
    private Integer damage;
}
