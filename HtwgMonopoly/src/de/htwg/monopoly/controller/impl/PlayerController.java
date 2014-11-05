package de.htwg.monopoly.controller.impl;

import java.util.HashMap;
import java.util.Map;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.MonopolyUtils;

/**
 * Controller for player
 * 
 * 
 */
public class PlayerController implements IPlayerController {

	// array with all players
	private Map<Integer, Player> players;

	// the number of players
	private int numberOfPlayer;

	// the player of the current turn
	private int currentPlayer;

	/**
	 * Constructor initialize all player with default values. See {@link Player}
	 * for more information on Player.
	 * 
	 * @param nameOfPlayers
	 * @param inNumberOfPlayers
	 * @deprecated Replaced by the constructor receiving a map.
	 */
	@Deprecated
	public PlayerController(int inNumberOfPlayers, String[] nameOfPlayers) {

		// check correct number of players
		assert nameOfPlayers.length != inNumberOfPlayers : "Anzahl der Spieler and Anzahl der Spielernamen stimmt nicht �berein";

		if (!MonopolyUtils.verifyPlayerNumber(inNumberOfPlayers)) {
			throw new AssertionError("Ung�ltige Anzahl an Spielern.");
		}

		// set number of players
		this.numberOfPlayer = inNumberOfPlayers;

		// create array of players.
		this.players = new HashMap<Integer, Player>();

		// create default players
		for (int i = 0; i < numberOfPlayer; i++) {
			players.put(i, new Player(nameOfPlayers[i]));
		}

	}

	/**
	 * Constructor initialize all player with default values. See {@link Player}
	 * for more information on Player.
	 * 
	 * @param nameOfPlayers
	 * @param inNumberOfPlayers
	 */
	public PlayerController(Map<Integer, String> inPlayers) {
		this.numberOfPlayer = inPlayers.size();

		// assert wrong number.
		if (!MonopolyUtils.verifyPlayerNumber(numberOfPlayer)) {
			throw new AssertionError("Ung�ltige Anzahl an Spielern.");
		}

		// init map
		this.players = new HashMap<Integer, Player>();

		// create default players
		for (Integer i : inPlayers.keySet()) {
			players.put(i, new Player(inPlayers.get(i)));
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getNextPlayer() {
		currentPlayer = (currentPlayer + 1) % numberOfPlayer;
		return getCurrentPlayer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getFirstPlayer() {
		currentPlayer = IMonopolyUtil.FIRST_PLAYER;
		return getCurrentPlayer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getPlayer(int i) {
		return players.get(i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void transferMoney(Player currentPlayer, ICards currentCard) {
		int actualMoney;

		// parse card for the money
		try {
			actualMoney = Integer.parseInt(currentCard.getTarget());
		} catch (NumberFormatException e) {
			throw new AssertionError("String ist keine Zahl" + e);
		}

		// transfer money between bank and player.
		if (currentCard.isReceiveFromToPark()) {
			if (actualMoney < 0) {
				Bank.addParkingMoney(currentPlayer, -actualMoney);
			} else {
				Bank.receiveMoney(currentPlayer, actualMoney);
			}

			// transfer money between players (including the player itself,
			// because it doesn't matter)
		} else {
			for (Player player : players.values()) {
				Bank.receiveMoneyFromPlayer(currentPlayer, player, actualMoney);
			}
		}
	}

}
