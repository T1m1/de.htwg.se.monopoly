package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.persistence.couchdb.PersistencePlayfield;
import de.htwg.monopoly.persistence.couchdb.PersistencePlayer;
import de.htwg.monopoly.persistence.couchdb.PersistenceGame;

import java.util.ArrayList;
import java.util.List;

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
       
        for(int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
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
       // IMonopolyGame monopolyGame = new MonopolyGame(game.getPlayers(), )
        return null;
    }
}
