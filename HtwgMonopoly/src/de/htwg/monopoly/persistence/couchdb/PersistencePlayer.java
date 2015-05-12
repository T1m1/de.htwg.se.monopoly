package de.htwg.monopoly.persistence.couchdb;

import de.htwg.monopoly.util.PlayerIcon;
import lombok.Getter;
import lombok.Setter;
import org.ektorp.support.CouchDbDocument;

import java.util.Collection;

/**
 * @author Timi.
 */
@SuppressWarnings("PMD.UnusedPrivateField") // FIXME: used?
public class PersistencePlayer extends CouchDbDocument{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8872737973382516790L;
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
    private Collection<Integer> ownershipPositions;
    @Getter
    @Setter
    private Integer prisonFreeCard;
    @Getter
    @Setter
    private PlayerIcon icon;
    
    
}
