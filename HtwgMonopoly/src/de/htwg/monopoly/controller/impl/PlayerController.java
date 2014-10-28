package de.htwg.monopoly.controller.impl;

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

	/**
	 * Constructor
	 * 
	 * @param nameOfPlayers
	 * @param numberOfPlayer2
	 */
	public PlayerController(int numberOfPlayer2, String[] nameOfPlayers) {

		// check correct number of players
		assert nameOfPlayers.length == numberOfPlayer : "Anzahl der Spieler and Anzahl der Spielernamen stimmt nicht �berein";
		assert (numberOfPlayer < IMonopolyUtil.MIN_NUMBER_OF_PLAYER || numberOfPlayer > IMonopolyUtil.MAX_NUMBER_OF_PLAYER) : "Ung�ltige Anzahl an Spielern.";

		// set number of players
		this.numberOfPlayer = numberOfPlayer2;

		// create array of players.
		this.players = new Player[numberOfPlayer2];

		// create default players
		for (int i = 0; i < numberOfPlayer; i++) {
			players[i] = new Player();
			players[i].setName(nameOfPlayers[i]);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getNextPlayer() {

		currentPlayer++;
		
		// reset to first player at the end of the player queue
		// TODO do it with collections!!
		if (currentPlayer >= numberOfPlayer) {
			currentPlayer = IMonopolyUtil.FIRST_PLAYER;
		}
		
		// return the current player
		return players[currentPlayer];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getFirstPlayer() {
		currentPlayer = IMonopolyUtil.FIRST_PLAYER;
		return players[IMonopolyUtil.FIRST_PLAYER];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getCurrentPlayer() {
		return players[currentPlayer];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfPlayer() {
		return this.numberOfPlayer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getPlayer(int i) {
		return this.players[i];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

}
