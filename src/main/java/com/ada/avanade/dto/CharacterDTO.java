package com.ada.avanade.dto;

import lombok.Data;

@Data
public class CharacterDTO {
    private Long id;
    private String type;
    private String characterClass;
    private Integer lifePoints;
    private Integer strength;
    private Integer defense;
    private Integer agility;
    private Integer diceNumber;
    private Integer sideNumber;
}
