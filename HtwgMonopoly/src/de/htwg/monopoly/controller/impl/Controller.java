package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Bank;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.entities.Playfield;
import de.htwg.monopoly.entities.Street;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.IMonopolyUtil;

public class Controller extends Observable implements IController {
	private PlayerController players;
	private Playfield field;
	private Player currentPlayer;

	public Controller() {
		this.players = new PlayerController();
		this.field = new Playfield();
	}
	
	@Override
	public boolean setNumberofPlayer() {
		return players.readNumberOfPlayer();
	}
	
	@Override
	public boolean setNameofPlayer(int i) {
		return players.readNameOfPlayer(i);
	}
	
	@Override
	public void initGame(int fieldSize) {
		//TODO initialize Gamefield. Streets etc.
		this.field.initialize(fieldSize);
	}

	@Override
	public void startNewGame() {
		startTurn();
	}

	@Override
	public void startTurn() {
		this.currentPlayer = players.getNextPlayer();
		if (currentPlayer.isInPrison()) {
			currentPlayer.incrementPrisonRound();
		} else {
			Dice.throwDice();
			field.movePlayer(currentPlayer, (1));
		}
		notifyObservers();
	}

	@Override
	public void rollDice() {

	}

	@Override
	public void endTurn() {

	}

	@Override
	public void exitGame() {

	}

	@Override
	public boolean buyStreet() {
		Street currentStreet = (Street) field.getCurrentField(currentPlayer);
		if (currentStreet.getPriceForStreet() < currentPlayer.getBudget()) {
			currentPlayer.setBudget(currentPlayer.getBudget() - currentStreet.getPriceForStreet());
			currentStreet.setOwner(currentPlayer);
			return true;
		}
		return false;

	}

	@Override
	public void addPlayer(String name) {
		// Frage: wird wirklich jeder player erreicht, oder etwa der erste
		// übersprungen?
		Player player = players.getNextPlayer();
		player.setName(name);
		player.setBudget(IMonopolyUtil.INITIAL_MONEY);
		// TODO Character checking
		// notifyObserver
	}

	@Override
	public void checkFieldType() {
		field.getCurrentField(currentPlayer);
		// pseudo
		/*
		 * switsch fieldobject case street case ereignis case gemeinschaft case
		 * los case frei parken case gehe ins gefängnis case zu besuch im
		 * gefängnis case bahnhof case elektrowerk case wasserwerk case
		 * steuerfeld notifyObserver
		 */
	}

	@Override
	public void payRent() {
		Bank.payRent(currentPlayer, field.getCurrentField(currentPlayer));
		notifyObservers();

	}

	@Override
	public void receiveGoMoney() {
		Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
		notifyObservers();
	}

	public PlayerController getPlayers() {
		return players;
	}

	public Playfield getField() {
		return field;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	

}
