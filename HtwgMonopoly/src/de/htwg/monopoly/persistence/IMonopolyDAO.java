/**
 * 
 */
package de.htwg.monopoly.persistence;

import java.util.List;

import de.htwg.monopoly.context.IMonopolyGame;

/**
 * @author Steffen
 *
 */
public interface IMonopolyDAO {

	/**
	 * Saves the context of a monopoly game into a database.
	 * 
	 * @param context
	 */
	void saveGame(final IMonopolyGame context);

	/**
	 * Retrieves a game of monopoly for the corresponding ID.
	 * 
	 * @param id
	 * @return an instance of a saved game or <code>null</code> if there was no
	 *         game in the database
	 */
	IMonopolyGame getGameById(final String id);

	/**
	 * Deletes a game of monopoly for the corresponding ID.
	 * 
	 * @param id
	 */
	void deleteGameById(final String id);

	/**
	 * Updates a game with new values
	 * 
	 * @param id
	 */
	void updateGame(final IMonopolyGame game);

	/**
	 * Get all existing games from the database.
	 * 
	 * @return
	 */
	List<IMonopolyGame> getAllGames();

	/**
	 * Checks if there is a game for the corresponding ID.
	 * 
	 * @param id
	 * @return true if a game to the ID is present in the database
	 */
	boolean containsGameById(final String id);
}
