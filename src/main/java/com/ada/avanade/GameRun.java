package com.ada.avanade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

import com.ada.avanade.dto.BattleDTO;
import com.ada.avanade.dto.CharacterDTO;
import com.ada.avanade.service.CharacterService;
import com.ada.avanade.service.DiceRoller;
import com.ada.avanade.model.Battle;
import com.ada.avanade.model.Character;
import com.ada.avanade.repository.BattleRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.ada.avanade")
public class GameRun implements CommandLineRunner {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private DiceRoller diceRoller;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Advanced Dangerous & Dragons (AD&D)!");
        System.out.print("Qual é o seu nome? ");
        String player = scanner.nextLine();

        BattleDTO battleDTO = new BattleDTO();
        battleDTO.setPlayer(player);
        
        int opcao;
        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar personagem");
            System.out.println("2 - Apagar personagem");
            System.out.println("3 - Listar personagens");
            System.out.println("4 - Iniciar batalha");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    CharacterDTO characterDTO = new CharacterDTO();

                    System.out.print("Tipo do personagem: ");
                    characterDTO.setType(scanner.nextLine());

                    System.out.print("Classe do personagem: ");
                    characterDTO.setCharacterClass(scanner.nextLine());

                    String[] fieldNames = {"Pontos de Vida", "Força", "Defesa", "Agilidade", "Número de Dados", "Número de Lados"};
                    int[] fieldValues = new int[6];

                    for (int i = 0; i < fieldNames.length; i++) {
                        boolean validInput = false;

                        do {
                            System.out.print(fieldNames[i] + ": ");
                            String userInput = scanner.nextLine();

                            try {
                                fieldValues[i] = Integer.parseInt(userInput);
                                validInput = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Por favor, insira um número inteiro para " + fieldNames[i] + ".");
                            }
                        } while (!validInput);
                    }

                    characterDTO.setLifePoints(fieldValues[0]);
                    characterDTO.setStrength(fieldValues[1]);
                    characterDTO.setDefense(fieldValues[2]);
                    characterDTO.setAgility(fieldValues[3]);
                    characterDTO.setDiceNumber(fieldValues[4]);
                    characterDTO.setSideNumber(fieldValues[5]);

                    characterService.createCharacter(characterDTO);

                    System.out.println("Personagem criado com sucesso!");
                    break;
                case 2:
                    boolean continueDeletion = true;
                    do {
                        listCharacters();
                        System.out.print("Digite o ID do personagem que deseja apagar (ou '0' para cancelar): ");
                        try {
                            Long characterIdToDelete = scanner.nextLong();
                            scanner.nextLine();
                    
                            if (characterIdToDelete == 0) {
                                continueDeletion = false;
                            } else if (characterService.characterExists(characterIdToDelete)) {
                                characterService.deleteCharacter(characterIdToDelete);
                                System.out.println("Personagem apagado com sucesso!");
                                continueDeletion = false;
                            } else {
                                System.out.println("Personagem com o ID fornecido não existe.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, insira um número válido.");
                            scanner.nextLine();
                        }
                    } while (continueDeletion);
                    break;
                case 3:
                    listCharacters();
                    System.out.println("Pressione Enter para voltar ao menu.");
                    scanner.nextLine();
                    break;        
                case 4:
                    startBattle(battleDTO);
                    System.out.println("Pressione Enter para voltar ao menu.");
                    scanner.nextLine();
                    break;    
                default:
                    System.out.println("Opção inválida");
            }
        } while (true);
    }

    public void listCharacters() {
        List<Character> characters = characterService.getAllCharacters();
        
        System.out.println("Personagens Disponíveis:");
        System.out.printf("%-4s | %-8s | %-10s | %-15s | %-6s | %-7s | %-10s | %-15s | %-16s%n",
        "ID", "Tipo", "Classe", "Pontos de Vida", "Força", "Defesa", "Agilidade", "Núm. de Dados", "Núm. de Lados");
        for (Character character : characters) {
            System.out.printf("%-4d | %-8s | %-10s | %-15d | %-6d | %-7d | %-10d | %-15d | %-16d%n",
                    character.getId(),
                    character.getType(),
                    character.getCharacterClass(),
                    character.getLifePoints(),
                    character.getStrength(),
                    character.getDefense(),
                    character.getAgility(),
                    character.getDiceNumber(),
                    character.getSideNumber());
        }
    }

    public void listMonsters() {
        List<Character> monsters = characterService.getMonsters();
        System.out.println("Monstros Disponíveis:");
        System.out.printf("%-4s | %-8s | %-10s | %-15s | %-6s | %-7s | %-10s | %-15s | %-16s%n",
        "ID", "Tipo", "Classe", "Pontos de Vida", "Força", "Defesa", "Agilidade", "Núm. de Dados", "Núm. de Lados");
        for (Character monster : monsters) {
            System.out.printf("%-4d | %-8s | %-10s | %-15d | %-6d | %-7d | %-10d | %-15d | %-16d%n",
                    monster.getId(), monster.getType(), monster.getCharacterClass(),
                    monster.getLifePoints(), monster.getStrength(), monster.getDefense(),
                    monster.getAgility(), monster.getDiceNumber(), monster.getSideNumber());
        }
    }

    public void startBattle(BattleDTO battleDTO) {
        Scanner scanner = new Scanner(System.in);
        Battle battle = new Battle();
        listCharacters();
            
        System.out.print("Escolha o ID do seu personagem: ");
        Long playerCharacterId = scanner.nextLong();
        Character playerCharacter = characterService.getCharacterById(playerCharacterId);
        
        battleDTO.setPlayerChoice(playerCharacter);
        
        if (playerCharacter == null) {
            System.out.println("Personagem não encontrado.");
            return;
        }
            
        System.out.println("O seu oponente sempre será um monstro. Deseja escolher? (s/n)");
        String choice = scanner.next();
        
        Character opponent;
        if (choice.equalsIgnoreCase("s")) {
            listMonsters();
            System.out.print("Escolha o ID do oponente: ");
            Long selectedOpponentId = scanner.nextLong();
            opponent = characterService.getCharacterById(selectedOpponentId);
        } else {
            opponent = characterService.getRandomMonster();
            System.out.println("Personagem escolhido para o oponente:\n" + opponent.getType());
        }
        if (opponent == null) {
            System.out.println("Oponente não encontrado.");
            return;
        }
            
        System.out.println("Agora precisamos decidir nos dados quem terá a iniciativa.");

        scanner.nextLine();
        System.out.println("Pressione Enter para jogar os dados...");
        scanner.nextLine();
        int playerRoll = diceRoller.roll(1, 20);
        System.out.println("Você jogou os dados e obteve: " + playerRoll);

        int machineRoll = diceRoller.roll(1, 20);
        System.out.println("A máquina jogou os dados e obteve: " + machineRoll);

        battle.setDicePlayer(playerRoll);
        battle.setDiceMachine(machineRoll);

        if (playerRoll > machineRoll) {
            System.out.println("Você começa o jogo!");
            battle.setAttacker(playerCharacter);
            battle.setDefender(opponent);
            } else {
            System.out.println("A máquina começa o jogo!");
            battle.setAttacker(opponent);
            battle.setDefender(playerCharacter);
        }

        battle.setPlayer(battleDTO.getPlayer());
        battle.setPlayerChoice(battleDTO.getPlayerChoice());
        battle.setOpponentChoice(battleDTO.getOpponentChoice());        
        battle.setCreatedAt(LocalDateTime.now());

        battleRepository.save(battle);
    }

    public static void main(String[] args) {
        SpringApplication.run(GameRun.class, args);
    }
}

