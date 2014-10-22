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
	 * 
	 * @param numberOfPlayer
	 */
	public PlayerController() {
		in = new Scanner(System.in);

	}

	/**
	 * initialize player array with this.numberOfPlayer
	 */
	private void init() {
		/* create array with number from method readNumberOfPlayer() */
		this.players = new Player[this.numberOfPlayer];
		/*
		 * set current player to the last index, that function getNextPlayer
		 * return the first player by initial call
		 */
		this.currentPlayer = this.numberOfPlayer - 1;
		/* loop to create player objects with default values */
		for (int i = 0; i < numberOfPlayer; i++) {
			players[i] = new Player();
		}
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

	/**
	 * read name of player
	 */
	public boolean readNameOfPlayer(int i) {
		// S: wann zur h�lle is das nicht wahr? also testbar?
		// T: ich denke NIE
		if (in.hasNext()) {
			this.players[i].setName(in.nextLine());
		}
		return true;

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
	 */
	public void setNumberOfPlayer(int number) {
		this.numberOfPlayer = number;
	}

	public void setNameofPlayer(int i, String string) {
		throw new UnsupportedOperationException("not yet implemented");
		
	}

}
