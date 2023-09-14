package com.ada.avanade.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.ada.avanade.dto.CharacterDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurnService {
    
    public Integer calculateAttack(CharacterDTO characterDTO) {
        Random random = new Random();
        int diceRoll = random.nextInt(12) + 1;
        return diceRoll + characterDTO.getStrength() + characterDTO.getAgility();
    }

    public Integer calculateDefense(CharacterDTO characterDTO) {
        Random random = new Random();
        int diceRoll = random.nextInt(12) + 1;
        return diceRoll + characterDTO.getDefense() + characterDTO.getAgility();
    }

    public Integer calculateDamage(Integer attack, Integer defense, CharacterDTO attackerDTO) {
        if (attack > defense) {
            int numberOfDice = attackerDTO.getDiceNumber();
            int facesOnDice = attackerDTO.getSideNumber();
            
            Random random = new Random();
            int diceSum = 0;
            
            for (int i = 0; i < numberOfDice; i++) {
                diceSum += random.nextInt(facesOnDice) + 1;
            }
            
            return diceSum + attackerDTO.getStrength();
        }
        return 0;
    }
}
