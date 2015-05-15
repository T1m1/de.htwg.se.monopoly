/**
 * 
 */
package de.htwg.monopoly.persistence.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.context.impl.MonopolyGame;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.controller.impl.Playfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.persistence.IMonopolyDAO;
import de.htwg.monopoly.persistence.util.HibernateUtil;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * @author Steffen
 *
 */
public class MonopolyHibernateDAO implements IMonopolyDAO {

	private Session session;

	public MonopolyHibernateDAO() {
		this.session = HibernateUtil.getInstance().openSession();
	}

	@Override
	public void saveGame(IMonopolyGame context) {
		Transaction tx = null;

		try {
			tx = this.session.beginTransaction();

			PersistentGame pGame = transformToHibernate(context);

			this.session.saveOrUpdate(pGame);
			for (PersistentPlayer pPlayer : pGame.getPlayers()) {
				this.session.saveOrUpdate(pPlayer);
			}

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
		}

	}

	@Override
	public IMonopolyGame getGameById(String id) {
		this.session.beginTransaction();
		return transformFromHibernate((PersistentGame) this.session.get(
				PersistentGame.class, id));

		// TODO: error handling
	}

	@Override
	public void deleteGameById(String id) {
		Transaction tx = null;

		try {
			tx = this.session.beginTransaction();

			PersistentGame pGame = (PersistentGame) this.session.get(
					PersistentGame.class, id);
			for (PersistentPlayer c : pGame.getPlayers()) {
				this.session.delete(c);
			}
			this.session.delete(pGame);

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
		}

	}

	@Override
	public void updateGame(IMonopolyGame game) {
		saveGame(game);
	}

	@Override
	public List<IMonopolyGame> getAllGames() {
		this.session.beginTransaction();

		Criteria criteria = this.session.createCriteria(PersistentGame.class);

		@SuppressWarnings("unchecked")
		List<PersistentGame> results = criteria.list();

		List<IMonopolyGame> games = new ArrayList<IMonopolyGame>();
		for (PersistentGame current : results) {
			IMonopolyGame game = transformFromHibernate(current);
			games.add(game);
		}

		return games;
	}

	@Override
	public boolean containsGameById(String id) {
		return getGameById(id) != null;
	}

	private PersistentGame transformToHibernate(IMonopolyGame game) {
		if (game == null) {
			return null;
		}

		PersistentGame pGame;
		List<PersistentPlayer> pPlayerList;

		String id = game.getId();
		// check if game already exist in database and update it
		if (!containsGameById(id)) {

			// game does not exist --> create a new one

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
			pGame.setPlayers(pPlayerList);
			pGame.setNumberOfFields(game.getPlayfield().getfieldSize());

		} else {

			// game already exist --> retrieve data
			Session session = HibernateUtil.getInstance().getCurrentSession();
			pGame = (PersistentGame) session.get(PersistentGame.class, id);
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

			Integer[] fields = new Integer[player.getOwnership().size()];
			int current = 0;
			for (IFieldObject field : player.getOwnership()) {
				fields[current] = field.getPosition();
				current++;
			}
			pPlayer.setOwnershipPositions(fields);
		}

		// TODO: synchronize values:
		// - prisonquestion
		// - the dice

		// update game with dynamic data
		pGame.setDrawnCardFlag(game.getDrawCardFlag());
		pGame.setDiceFlag(game.getDiceFlag());
		pGame.setMessage(game.getMessage());
		pGame.setParkingMoney(game.getParkingMoney());
		pGame.setPhase(game.getCurrentGamePhase().toString());
		pGame.setCurrentPlayerIndex(game.getPlayerController()
				.getCurrentPlayerIndex());

		return pGame;
	}

	private IMonopolyGame transformFromHibernate(PersistentGame game) {
		if (game == null) {
			return null;
		}

		List<Player> players = new ArrayList<Player>();
		Playfield playfield = new Playfield(game.getNumberOfFields());

		int count = 0;

		for (PersistentPlayer pPlayer : game.getPlayers()) {
			Player player = new Player(pPlayer.getName(),
					PlayerIcon.valueOf(pPlayer.getIcon()));

			player.setInPrison(pPlayer.getInPrison());
			player.setPosition(pPlayer.getPosition());

			// set budget to 0 and then increment the value from database
			player.decrementMoney(IMonopolyUtil.INITIAL_MONEY);
			player.incrementMoney(pPlayer.getBudget());

			for (int i = 0; i < pPlayer.getPrisonFreeCard(); i++) {
				player.incrementPrisonFreeCard();
			}

			for (Integer index : pPlayer.getOwnershipPositions()) {
				player.addOwnership(playfield.getFieldAtIndex(index));
			}

			players.add(count, player);
			count++;
		}

		IPlayerController playerController = new PlayerController(players,
				players.size(), game.getCurrentPlayerIndex());

		// TODO: these two values?
		PrisonQuestion question = new PrisonQuestion();
		Dice dice = new Dice();

		return new MonopolyGame(playerController, playfield, question,
				GameStatus.valueOf(game.getPhase()), game.getName(),
				game.getParkingMoney(), game.getMessage(), game.getDiceFlag(),
				game.isDrawnCardFlag(), dice, game.getId(), null);
	}
}
