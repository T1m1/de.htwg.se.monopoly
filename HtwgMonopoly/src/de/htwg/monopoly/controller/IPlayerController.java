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
	 * Return the number of Player.
	 * 
	 * @return
	 */
	int getNumberOfPlayer();


	/**
	 * returns a player object at index i in the Queue
	 * 
	 * @param i
	 * @return
	 */
	Player getPlayer(int i);

	/**
	 * Performs money transfer according to the drawn card. There are two types:
	 * Money is transferred to/from bank or to/from all the other players.
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
	Player getCurrentPlayer();

	/**
	 * Returns the first player in the queue. Basically players[0]
	 * 
	 * @return the first player.
	 */
	Player getFirstPlayer();

	/**
	 * @return the currentPlayerIndex
	 */
	int getCurrentPlayerIndex();

}
