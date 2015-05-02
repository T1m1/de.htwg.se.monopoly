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
import de.htwg.monopoly.util.IMonopolyUtil;

import java.util.ArrayList;
import java.util.List;

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

        ArrayList<Player> players = new ArrayList<Player>();

        Playfield playfield = new Playfield(game.getPlayfield().getNumberOfFields());

        int count = 0;
        for (PersistencePlayer persistencePlayer : game.getPlayers()) {
            Player playerFromDatabase = new Player(persistencePlayer.getName(), persistencePlayer.getIcon());
            playerFromDatabase.setInPrison(persistencePlayer.getInPrison());
            playerFromDatabase.setPosition(persistencePlayer.getPosition());
            // set default money to 0
            playerFromDatabase.decrementMoney(IMonopolyUtil.INITIAL_MONEY);
            playerFromDatabase.incrementMoney(persistencePlayer.getBudget());
            if(persistencePlayer.getPrisonFreeCard() > 0) {
                playerFromDatabase.incrementPrisonFreeCard();
            }

            for (Integer index : persistencePlayer.getOwnershipPositions()) {
                playerFromDatabase.addOwnership(playfield.getFieldAtIndex(index));
            }

            players.add(count, playerFromDatabase);
        }

        IPlayerController playerController = new PlayerController(players, players.size(), game.getCurrentPlayerIndex());

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
