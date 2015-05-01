package de.htwg.monopoly.persistence.couchdb;

import lombok.Getter;
import lombok.Setter;
import org.ektorp.support.CouchDbDocument;

import java.util.Collection;

/**
 * @author Timi,
 */
public class PersistenceGame extends CouchDbDocument {

    @Getter
    @Setter
    Collection<PersistenUser> user;
    
    @Getter
    @Setter
    PeristentPlayfield playfield;
}
