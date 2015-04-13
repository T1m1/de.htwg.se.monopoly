/**
 * 
 */
package de.htwg.monopoly.database;

import de.htwg.monopoly.context.IMonopolyGame;

/**
 * @author Steffen
 *
 */
public interface IMonopolyDAO {
	
	void saveGame(final IMonopolyGame context);
	
	IMonopolyGame getGameById(final String id);
		
	void deleteGameById(final String id);
	
	void updateGameById(final String id);

}
