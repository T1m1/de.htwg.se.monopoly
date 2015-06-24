package de.htwg.monopoly.controller;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.IObservable;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.PlayerIcon;
import de.htwg.monopoly.util.UserAction;

import java.util.List;
import java.util.Map;

public interface IController extends IObservable {

	/**
	 * Start a new game with the given list of player names. This method is
	 * suitable for TUI & GUI. It connects the player names with random player
	 * icons. Note: The number of players must be between
	 * {@link IMonopolyUtil#MIN_NUMBER_OF_PLAYER} and
	 * {@link IMonopolyUtil#MAX_NUMBER_OF_PLAYER}.
	 * 
	 * @param players
	 *            a list containing the names of the players.
	 */
	void startNewGame(List<String> players);

	/**
	 * 
	 * !!! IMPORTANT for WEBUI !!! -> Do not delete * Start a new game with the
	 * given map of player names and the corresponding player icon. This method
	 * is suitable for WEBUI (or other instances which provide player icons).
	 * Note: The number of players must be between
	 * {@link IMonopolyUtil#MIN_NUMBER_OF_PLAYER} and
	 * {@link IMonopolyUtil#MAX_NUMBER_OF_PLAYER}.
	 * 
	 * @param players
	 *            a map containing the names of the players and the icon.
	 */
	void startNewGame(Map<String, PlayerIcon> players);

	/**
	 * Starts a turn of a player. Means: Throwing the dice and moving the player
	 * according to the result. The happened action is stored in the message
	 * object and can retrieved via {@link IController#getMessage()}
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
	 * End the turn of the current player. Sets the current player to the next
	 * one and clears the message Object.
	 */
	void endTurn();

	/**
	 * Tries to redeem the current player with a prison free card.
	 * 
	 * @return true if the player had a card and was set free, false otherwise
	 */
	boolean redeemWithCard();

	/**
	 * Tries to redeem the player with money.
	 * 
	 * @return true if the player had enough money and was set free from prison,
	 *         false otherwise.
	 */
	boolean redeemWithMoney();

	/**
	 * Activate the game phase, where the player tries to redeem by throwing a
	 * "pasch" within 3 throws. Call {@link IController#rollDiceToRedeem()} for
	 * throwing the dice to actual redeem the player.
	 * 
	 * @return always true
	 */
	boolean redeemWithDice();

	/**
	 * Throw the two dices. If the result is a "pasch", the player is set free
	 * from prison and is able to start his turn.
	 */
	void rollDiceToRedeem();

	/**
	 * Exit the game. Sets all instance variable to null, except the fieldsize,
	 * dice and userOptions. The controller instance is now ready to start a new
	 * game (or end the application.)
	 */
	void exitGame();

	/**
	 * Checks if the given option is valid. It is <b>highly</b> recommended to
	 * verify your next controller call against this method. Otherwise the
	 * outcome of the game is unpredictable. You can get all available Options
	 * from {@link IController#getOptions()}
	 * 
	 * @param userOption
	 *            user option of type UserAction
	 * @return true if the valid options contains the given options, false
	 *         otherwise.
	 */
	boolean isCorrectOption(UserAction userOption);

	/**
	 * return object from current player
	 * 
	 * @return
	 */
	Player getCurrentPlayer();

	/**
	 * The returned String represents all action, which happened during the
	 * turn. It is suitable to display this message to the user every time there
	 * is an update call.
	 * 
	 * @return a string with information of current turn
	 */
	String getMessage();

	/**
	 * @return a Question for getting out of jail
	 */
	String getPrisonQuestion();

	/**
	 * Checks if the answer to the current drawn question is correct and sets
	 * the player free if so.
	 * 
	 * @param answer
	 */
	boolean checkPlayerAnswer(boolean answer);

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

	/**
	 * Returns the current phase of the game. For more information see
	 * {@link GameStatus}
	 * 
	 * @return
	 */
	GameStatus getPhase();

	/**
	 * Returns a list with available options for the current player. Suitable
	 * for displaying it to the user.
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

	/**
	 * Returns a field object at the index i.
	 * 
	 * @param i
	 * @return
	 */
	IFieldObject getFieldAtIndex(int i);

	/**
	 * This method draws a card from the current field, where the player is
	 * standing on. The text of the drawn card is stored in the message Object
	 * and is retrieved via {@link IController#getMessage()}.
	 */
	void drawCard();

	/**
	 * Saves a game of Monopoly to a database
	 * 
	 * @param name
	 * @throws IllegalAccessException
	 */
	void saveGameToDB(String name) throws IllegalAccessException;

	/**
	 * Loads a previous game of Monopoly from a database
	 * 
	 * @param id
	 */
	void loadGameFromDB(String id);

	/**
	 * Returns a map of all saved games in the database with: Key = UUID and
	 * value = name.
	 * 
	 * @return
	 */
	Map<String, String> getSavedGames();

	void deleteGame(String id);
	
	void cheatAndGetMoney(int money);

}
