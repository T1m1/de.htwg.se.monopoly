package de.htwg.monopoly.controller;

import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Player;

public interface IPlayerController {

	/**
	 * Return the next Player in the Player-Queue. This Player will be moved at
	 * the end of the Queue.
	 * 
	 * @return player
	 */
	Player getNextPlayer();

	/**
	 * Reads a number from the Standard input and checks if correct. 
	 * Return true if number is correct, otherwise false.
	 * 
	 * @return
	 */
	boolean readNumberOfPlayer();

	/**
	 * Return the number of Player.
	 *  
	 * @return
	 */
	int getNumberOfPlayer();

	/**
	 * Reads the name of each Player from the Standard input.
	 * Return true if names are correct, otherwise false.
	 * 
	 * @param i
	 * @return
	 */
	boolean readNameOfPlayer(int i);

	/**
	 * returns a player object at index i in the Queue
	 * 
	 * @param i
	 * @return
	 */
	Player getPlayer(int i);

	/**
	 * Performs money transfer according to the drawn card. There are two types:
	 * Money is transferred to/from bank or all the other players.
	 * 
	 * @param currentCard
	 * @param currentPlayer
	 */
	void transferMoney(Player currentPlayer, ICards currentCard);

	/**
	 * Returns the current Player
	 * 
	 * @return
	 */
	Player currentPlayer();
}
