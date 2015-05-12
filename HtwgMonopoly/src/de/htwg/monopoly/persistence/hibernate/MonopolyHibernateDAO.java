/**
 * 
 */
package de.htwg.monopoly.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.persistence.IMonopolyDAO;
import de.htwg.monopoly.persistence.util.HibernateUtil;

/**
 * @author Steffen
 *
 */
public class MonopolyHibernateDAO implements IMonopolyDAO {

	@Override
	public void saveGame(IMonopolyGame context) {
		Transaction tx = null;
		Session session = null;

		try {
			session = HibernateUtil.getInstance().getCurrentSession();
			tx = session.beginTransaction();

			PersistentGame pGame = transformToHibernate(context);

			session.saveOrUpdate(pGame);
			session.saveOrUpdate(pGame.getPlayfield());
			for (PersistentPlayer pPlayer : pGame.getPlayers()) {
				session.saveOrUpdate(pPlayer);
			}

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
		}

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

	private PersistentGame transformToHibernate(IMonopolyGame game) {
		if (game == null) {
			return null;
		}

		PersistentGame pGame;
		PersistentPlayfield pPlayfield;
		List<PersistentPlayer> pPlayerList;

		String id = game.getId();
		// check if game already exist in database and update it
		if (!containsGameById(id)) {

			// game does not exist --> create a new one

			// create new persistence playfield and save static data
			pPlayfield = new PersistentPlayfield();
			pPlayfield.setNumberOfFields(game.getPlayfield().getfieldSize());

			// create new player and save static data
			pPlayerList = new ArrayList<PersistentPlayer>();
			for (int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
				Player player = game.getPlayerController().getPlayer(i);
				PersistentPlayer pPlayer = new PersistentPlayer();

				pPlayer.setIcon(player.getIcon().toString());
				pPlayer.setName(player.getName());

				pPlayerList.add(pPlayer);
			}

			// create new game and save static data
			pGame = new PersistentGame();
			pGame.setId(id);
			pGame.setName(game.getName());
			pGame.setPlayfield(pPlayfield);
			pGame.setPlayers(pPlayerList);

		} else {

			// game already exist --> retrieve data
			Session session = HibernateUtil.getInstance().getCurrentSession();
			pGame = (PersistentGame) session.get(PersistentGame.class, id);
			pPlayfield = pGame.getPlayfield();
			pPlayerList = pGame.getPlayers();

		}

		// update player with dynamic data
		for (int i = 0; i < game.getPlayerController().getNumberOfPlayer(); i++) {
			Player player = game.getPlayerController().getPlayer(i);
			PersistentPlayer pPlayer = pPlayerList.get(i);
			pPlayer.setBudget(player.getBudget());
			pPlayer.setInPrison(player.isInPrison());
			pPlayer.setPrisonRound(player.getPrisonRound());
			pPlayer.setPosition(player.getPosition());
			pPlayer.setPrisonFreeCard(player.getNumberOfPrisonFreeCards());

			List<Integer> fields = new ArrayList<Integer>();
			for (IFieldObject field : player.getOwnership()) {
				fields.add(field.getPosition());
			}
			pPlayer.setOwnershipPositions(fields);
		}

		// update field with dynamic data
		pPlayfield.setCurrentPlayer(game.getPlayerController()
				.getCurrentPlayer().getName());

		// TODO: synchronize values:
		// - prisonquestion
		// - the dice

		// update game with dynamic data
		pGame.setDrawnCardFlag(game.getDrawCardFlag());
		pGame.setDiceFlag(game.getDiceFlag());
		pGame.setMessage(game.getMessage());
		pGame.setParkingMoney(game.getParkingMoney());
		pGame.setPhase(game.getCurrentGamePhase().toString());

		return pGame;
	}
	
	

}
