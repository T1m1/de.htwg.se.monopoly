/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import java.util.List;
import java.util.Map;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * @author Steffen
 *
 */
public class MockPlayerController implements IPlayerController {
	
	// array with all players
	private List<Player> players;

	// the number of players
	private int numberOfPlayer;

	// the player of the current turn
	private int currentPlayer;

	private Player nextPlayer;

	public MockPlayerController(Map<String, PlayerIcon> players) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player getNextPlayer() {
		return nextPlayer;
	}

	@Override
	public int getNumberOfPlayer() {
		return numberOfPlayer;
	}

	@Override
	public Player getPlayer(int i) {
		return players.get(i);
	}

	@Override
	public void transferMoney(Player currentPlayer, ICards currentCard) {
		// do nothing
	}

	@Override
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}

	@Override
	public Player getFirstPlayer() {
		return players.get(0);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public void setNumberOfPlayer(int numberOfPlayer) {
		this.numberOfPlayer = numberOfPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	
}
