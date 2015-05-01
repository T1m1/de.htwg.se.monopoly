package de.htwg.monopoly.persistence.couchdb;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.PlayerIcon;
import org.ektorp.support.CouchDbDocument;

import java.util.List;

/**
 * @author Timi.
 */
public class PersistenUser extends CouchDbDocument{
    
    private String name;
    private int budget;
    private int position;
    private int prisonRound;
    private boolean inPrison;
    private List<IFieldObject> ownership;
    private int prisonFreeCard;
    private PlayerIcon icon;
    
}
