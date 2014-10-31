/**
 * 
 */
package de.htwg.monopoly.util;

/**
 * These enums represents the different phases of the game.
 * 
 * @author stgorenf
 *
 */
public enum GameStatus {
	
	/**
	 * The game is started.
	 */
	STARTED,
	
	/**
	 * The player is about to begin his turn (e.g. roll the dice and start his turn).
	 */
	BEFORE_TURN,
	
	/**
	 * The player is during his turn and is able to perform his moves (e.g. buy a street).
	 */
	DURING_TURN,
	
	/**
	 * The player has ended his turn.
	 */
	USER_AFTER,
	
	/**
	 * The game has ended or is not started yet.
	 */
	STOPPED;

}
