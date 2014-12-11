/**
 * 
 */
package de.htwg.monopoly.information;

import java.util.List;

import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

/**
 * @author Steffen
 *
 */
public interface IStats {

	void update();
	
	/**
	 * return object from current player
	 * 
	 * @return
	 */
	Player getCurrentPlayer();
	
	/**
	 * Return the number of Players.
	 * 
	 * @return
	 */
	int getNumberOfPlayers();
	

	/**
	 * Get the Player at index i in the Player-Queue
	 * 
	 * @param i
	 * @return
	 */
	Player getPlayer(int i);

	
	/**
	 * get dice objects
	 * 
	 * @return
	 */
	Dice getDice();
	
	/**
	 * Get the field, where the current player is standing on.
	 * 
	 * @return
	 */
	IFieldObject getCurrentField();
	
	GameStatus getPhase();
	
	/**
	 * Returns a list with available options for the current player.
	 * 
	 * @return
	 */
	List<UserAction> getOptions();
	
	/**
	 * Returns the size of the playfield. Meaning the number of fields existing
	 * in the game.
	 * 
	 * @return integer greater than 0
	 */
	int getFieldSize();
	
	IFieldObject getFieldAtIndex(int i);

	IPlayfield getField();

}
