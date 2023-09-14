package com.ada.avanade.dto;

import java.time.LocalDateTime;

import com.ada.avanade.model.Character;

import lombok.Data;

@Data
public class BattleDTO {
    private String player;
    private Character playerChoice;
    private Character opponentChoice;
    private Integer dicePlayer;
    private Integer diceMachine;
    private Character attacker;
    private Character defender;
    private LocalDateTime createdAt;
}
