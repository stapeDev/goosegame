package it.stapedev.service;

import java.util.ArrayList;
import java.util.List;

import it.stapedev.data.Dice;
import it.stapedev.data.GameStatus;
import it.stapedev.data.Player;
import it.stapedev.enums.EGosseGamePanel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GooseLogics {

    private static final Integer gooseGameMaxNumber = 63;

    private List<Player> players = new ArrayList<>(0);

    /**
     * This method verify is the panel is special
     * 
     * @param panel - panel's number
     * @return true if is a special number otherwise not
     */
    public Boolean isSpecialPanel(Integer panel) {
        switch (panel) {
        case 5:
        case 6:
        case 9:
        case 14:
        case 23:
        case 27:
        case 32:
        case 36:
        case 41:
        case 45:
        case 50:
        case 54:
        case 59:
        case 63:
            return true;
        default:
            return false;
        }
    }

    /**
     * This method get the type of the panel
     * 
     * @param panel - panel's number
     * @return the kind of panel
     */
    public EGosseGamePanel getPanel(Integer panel) {
        switch (panel) {
        case 5:
        case 9:
        case 14:
        case 23:
        case 27:
        case 32:
        case 36:
        case 41:
        case 45:
        case 50:
        case 54:
        case 59:
        case 63:
            return EGosseGamePanel.GOOSE;
        case 6:
            return EGosseGamePanel.BRIDGE;
        default:
            return EGosseGamePanel.NORMAL;
        }
    }

    public Dice lauchDice(Player player) {
        if (isAPlayerWonTheGame()) {
            throw new RuntimeException("Mossa non consentita, un giocatore ha già vinto");
        }
        Dice currentRoll = new Dice();
        log.info("{} tira {}, {}.", player.getName(), currentRoll.getFirst(), currentRoll.getSecond());
        return currentRoll;
    }

    public Dice lauchDice(Player player, Integer first, Integer second) {
        if (isAPlayerWonTheGame()) {
            throw new RuntimeException("Mossa non consentita, un giocatore ha già vinto");
        }
        Dice currentRoll = new Dice(first, second);
        log.info("{} tira {}, {}.", player.getName(), currentRoll.getFirst(), currentRoll.getSecond());
        return currentRoll;
    }

    /**
     * This method elaborate and move the player from its current position to newest
     * 
     * @param player     - player needs to move
     * @param diceNumber - how many panel the player needs to move
     */
    public void move(Player player, Dice currentRoll) {
        if (isAPlayerWonTheGame()) {
            throw new RuntimeException("Mossa non consentita, un giocatore ha già vinto");
        }
        Integer current = player.getGameStatus().getCurrent();
        Integer newCurrent = calculateMove(current, currentRoll.getFirst() + currentRoll.getSecond());
        player.getGameStatus().newPosition(newCurrent);
        log.info("{} muove da {} a {}.", player.getName(), player.getGameStatus().getPrevious(),
                player.getGameStatus().getCurrent());
        if (player.getGameStatus().getCurrent().equals(gooseGameMaxNumber)) {
            log.info("{} ha vinto", player.getName());
        } else {
            if (isSpecialPanel(newCurrent)) {
                switch (getPanel(newCurrent)) {
                case BRIDGE:
                    log.info("Ponte, {} salta a 12", player.getName());
                    player.getGameStatus().newPosition(12);
                    break;
                case GOOSE:
                    log.info("Oca, {} muove di nuovo", player.getName());
                    player.getGameStatus()
                            .newPosition(calculateMove(newCurrent, currentRoll.getFirst() + currentRoll.getSecond()));
                    log.info("{} muove da {} a {}.", player.getName(), player.getGameStatus().getPrevious(),
                            player.getGameStatus().getCurrent());
                    break;
                }
            }
            if (isAnotherPlayerIntoPanel(newCurrent)) {
                log.info(
                        "Scherzetto, è stato trovato un altro giocatore nella stessa casella, {} torna al punto precedente",
                        player.getName());
                player.getGameStatus().undoPosition();
            }
        }
    }

    private Integer calculateMove(Integer current, Integer howManyPanelsToJump) {
        Integer newCurrent = current + howManyPanelsToJump;
        if (newCurrent > gooseGameMaxNumber) {
            newCurrent = newCurrent - (newCurrent - gooseGameMaxNumber);
        }
        return newCurrent;
    }

    /**
     * This method verify if in a panel there many players
     * 
     * @param panel - panel's number
     * @return true if there are more than one player into the panel otherwise false
     */
    private Boolean isAnotherPlayerIntoPanel(Integer panel) {
        return this.players.stream().filter(player -> player.getGameStatus().getCurrent().equals(panel)).count() > 0;
    }

    private Boolean isAPlayerWonTheGame() {
        return this.players.stream().filter(player -> player.getGameStatus().getCurrent().equals(gooseGameMaxNumber))
                .count() > 0;
    }

    /**
     * This method add a player into the game
     * 
     * @param name - player's name
     */
    public Player addPlayer(String name) {
        if (players.stream().filter(player -> player.getName().equals(name)).count() > 0) {
            throw new RuntimeException("Giocatore già esistente");
        }
        if (players.stream().filter(player -> player.getGameStatus().getCurrent() > 1).count() > 0) {
            throw new RuntimeException("Partita già cominciata");
        }
        Player player = Player.builder()//
                .name(name)//
                .gameStatus(new GameStatus())//
                .build();
        this.players.add(player);
        log.info("Aggiunto giocatore {}", name);
        return player;
    }
}
