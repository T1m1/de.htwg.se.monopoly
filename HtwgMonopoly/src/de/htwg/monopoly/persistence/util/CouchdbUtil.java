package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.context.impl.MonopolyGame;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.controller.impl.Playfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.persistence.couchdb.PersistencePlayfield;
import de.htwg.monopoly.persistence.couchdb.PersistencePlayer;
import de.htwg.monopoly.persistence.couchdb.PersistenceGame;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.PlayerIcon;

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
        PersistencePlayfield persistencePlayfield = new PersistencePlayfield();
        persistencePlayfield.setNumberOfFields(game.getPlayfield().getfieldSize());
        persistencePlayfield.setGamePhase(game.getCurrentGamePhase().toString());
        persistencePlayfield.setParkingMoney(game.getParkingMoney());
        persistencePlayfield.setCurrentPlayer(game.getPlayerController().getCurrentPlayer().getName());
        if(!game.getId().isEmpty()) {
            persistencePlayfield.setId(game.getId());
        }

        // Player
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
            user.setPrisonFreeCard(player.hasPrisonFreeCard() ? 1 : 0);
            List<Integer> fields = new ArrayList<Integer>();
            for (IFieldObject field : player.getOwnership()) {
                fields.add(field.getPosition());
            }
            user.setOwnershipPositions(fields);

            persistencePlayer.add(user);
        }

        persistenceGame.setPlayfield(persistencePlayfield);
        persistenceGame.setPlayers(persistencePlayer);

        return persistenceGame;
    }

    public IMonopolyGame transformFromCouchDb(PersistenceGame game) {

        // player controller
        Map<String, PlayerIcon> playersDb = new HashMap<String, PlayerIcon>();
        ArrayList<Player> tmpPlayer = new ArrayList<Player>();

        Playfield playfield = new Playfield(game.getPlayfield().getNumberOfFields());

        int count = 0;
        for (PersistencePlayer player : game.getPlayers()) {
            tmpPlayer.get(count).setPosition(player.getPosition());
            tmpPlayer.get(count).setInPrison(player.getInPrison());
            for (Integer index : player.getOwnershipPositions()) {
                tmpPlayer.get(count).addOwnership(playfield.getFieldAtIndex(index));
            }

            playersDb.put(player.getName(), player.getIcon());

        }

        IPlayerController playerController = new PlayerController(playersDb);

        for (int i = 0; i < playerController.getNumberOfPlayer(); i++) {
            playerController.getPlayer(i).setPosition(tmpPlayer.get(i).getPosition());
            playerController.getPlayer(i).setInPrison(tmpPlayer.get(i).getPrisonRound() != 0);
            for(IFieldObject field : tmpPlayer.get(i).getOwnership()){
                playerController.getPlayer(i).addOwnership(field);
            }
        }

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
                game.getPlayfield().getParkingMoney(), game.getId(), 0, false, dice);


        return monopolyGame;
    }
}
