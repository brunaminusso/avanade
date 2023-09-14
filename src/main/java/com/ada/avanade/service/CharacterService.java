package com.ada.avanade.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ada.avanade.model.Character;
import com.ada.avanade.repository.CharacterRepository;
import com.ada.avanade.dto.CharacterDTO;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;

    public Character createCharacter(CharacterDTO characterDTO) {
        Character character = new Character();
        character.setType(characterDTO.getType());
        character.setCharacterClass(characterDTO.getCharacterClass());
        character.setLifePoints(characterDTO.getLifePoints());
        character.setStrength(characterDTO.getStrength());
        character.setDefense(characterDTO.getDefense());
        character.setAgility(characterDTO.getAgility());
        character.setDiceNumber(characterDTO.getDiceNumber());
        character.setSideNumber(characterDTO.getSideNumber());

        return characterRepository.save(character);
    }

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character getCharacterById(Long characterId) {
        return characterRepository.findById(characterId).orElse(null);
    }

    public boolean characterExists(Long characterId) {
        return characterRepository.existsById(characterId);
    }

    public void deleteCharacter(Long characterId) {
        characterRepository.deleteById(characterId);
    }

    public List<Character> getMonsters() {
        return characterRepository.findByType("monstro");
    }

    public Character getRandomMonster() {
        List<Character> monsters = getMonsters();
        Random random = new Random();
        
        int randomIndex = random.nextInt(monsters.size());
        return monsters.get(randomIndex);
    }    
}
