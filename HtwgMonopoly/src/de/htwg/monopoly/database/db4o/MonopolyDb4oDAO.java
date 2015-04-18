/**
 * 
 */
package de.htwg.monopoly.database.db4o;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.EmbeddedObjectContainer;
import com.db4o.query.Predicate;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.database.IMonopolyDAO;

/**
 * @author Steffen
 *
 */
public class MonopolyDb4oDAO implements IMonopolyDAO {

	private EmbeddedObjectContainer db;

	/**
	 * 
	 */
	public MonopolyDb4oDAO() {
		db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),
				"monopoly.data");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveGame(IMonopolyGame context) {
		db.store(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IMonopolyGame getGameById(final String id) {
		List<IMonopolyGame> games = db.query(new Predicate<IMonopolyGame>() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean match(IMonopolyGame game) {
				return (game.getId().equals(id));
			}
		});

		if (games.isEmpty()) {
			// no game found
			return null;
		}

		// return the retrieved game.
		return games.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteGameById(String id) {
		db.delete(getGameById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IMonopolyGame> getAllGames() {
		return db.query(IMonopolyGame.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsGameById(String id) {
		IMonopolyGame game = getGameById(id);
		return (game != null);
	}
	
	public void closeDB() {
		db.close();
	}

	@Override
	public void updateGame(IMonopolyGame game) {
		// naive code FIXME: better solution?
		deleteGameById(game.getId());
		saveGame(game);
		
	}

}
