/**
 * 
 */
package de.htwg.monopoly.database.db4o;

import java.util.List;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.database.IMonopolyDAO;

/**
 * @author Steffen
 *
 */
public class MonopolyDb4oDAO implements IMonopolyDAO {

	/**
	 * 
	 */
	public MonopolyDb4oDAO() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void saveGame(IMonopolyGame context) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public IMonopolyGame getGameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteGameById(String id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateGameById(String id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<IMonopolyGame> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean containsGameById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
