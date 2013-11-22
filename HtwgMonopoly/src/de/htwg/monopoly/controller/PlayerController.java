package de.htwg.monopoly.controller;

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
	private int currentPlayer;

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
		/* set to first player, if all player on turn */
		if (currentPlayer >= numberOfPlayer) {
			currentPlayer = IMonopolyUtil.FIRST_PLAYER;
		}
		/* return current player object */
		return players[currentPlayer];
	}

}
