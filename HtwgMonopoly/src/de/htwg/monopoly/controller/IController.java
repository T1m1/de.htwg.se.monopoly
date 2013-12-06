package de.htwg.monopoly.controller;

public interface IController {

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
	void initGame(int numberOfPlayer);

	/**
	 * adds a Player to the game.
	 * 
	 * @param name
	 *            of the player
	 */
	void addPlayer(String name);

	/**
	 * end the turn of the current Player.
	 */
	void endTurn();
	
	/**
	 * the current player buys the street, where he is standing on.
	 * @return true if the purchase was successful and false if the current player has not enough money
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
}
