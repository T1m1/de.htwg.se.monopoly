/**
 * 
 */
package de.htwg.monopoly.controller;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;

/**
 *
 *
 */
public interface IPlayfield {

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
	IFieldObject getCurrentField(Player currentPlayer);

	/**
	 * Return the name of the Field at index i *
	 * 
	 * @param i
	 * @return String
	 */
	String getFieldNameAtIndex(int i);

	
	/**
	 * Return the current Field at index i *
	 * 
	 * @param i
	 * @return String
	 */
	IFieldObject getFieldAtIndex(int i);
	
	/**
	 * Return the size of the playfield.
	 * 
	 * @return int
	 */
	int getfieldSize();

	/**
	 * Build and return a String, depending on the field, the current player is
	 * standing on. In addition, this method performs this action.
	 * 
	 * 
	 * @param currentField
	 * @param currentPlayer
	 * @return
	 */
	String performActionAndAppendInfo(IFieldObject currentField, Player currentPlayer);

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
	
	/**
	 * for testing
	 * 
	 * 
	 * @param i
	 * @param field
	 */
	void setFieldAtIndex(int i, IFieldObject field);
	
	/**
	 * The current Player buys this current Street. Returns false if the Player
	 * has not enough money. Otherwise true if the purchase was successful.
	 * 
	 * 
	 * @param currentPlayer
	 * @param currentStreet
	 * @return
	 */
	boolean buyStreet(Player currentPlayer, Street currentStreet);

    IFieldObject[] getPlayfield();
}
