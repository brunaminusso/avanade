package com.ada.avanade.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ada.avanade.model.Character;
import com.ada.avanade.service.CharacterService;
import com.ada.avanade.dto.CharacterDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody CharacterDTO characterDTO) {
        Character createdCharacter = characterService.createCharacter(characterDTO);

        return ResponseEntity.ok(createdCharacter);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterService.getAllCharacters();
        return ResponseEntity.ok(characters);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterService.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }
}
