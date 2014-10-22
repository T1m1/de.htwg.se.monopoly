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
	 * function to read number of player
	 */
	public boolean readNumberOfPlayer() {

		int tmpNumberOfPlayer = 0;

		if (in.hasNext()) {
			/* check if input an integer and in right interval */
			if (in.hasNextInt()) {
				tmpNumberOfPlayer = in.nextInt();
				in.nextLine();
			} else {
				/* TODO: alles weg */
				in.nextLine();
				return false;
			}
		}

		/* check if input smaller as maximum of player and bigger as minimum */
		if (tmpNumberOfPlayer < IMonopolyUtil.MIN_NUMBER_OF_PLAYER
				|| tmpNumberOfPlayer > IMonopolyUtil.MAX_NUMBER_OF_PLAYER) {
			return false;
		}

		/* if scanned number correct, save it */
		this.numberOfPlayer = tmpNumberOfPlayer;
		init();
		return true;
	}

	/**
	 * get number of player
	 */
	public int getNumberOfPlayer() {
		return this.numberOfPlayer;
	}


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
	 * Set the number of player for the game and initialize the array with
	 * player objects.
	 */
	public void setNumberOfPlayer(int number) {
		// set number of players
		this.numberOfPlayer = number;
		// create array of players.
		this.players = new Player[this.numberOfPlayer];
		// create default players
		for (int i = 0; i < numberOfPlayer; i++) {
			players[i] = new Player();
		}

	}

	/**
	 * Set the name of the player in the player array according to the given
	 * index.
	 * 
	 * @param i
	 *            the index of the player array
	 * @param string
	 *            the name of the player
	 */
	public void setNameofPlayer(int i, String string) {
		players[i].setName(string);

	}


	@Override
	public Player getFirstPlayer() {
		return players[0];
	}

}
