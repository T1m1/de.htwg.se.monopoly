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
@SuppressWarnings("PMD.UnusedPrivateField")
public class PersistenceGame extends CouchDbDocument {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8167905109800696178L;

	@Getter
    @Setter
    private String name;
    
    @Getter
    @Setter
    private Collection<PersistencePlayer> players;

    @Getter
    @Setter
    private Integer currentPlayerIndex;

    @Getter
    @Setter
    private PersistencePlayfield playfield;
    
    @Getter
    @Setter
    private String message;
    
    @Getter
    @Setter
    private boolean drawnCardFlag;
    
    @Getter
    @Setter
    private int diceFlag;
}
