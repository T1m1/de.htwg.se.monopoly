package de.htwg.monopoly.database.couchdb;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.database.IMonopolyDAO;

import java.util.List;

/**
 * Created by Timi on 24.04.2015.
 */
public class MonopolyCouchdbDAO implements IMonopolyDAO {

    public MonopolyCouchdbDAO() {
        
    }

    @Override
    public void saveGame(IMonopolyGame context) {

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
