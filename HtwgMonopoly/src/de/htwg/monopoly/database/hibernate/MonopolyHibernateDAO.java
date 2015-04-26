/**
 * 
 */
package de.htwg.monopoly.database.hibernate;

import java.util.List;

import org.hibernate.classic.Session;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.context.impl.MonopolyGame;
import de.htwg.monopoly.database.IMonopolyDAO;

/**
 * @author Steffen
 *
 */
public class MonopolyHibernateDAO implements IMonopolyDAO {

	private PersistentGame copyGame(MonopolyGame game) {
		if (game == null) {
			return null;
		}

		String id = game.getId();
		PersistentGame pGame;

		// check if game already exist in database and update it
		if (containsGameById(id)) {
			
			Session session = HibernateUtil.getInstance().getCurrentSession();
			pGame = (PersistentGame) session.get(PersistentGame.class, id);

			
			// TODO: synchronize values
		} else {
			// create new database entry
			pGame = new PersistentGame();
			
			game.getCurrentGamePhase();
			game.getDice();
			game.getPlayerController();
			game.getPlayfield();
			game.getPrisonQuestions();
			
		
			
		}

		
		// set simple values
		pGame.setId(game.getId());
		pGame.setDrawnCardFlag(game.getDrawCardFlag());
		pGame.setDiceFlag(game.getDiceFlag());
		pGame.setMessage(game.getMessage());
		pGame.setParkingMoney(game.getParkingMoney());
		pGame.setName(game.getName());
		
		return pGame;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.monopoly.database.IMonopolyDAO#saveGame(de.htwg.monopoly.context
	 * .IMonopolyGame)
	 */
	@Override
	public void saveGame(IMonopolyGame context) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.monopoly.database.IMonopolyDAO#getGameById(java.lang.String)
	 */
	@Override
	public IMonopolyGame getGameById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.monopoly.database.IMonopolyDAO#deleteGameById(java.lang.String)
	 */
	@Override
	public void deleteGameById(String id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.monopoly.database.IMonopolyDAO#updateGame(de.htwg.monopoly.context
	 * .IMonopolyGame)
	 */
	@Override
	public void updateGame(IMonopolyGame game) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.htwg.monopoly.database.IMonopolyDAO#getAllGames()
	 */
	@Override
	public List<IMonopolyGame> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.htwg.monopoly.database.IMonopolyDAO#containsGameById(java.lang.String)
	 */
	@Override
	public boolean containsGameById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
