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
	 */
	public PlayerController() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	 * {@inheritDoc}
	 */
	@Override
	public void setNameofPlayer(int i, String string) {
		players[i].setName(string);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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
	 * {@inheritDoc}
	 */
	@Override
	public Player getFirstPlayer() {
		currentPlayer = 0;
		return players[0];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player currentPlayer() {
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
