package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.entities.Playfield;

public class Controller implements IController{
	private PlayerController players;
	private Playfield field;

	public void startNewGame() {

	}

	public void startTurn() {
		Player currentplayer = players.getNextPlayer();
		if (currentplayer.isInPrison()) {
			currentplayer.incrementPrisonRound();
		} else {
			Dice.throwDice();
			field.movePlayer(currentplayer, (Dice.dice1 + Dice.dice2));
		}
		//notifyObserver();
	}

	public void rollDice() {

	}

	public void endTurn() {

	}

	public void exitGame() {

	}

	public void buyStreet() {

	}

}
