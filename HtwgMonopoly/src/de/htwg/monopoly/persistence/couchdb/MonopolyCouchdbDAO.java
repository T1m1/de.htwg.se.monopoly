package de.htwg.monopoly.persistence.couchdb;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.persistence.IMonopolyDAO;
import de.htwg.monopoly.persistence.util.CouchdbUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DocumentNotFoundException;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timi.
 */
public class MonopolyCouchdbDAO implements IMonopolyDAO {

    private static final String HTTP_LENNY2 = "http://lenny2.in.htwg-konstanz.de:5984";
    private final Logger logger = LogManager.getLogger("CouchDb");
    private CouchDbConnector db;
    private CouchdbUtil util;


    public MonopolyCouchdbDAO() {
        util = new CouchdbUtil();
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
        PersistenceGame game = util.transformToCouchDb(context);
        db.create(game);
    }

    @Override
    public IMonopolyGame getGameById(String id) {
        try {
            PersistenceGame game = db.get(PersistenceGame.class, id);
            return util.transformFromCouchDb(game);
        } catch (DocumentNotFoundException e) {
            logger.error(e);
            return null;
        }
    }

    @Override
    public void deleteGameById(String id) {
        db.delete(util.transformToCouchDb(getGameById(id)));
    }

    @Override
    public void updateGame(IMonopolyGame game) {
        saveGame(game);
    }

    @Override
    public List<IMonopolyGame> getAllGames() {
        ViewQuery query = new ViewQuery().allDocs().includeDocs(true);

        List<IMonopolyGame> monopolyGames = new ArrayList<IMonopolyGame>();
        for (PersistenceGame pChessGame : db.queryView(query, PersistenceGame.class)) {
            monopolyGames.add(util.transformFromCouchDb(pChessGame));
        }

        return monopolyGames;
    }

    @Override
    public boolean containsGameById(String id) {
        return null != getGameById(id);
    }
}
