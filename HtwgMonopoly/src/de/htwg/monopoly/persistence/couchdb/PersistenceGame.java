package de.htwg.monopoly.persistence.couchdb;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.ektorp.support.CouchDbDocument;

import java.util.Collection;

/**
 * @author Timi,
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistenceGame extends CouchDbDocument {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8167905109800696178L;

	@Getter
    @Setter
    String name;
    
    @Getter
    @Setter
    Collection<PersistencePlayer> players;

    @Getter
    @Setter
    Integer currentPlayerIndex;

    @Getter
    @Setter
    PersistencePlayfield playfield;
    
    @Getter
    @Setter
    String message;
    
    @Getter
    @Setter
    boolean drawnCardFlag;
    
    @Getter
    @Setter
    int diceFlag;
}
