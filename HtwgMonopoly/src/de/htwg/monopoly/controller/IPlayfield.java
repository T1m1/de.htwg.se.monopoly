/**
 * 
 */
package de.htwg.monopoly.controller;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;
import de.htwg.monopoly.entities.impl.Player;

/**
 *
 *
 */
public interface IPlayfield {
	/**
	 * help function for testing, later it will be replaced by google juice
	 * (maybe)
	 * 
	 * @param size
	 */
	void initialize(int size);

	/**
	 * Move the Player to the new Field according to the result of the dice
	 * roll.
	 * 
	 * @param currentPlayer
	 *            which will be moved
	 * @param diceResult
	 *            : a Number between 2 and 12 modulo the playfield size.
	 * @return true if Player moved over or stays on "Los" otherwise return
	 *         false.
	 */
	void movePlayer(Player currentPlayer, int diceResult);

	/**
	 * Get the current Field where the Player is standing on.
	 * 
	 * @param currentPlayer
	 * @return An Object of Type IFieldObject
	 */
	public IFieldObject getCurrentField(Player currentPlayer);

	String getFieldNameAtIndex(int i);

	int getfieldSize();

	String appendInfo(IFieldObject currentField, Player currentPlayer);

	/**
	 * Moves the current Player to the field according to the String target. If
	 * the player went over or stays on Go, he gets money, but not if he goes in
	 * Prison
	 * 
	 * @param currentPlayer
	 * @param target
	 * @throws AssertionError
	 *             if target doesn't exist
	 */
	String movePlayerTo(Player currentPlayer, String target);

	/**
	 * Returns the Community Cards Stack
	 * 
	 * @return
	 */
	CommunityCardsStack getCommStack();

	/**
	 * Returns the Chance Cards Stack
	 * 
	 * @return
	 */
	ChanceCardsStack getChanStack();

}
