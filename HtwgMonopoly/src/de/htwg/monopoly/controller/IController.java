package de.htwg.monopoly.controller;

import java.util.List;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.IObservable;

public interface IController extends IObservable {

	/**
	 * start the actual Gameplay
	 */
	void startNewGame();

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
	 * Money will be transferred from to current player to the player who owns
	 * the current street where the current player is standing on.
	 */
	void payRent();

	/**
	 * The current player receive the amount of money, which you get when you
	 * pass the GO field
	 */
	void receiveGoMoney();

	/***
	 * set number of player. This function reads from stdin.
	 * 
	 * @return true if setting the number was succesful.
	 */
	boolean setNumberofPlayer();

	/**
	 * set name of player i
	 * 
	 * @param i
	 *            -> number of player to set name for it
	 * @return status
	 */
	boolean setNameofPlayer(int i);

	/**
	 * return object from current player
	 * 
	 * @return
	 */
	Player getCurrentPlayer();

	/**
	 * 
	 * @return object of monopoly field
	 */
	IPlayfield getField();

	/**
	 * for tui, to show which option user can choose
	 * 
	 * @param chooseOption
	 * @return a list with all option
	 */
	List<String> getOptions(int chooseOption);

	/**
	 * 
	 * @return a string with information of current turn
	 */
	String getMessage();

	/**
	 * function to check if user have the correct options choose correct options
	 * are options they are contains in list of function getOption
	 * 
	 * @param l
	 * @return
	 */
	boolean isCorrectOption(String l);

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
	 * Returns the Player-Controller.
	 * 
	 * @return
	 */
	IPlayerController getPlayers();

	/**
	 * Sets the current Field to fieldObject. This is a function only for
	 * testing.
	 * 
	 * @param fieldObject
	 */
	void setCurrentField(IFieldObject fieldObject);

	/**
	 * get dice objects
	 * 
	 * @return
	 */
	Dice getDice();

}
