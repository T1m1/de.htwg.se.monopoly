package de.htwg.monopoly.controller;

import java.util.List;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.IObservable;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

public interface IController extends IObservable {

	/**
	 * start a turn of a player
	 */
	void startTurn();

	/**
	 * throw the two dices
	 */
	void rollDice();

	/**
	 * end the turn of the current Player.
	 */
	void endTurn();

	/**
	 * the current player buys the street, where he is standing on. (This method
	 * checks not if the player is really standing on a street)
	 * 
	 * @return true if the purchase was successful and false if the current
	 *         player has not enough money
	 */
	boolean buyStreet();

	/**
	 * exit the game.
	 */
	void exitGame();

	/**
	 * return object from current player
	 * 
	 * @return
	 */
	Player getCurrentPlayer();

	/**
	 * 
	 * @return a string with information of current turn
	 */
	String getMessage();

	/**
	 * Return the number of Players.
	 * 
	 * @return
	 */
	int getNumberOfPlayer();

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
	 * Start a new game with the given number of player and the names oft the
	 * players.
	 * 
	 * @param numberOfPlayer
	 * @param nameOfPlayers
	 */
	void startNewGame(int numberOfPlayer, String[] nameOfPlayers);

	/**
	 * Get the field, where the current player is standing on.
	 * @return
	 */
	IFieldObject getCurrentField();

	
	boolean redeemWithCard();

	boolean redeemWithMoney();

	boolean redeemWithDice();

	GameStatus getPhase();

	/**
	 * Returns a list with available options for the current player.
	 * 
	 * @return
	 */
	List<UserAction> getOptions();

	/**
	 * Checks if the given option is valid.
	 * 
	 * @param action
	 * @return true if the valid options contains the given options, false
	 *         otherwise.
	 */
	boolean isCorrectOption(UserAction userOption);

	void performAction(UserAction choosedOption);

	/**
	 * Returns the size of the playfield. Meaning the number of fields existing
	 * in the game.
	 * 
	 * @return integer greater than 0
	 */
	int getFieldSize();

	IFieldObject getFieldAtIndex(int i);

}
