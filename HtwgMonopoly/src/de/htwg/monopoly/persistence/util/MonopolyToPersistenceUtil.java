package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.persistence.couchdb.PeristencePlayfield;
import de.htwg.monopoly.persistence.couchdb.PersistenceUser;
import de.htwg.monopoly.persistence.couchdb.PersistenceGame;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Timi.
 */
public class MonopolyToPersistenceUtil {
    
    public PersistenceGame transformToCouchDb(IMonopolyGame game) {
        PersistenceGame persistenceGame = new PersistenceGame();

        // Playfield
        // TODO:
        // - id
        // - currentPlayer (int or name)
        PeristencePlayfield peristencePlayfield = new PeristencePlayfield();
        peristencePlayfield.setNumberOfFields(game.getPlayfield().getfieldSize());
        peristencePlayfield.setGamePhase(game.getCurrentGamePhase().toString());
        peristencePlayfield.setParkingMoney(game.getParkingMoney());
        

        // Player
        // TODO:
        // - prison free card
        // - ownership
        List<PersistenceUser> persistenceUser = new ArrayList<PersistenceUser>();
       
        for(int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
            Player player = game.getPlayerController().getPlayer(i);
            PersistenceUser user = new PersistenceUser();
            user.setBudget(player.getBudget());
            user.setIcon(player.getIcon());
            user.setInPrison(player.getPrisonRound() != 0);
            user.setName(player.getName());
            user.setPrisonRound(player.getPrisonRound());
            user.setPosition(player.getPosition());
            persistenceUser.add(user);
        }
        
        persistenceGame.setPlayfield(peristencePlayfield);
        persistenceGame.setUser(persistenceUser);
        
        return persistenceGame;
    }
}
