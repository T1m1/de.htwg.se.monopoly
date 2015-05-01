package de.htwg.monopoly.persistence.couchdb;

import lombok.Getter;
import lombok.Setter;
import org.ektorp.support.CouchDbDocument;

/**
 * @author Timi.
 */
public class PeristentPlayfield  extends CouchDbDocument{
    
    @Getter
    @Setter
    private Integer numberOfFields;
    @Getter
    @Setter
    private Integer currentPlayer;
    @Getter
    @Setter
    private Integer parkingMoney;
    @Getter
    @Setter
    private String GamePhase;
}
