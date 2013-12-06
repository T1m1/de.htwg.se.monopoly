package de.htwg.monopoly.controller.impl;

import java.util.Scanner;

import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.util.IMonopolyUtil;

/**
 * Controller for player
 * 
 * @author RuprechtT
 * 
 */
public class PlayerController {
	/* array with player objects */
	private Player[] players;
	/* number of player in game */
	private int numberOfPlayer;
	/* index of current player */
	// Idee: mit Stack arbeiten, dann braucht man auch keinen index, sondern der
	// player wo "oben" liegt ist dran.
	private int currentPlayer;
	private Scanner in;

	/**
	 * Constructor
	 * 
	 * @param numberOfPlayer
	 */
	public PlayerController(int numberOfPlayer) {

		/* create array with commited number of players */
		this.players = new Player[numberOfPlayer];
		this.numberOfPlayer = numberOfPlayer;
		/*
		 * set current player to the last index, that function getNextPlayer
		 * return the first player by initial call
		 */
		this.currentPlayer = numberOfPlayer;
		in = new Scanner(System.in);
		/* function to init players */
		init();
	}

	/**
	 * initialize player array
	 */
	private void init() {
		/* loop to create player objects with default values */
		for (int i = 0; i < numberOfPlayer; i++) {
			players[i] = new Player();
		}
	}

	/**
	 * function to get next player
	 * 
	 * @return player
	 */
	public Player getNextPlayer() {

		currentPlayer++;
		// wie wäre es mit modulo?
		/* set to first player, if all player on turn */
		if (currentPlayer >= numberOfPlayer) {
			currentPlayer = IMonopolyUtil.FIRST_PLAYER;
		}
		/* return current player object */
		return players[currentPlayer];
	}

	public Player currentPlayer() {
		return players[currentPlayer];
	}

	public boolean readNumberOfPlayer() {

		int numberOfPlayer = 0;

		if (in.hasNext()) {
			/* check if input an integer and in right interval */
			if (in.hasNextInt()) {
				numberOfPlayer = in.nextInt();
				in.nextLine();
			} else {
				in.nextLine();
				return false;
			}
		}

		if (numberOfPlayer < IMonopolyUtil.MIN_NUMBER_OF_PLAYER
				|| numberOfPlayer > IMonopolyUtil.MAX_NUMBER_OF_PLAYER) {
			return false;
		}

		/* if scanned number correct, save it */
		this.numberOfPlayer = numberOfPlayer;
		return true;
	}

	public int getNumberOfPlayer() {
		return this.numberOfPlayer;
	}

	public boolean readNameOfPlayer(int i) {
		try {
			if (in.hasNext()) {
				this.players[i].setName(in.nextLine());
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
