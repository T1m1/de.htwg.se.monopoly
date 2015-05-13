package de.htwg.monopoly.persistence.couchdb;

import lombok.Getter;
import lombok.Setter;
import org.ektorp.support.CouchDbDocument;

/**
 * @author Timi.
 */
@SuppressWarnings("PMD.UnusedPrivateField")
public class PersistencePlayfield extends CouchDbDocument{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -2907621521937300350L;
	@Getter
    @Setter
    private Integer numberOfFields;
    @Getter
    @Setter
    private String currentPlayer;
    @Getter
    @Setter
    private Integer parkingMoney;
    @Getter
    @Setter
    private String gamePhase;
}
