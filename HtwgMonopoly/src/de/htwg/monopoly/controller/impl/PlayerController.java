package de.htwg.monopoly.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.MonopolyUtils;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * Controller for player
 */
public class PlayerController implements IPlayerController {

	// array with all players
	private List<Player> players;

	// the number of players
	private final int numberOfPlayer;

	// the player of the current turn
	private int currentPlayerIndex;

	/**
	 * Constructor initialize a controller for handling all players. You can use
	 * {@link MonopolyUtils#getPlayersWithIcons(List)} for getting PlayerIcons
	 * to a corresponding List
	 * 
	 * @param inPlayers
	 */
	public PlayerController(Map<String, PlayerIcon> inPlayers) {
		this.numberOfPlayer = inPlayers.size();

		// assert wrong number.
		if (!MonopolyUtils.verifyPlayerNumber(numberOfPlayer)) {
			throw new AssertionError("Ung�ltige Anzahl an Spielern.");
		}

		// create and initialize the array of players.
		this.players = new ArrayList<Player>();
		for (String name : inPlayers.keySet()) {

			// create player
			Player createdPlayer = new Player(name, inPlayers.get(name));

			// add player to the array
			players.add(createdPlayer);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getNextPlayer() {
		currentPlayerIndex = (currentPlayerIndex + 1) % numberOfPlayer;
		return getCurrentPlayer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getFirstPlayer() {
		currentPlayerIndex = IMonopolyUtil.FIRST_PLAYER;
		return getCurrentPlayer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getCurrentPlayer() {
		return players.get(currentPlayerIndex);
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
			for (Player tempPlayer : players) {
				Bank.receiveMoneyFromPlayer(currentPlayer, tempPlayer,
						actualMoney);
			}
		}
	}

}
