package com.ada.avanade.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "battles")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String player;

    @OneToOne
    @JoinColumn(name = "player_choice")
    private Character playerChoice;

    @OneToOne
    @JoinColumn(name = "opponent_choice")
    private Character opponentChoice;
    
    private Integer dicePlayer;
    private Integer diceMachine;

    @OneToOne
    @JoinColumn(name = "attacker_id")
    private Character attacker;

    @OneToOne
    @JoinColumn(name = "defender_id")
    private Character defender;

    private LocalDateTime createdAt;    
}
