package com.ada.avanade.dto;

import lombok.Data;

@Data
public class TurnDTO {
    private Character attacker;
    private Character defender;
    private Integer attack;
    private Integer defense;
}