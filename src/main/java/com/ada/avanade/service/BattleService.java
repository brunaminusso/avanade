package com.ada.avanade.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import com.ada.avanade.model.Battle;
import com.ada.avanade.repository.BattleRepository;
import com.ada.avanade.dto.BattleDTO;

@Service
@RequiredArgsConstructor
public class BattleService {
    private final BattleRepository battleRepository;
    
    public Battle createBattle(BattleDTO battleDTO) {
        Battle battle = new Battle();
        battle.setPlayer(battleDTO.getPlayer());
        battle.setDicePlayer(battleDTO.getDicePlayer());
        battle.setDiceMachine(battleDTO.getDiceMachine());
        battle.setAttacker(battleDTO.getAttacker());
        battle.setDefender(battleDTO.getDefender());
        battle.setCreatedAt(battleDTO.getCreatedAt());
    
        return battleRepository.save(battle);
    }

    public Battle startBattle(BattleDTO battleDTO) {
        Battle battle = new Battle();
        battle.setPlayer(battleDTO.getPlayer());
        battle.setDicePlayer(battleDTO.getDicePlayer());
        battle.setDiceMachine(battleDTO.getDiceMachine());
        battle.setAttacker(battleDTO.getAttacker());
        battle.setDefender(battleDTO.getDefender());
        battle.setCreatedAt(battleDTO.getCreatedAt());
    
        return battleRepository.save(battle);
    }
}
