package com.ada.avanade.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ada.avanade.model.Battle;
import com.ada.avanade.service.BattleService;
import com.ada.avanade.dto.BattleDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/battle")
@RequiredArgsConstructor
public class BattleController {
    private final BattleService battleService;

    @PostMapping
    public ResponseEntity<Battle> createBattle(@RequestBody BattleDTO battleDTO) {
        Battle createdBattle = battleService.createBattle(battleDTO);

        return ResponseEntity.ok(createdBattle);
    }

    @PostMapping("/start")
    public void startBattle(@RequestBody BattleDTO battleDTO) {
        battleService.startBattle(battleDTO);
    }
}
