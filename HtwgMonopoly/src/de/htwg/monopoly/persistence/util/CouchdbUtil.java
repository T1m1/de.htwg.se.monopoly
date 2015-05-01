package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.context.impl.MonopolyGame;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.controller.impl.Playfield;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.persistence.couchdb.PersistencePlayfield;
import de.htwg.monopoly.persistence.couchdb.PersistencePlayer;
import de.htwg.monopoly.persistence.couchdb.PersistenceGame;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.PlayerIcon;
import javafx.beans.binding.MapBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Timi.
 */
public class CouchdbUtil {

    public PersistenceGame transformToCouchDb(IMonopolyGame game) {
        PersistenceGame persistenceGame = new PersistenceGame();

        // Playfield
        // TODO:
        // - id
        // - currentPlayer (int or name)
        PersistencePlayfield persistencePlayfield = new PersistencePlayfield();
        persistencePlayfield.setNumberOfFields(game.getPlayfield().getfieldSize());
        persistencePlayfield.setGamePhase(game.getCurrentGamePhase().toString());
        persistencePlayfield.setParkingMoney(game.getParkingMoney());


        // Player
        // TODO:
        // - prison free card
        // - ownership
        List<PersistencePlayer> persistencePlayer = new ArrayList<PersistencePlayer>();

        for (int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
            Player player = game.getPlayerController().getPlayer(i);
            PersistencePlayer user = new PersistencePlayer();
            user.setBudget(player.getBudget());
            user.setIcon(player.getIcon());
            user.setInPrison(player.getPrisonRound() != 0);
            user.setName(player.getName());
            user.setPrisonRound(player.getPrisonRound());
            user.setPosition(player.getPosition());
            persistencePlayer.add(user);
        }

        persistenceGame.setPlayfield(persistencePlayfield);
        persistenceGame.setPlayers(persistencePlayer);

        return persistenceGame;
    }

    public IMonopolyGame transformFromCouchDb(PersistenceGame game) {

        // player controller
        Map<String, PlayerIcon> players = new HashMap<String, PlayerIcon>();
        for (PersistencePlayer player : game.getPlayers()) {
            players.put(player.getName(), player.getIcon());
        }
        IPlayerController playerController = new PlayerController(players);

        // playfield
        IPlayfield playfield = new Playfield(game.getPlayfield().getNumberOfFields());

        // TODO: 
        // - prison questions
        // -last message
        // -diceFlag
        // -diceCardFlag
        // -dice
        PrisonQuestion question = new PrisonQuestion();
        Dice dice = new Dice();

        // playfield.
        IMonopolyGame monopolyGame = new MonopolyGame(playerController, playfield, question,
                GameStatus.valueOf(game.getPlayfield().getGamePhase()), game.getId(),
                game.getPlayfield().getParkingMoney(), null, 0, false, dice);

        return monopolyGame;
    }
}
