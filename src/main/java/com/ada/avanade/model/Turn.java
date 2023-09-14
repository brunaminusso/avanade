package com.ada.avanade.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "turns")
public class Turn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "battle_id")
    private Battle battle;

    private Integer turn;

    @ManyToOne
    @JoinColumn(name = "defender_id")
    private Character defender;

    private Integer attack;
    private Integer defense;
    private Integer damage;
}
