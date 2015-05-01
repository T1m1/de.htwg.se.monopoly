package de.htwg.monopoly.persistence.couchdb;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.PlayerIcon;
import lombok.Getter;
import lombok.Setter;
import org.ektorp.support.CouchDbDocument;

import java.util.List;

/**
 * @author Timi.
 */

public class PersistencePlayer extends CouchDbDocument{
    
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer budget;
    @Getter
    @Setter
    private Integer position;
    @Getter
    @Setter
    private Integer prisonRound;
    @Getter
    @Setter
    private Boolean inPrison;
    @Getter
    @Setter
    // Save only position of ownerships
    private List<Integer> ownershipPositions;
    @Getter
    @Setter
    private Integer prisonFreeCard;
    @Getter
    @Setter
    private PlayerIcon icon;
    
    
}
