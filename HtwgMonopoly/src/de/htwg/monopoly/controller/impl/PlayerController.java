package de.htwg.monopoly.controller.impl;

import java.util.Scanner;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;

/**
 * Controller for player
 * 
 * 
 */
public class PlayerController implements IPlayerController {
	/* array with player objects */
	private Player[] players;
	/* number of player in game */
	private int numberOfPlayer;
	private int currentPlayer;
	private Scanner in;

	/**
	 * Constructor
	 */
	public PlayerController() {

	}


	public Player getNextPlayer() {

		currentPlayer++;
		/* set to first player, if all player on turn */
		if (currentPlayer >= numberOfPlayer) {
			currentPlayer = IMonopolyUtil.FIRST_PLAYER;
		}
		/* return current player object */
		return players[currentPlayer];
	}

	/**
	 * return the current player object
	 */
	public Player currentPlayer() {
		return players[currentPlayer];
	}



	/**
	 * get number of player
	 */
	public int getNumberOfPlayer() {
		return this.numberOfPlayer;
	}



	/**
	 * get a specific player
	 */
	public Player getPlayer(int i) {
		return this.players[i];
	}

	/**
	 * transfer money
	 */
	public void transferMoney(Player currentPlayer, ICards currentCard) {
		int actualMoney;
		try {
			actualMoney = Integer.parseInt(currentCard.getTarget());
		} catch (NumberFormatException e) {
			throw new AssertionError("String ist keine Zahl" + e);
		}
		if (currentCard.isReceiveFromToPark()) {
			if (actualMoney < 0) {
				Bank.addParkingMoney(currentPlayer, -actualMoney);
			} else {
				Bank.receiveMoney(currentPlayer, actualMoney);
			}
		} else {
			for (Player player : players) {
				Bank.receiveMoneyFromPlayer(currentPlayer, player, actualMoney);
			}
		}
	}

	/**
	 * Set the number of player for the game.
	 * 
	 * @param number
	 *            a number between 2 and 6
	 * @return true if correct number of players was omitted.
	 */
	public boolean setNumberOfPlayer(int number) {
		// TODO: abfrage weiter oben oder Zahlen global setzen.
		if (number < 2 || number > 6) {
			return false;
		}
		this.numberOfPlayer = number;
		return true;
	}

}
