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

	/**
	 * Start a new game with the given number of player and the names oft the
	 * players.
	 * 
	 * @param numberOfPlayer
	 * @param nameOfPlayers
	 */
	void startNewGame(int numberOfPlayer, String[] nameOfPlayers);

	IFieldObject getCurrentField();

	boolean redeemWithCard();

	boolean redeemWithMoney();

	boolean redeemWithDice();

	GameStatus getPhase();

	List<UserAction> getOptions();

	boolean isCorrectOption(UserAction userOption);



}
