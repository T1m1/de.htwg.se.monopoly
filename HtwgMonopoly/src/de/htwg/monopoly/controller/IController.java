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
	
	void initGame(int numberOfPlayer);
	
	void addPlayer(String Name);

	void endTurn();

	void buyStreet();
	
	void checkFieldType();

	void exitGame();
	
	void payRent();
	
	void receiveLosMoney();
}
