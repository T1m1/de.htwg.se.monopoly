/**
 * 
 */
package de.htwg.monopoly.util;

/**
 * These enums define the options, which a player can use in the game.
 * 
 * @author stgorenf
 *
 */
public enum UserAction {
	
	/**
	 * A simple dice roll.
	 */
	ROLL_DICE,
	
	/**
	 * Buying a street or other fields, which can be bought.
	 */
	BUY_STREET,
	
	/**
	 * End the turn of the player
	 */
	END_TURN,
	
	/**
	 * Start the turn of a player, by rolling a dice and moving.
	 */
	START_TURN,
	
	/**
	 * End the game, by surrendering.
	 */
	SURRENDER,
	
	/**
	 * This option is available if the player wants to redeem from prison.
	 */
	REDEEM;

}
