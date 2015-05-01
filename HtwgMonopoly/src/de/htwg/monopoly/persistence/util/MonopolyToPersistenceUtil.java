package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.persistence.couchdb.PeristentPlayfield;
import de.htwg.monopoly.persistence.couchdb.PersistenUser;
import de.htwg.monopoly.persistence.couchdb.PersistentGame;

import java.util.Collection;

/**
 * @author Timi.
 */
public class MonopolyToPersistenceUtil {
    
    public PersistentGame transformToCouchDb(IMonopolyGame game) {
        PersistentGame persistentGame= new PersistentGame();

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
        Collection<PersistenUser> persistenUser = null;
        for(int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
            Player player = game.getPlayerController().getPlayer(i);
            PersistenUser user = new PersistenUser();
            user.setBudget(player.getBudget());
            user.setIcon(player.getIcon());
            user.setInPrison(player.getPrisonRound()!=0);
            user.setName(player.getName());
            user.setPrisonRound(player.getPrisonRound());
            user.setPosition(player.getPosition());
            persistenUser.add(user);
        }
        
        
        return persistentGame;
    }
}
