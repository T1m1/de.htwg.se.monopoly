package de.htwg.monopoly.persistence.couchdb;

import org.ektorp.support.CouchDbDocument;

/**
 * @author Timi.
 */
public class PeristentPlayfield  extends CouchDbDocument{
    private Integer numberOfFields;
    private Integer currentPlayer;
    private Integer parkingMoney;
    private String GamePhase;
}
