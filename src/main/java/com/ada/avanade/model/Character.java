package com.ada.avanade.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
