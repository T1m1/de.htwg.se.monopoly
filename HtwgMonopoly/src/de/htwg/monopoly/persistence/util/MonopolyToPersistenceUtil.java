package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.persistence.couchdb.PeristentPlayfield;
import de.htwg.monopoly.persistence.couchdb.PersistenUser;
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
        PeristentPlayfield peristentPlayfield = new PeristentPlayfield();
        peristentPlayfield.setNumberOfFields(game.getPlayfield().getfieldSize());
        peristentPlayfield.setGamePhase(game.getCurrentGamePhase().toString());
        peristentPlayfield.setParkingMoney(game.getParkingMoney());
        

        // Player
        // TODO:
        // - prison free card
        // - ownership
        List<PersistenUser> persistenceUser = new ArrayList<PersistenUser>();
       
        for(int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
            Player player = game.getPlayerController().getPlayer(i);
            PersistenUser user = new PersistenUser();
            user.setBudget(player.getBudget());
            user.setIcon(player.getIcon());
            user.setInPrison(player.getPrisonRound() != 0);
            user.setName(player.getName());
            user.setPrisonRound(player.getPrisonRound());
            user.setPosition(player.getPosition());
            persistenceUser.add(user);
        }
        
        persistenceGame.setPlayfield(peristentPlayfield);
        persistenceGame.setUser(persistenceUser);
        
        return persistenceGame;
    }
}
