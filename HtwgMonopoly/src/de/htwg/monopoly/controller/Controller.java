package de.htwg.monopoly.controller;

import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.entities.Playfield;

public class Controller {
	PlayerController players;
	Playfield field;

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
