package de.htwg.monopoly.controller;

public interface IController {
	
	
	public void startNewGame();

	/**
	 * 
	 */
	void startTurn();

	void rollDice();

	void endTurn();

	void exitGame();
	
	public void buyStreet();

}
