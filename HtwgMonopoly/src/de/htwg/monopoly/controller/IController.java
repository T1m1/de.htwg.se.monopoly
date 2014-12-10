package de.htwg.monopoly.controller;

import java.util.List;
import java.util.Map;

import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.IObservable;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;
import de.htwg.monopoly.util.UserAction;

public interface IController extends IObservable {

	/**
	 * Start a new game with the given list of player names. Note: The
	 * number of players must be between
	 * {@link IMonopolyUtil#MIN_NUMBER_OF_PLAYER} and
	 * {@link IMonopolyUtil#MAX_NUMBER_OF_PLAYER}.
	 * 
	 * @param players
	 *            a list containing the names of the players.
	 */
	void startNewGame(List<String> players);
	
	/**
	 * Start a new game with the given map of player names and the corresponding player icon. Note: The
	 * number of players must be between
	 * {@link IMonopolyUtil#MIN_NUMBER_OF_PLAYER} and
	 * {@link IMonopolyUtil#MAX_NUMBER_OF_PLAYER}.
	 * 
	 * @param players
	 *            a map containing the names of the players and the icon.
	 */
	void startNewGame(Map<String, PlayerIcon> players);

	/**
	 * start a turn of a player
	 */
	void startTurn();

	/**
	 * the current player buys the street, where he is standing on. (This method
	 * checks not if the player is really standing on a street)
	 * 
	 * @return true if the purchase was successful and false if the current
	 *         player has not enough money
	 */
	boolean buyStreet();

	/**
	 * end the turn of the current Player.
	 */
	void endTurn();

	/**
	 * 
	 * @return
	 */
	boolean redeemWithCard();

	boolean redeemWithMoney();

	boolean redeemWithDice();

	/**
	 * throw the two dices
	 */
	void rollDiceToRedeem();

	/**
	 * exit the game.
	 */
	void exitGame();

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

	/**
	 * Start a new game with the given number of player and the names oft the
	 * players.
	 * 
	 * @param numberOfPlayer
	 * @param nameOfPlayers
	 * @deprecated use {@link Controller#startNewGame(Map)} instead.
	 */
	void startNewGame(int numberOfPlayer, String[] nameOfPlayers);

}
