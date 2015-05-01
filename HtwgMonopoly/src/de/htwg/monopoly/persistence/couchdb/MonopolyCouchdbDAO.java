package de.htwg.monopoly.persistence.couchdb;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.persistence.IMonopolyDAO;
import de.htwg.monopoly.persistence.util.MonopolyToPersistenceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.List;

/**
 * @author Timi.
 */
public class MonopolyCouchdbDAO implements IMonopolyDAO {

    public static final String HTTP_LENNY2 = "http://lenny2.in.htwg-konstanz.de:5984";
    private final Logger logger = LogManager.getLogger("CouchDb");
    CouchDbConnector db;

    public MonopolyCouchdbDAO() {
        HttpClient client = null;
        try {
            client = new StdHttpClient.Builder().url(HTTP_LENNY2).build();
        } catch (MalformedURLException e) {
            logger.error(e);
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("htwg_monopoly", true);
        db.createDatabaseIfNotExists();
    }

    @Override
    public void saveGame(IMonopolyGame context) {
        /**
         * json object
         * 
         * for each player:
         *      name
         *      in prison
         *      prisonFreeCard
         *      icon
         *      posiotion
         *      budget
         *      ownership
         *      
         * Playfiled:
         *      numberOfFields
         *      current player      
         *      parking money
         *      Game phase      
         * * * * *
         */
        MonopolyToPersistenceUtil util = new MonopolyToPersistenceUtil();
        PersistentGame game =  util.transformToCouchDb(context);
        db.create(game);
    }

    @Override
    public IMonopolyGame getGameById(String id) {
        return null;
    }

    @Override
    public void deleteGameById(String id) {

    }

    @Override
    public void updateGame(IMonopolyGame game) {

    }

    @Override
    public List<IMonopolyGame> getAllGames() {
        return null;
    }

    @Override
    public boolean containsGameById(String id) {
        return false;
    }
}
