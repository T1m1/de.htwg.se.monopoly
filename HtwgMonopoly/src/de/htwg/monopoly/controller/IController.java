package de.htwg.monopoly.controller;

import java.util.List;

import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.entities.Playfield;
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
	 * 
	 * @param numberOfPlayer
	 */
	void initGame(int fieldSize);

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

	void checkFieldType();

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

	/**
	 * Return a Object which control all the players
	 * 
	 * 
	 * @return
	 */
	PlayerController getPlayers();

	/***
	 * set number of player
	 * @return status
	 */
	boolean setNumberofPlayer();

	/**
	 * set name of player i
	 * @param i -> number of player to set name for it
	 * @return status
	 */
	boolean setNameofPlayer(int i);

	/**
	 * return object from current player
	 * @return
	 */
	Player getCurrentPlayer();
	
	/**
	 * 
	 * @return object of monopoly field
	 */
	Playfield getField();

	/**
	 * for tui, to show which option user can choose
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
	 * function to check if user have the correct options choose
	 * correct options are options they are contains in list of function getOption
	 * @param l
	 * @return
	 */
	boolean isCorrectOption(String l);


}
