package de.htwg.monopoly.persistence.util;

import de.htwg.monopoly.context.IMonopolyGame;
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
        

        Collection<PersistenUser> persistenUser;
        
        
        return persistentGame;
    }
}
