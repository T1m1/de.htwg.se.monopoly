package de.htwg.monopoly.controller.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Bank;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.entities.Playfield;
import de.htwg.monopoly.entities.Street;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.IMonopolyUtil;

public class Controller extends Observable implements IController {
	private PlayerController players;
	private Playfield field;
	private Player currentPlayer;
	private IFieldObject currentField;
	private StringBuilder message;

	public Controller() {
		this.players = new PlayerController();
		this.field = new Playfield();
		this.message = new StringBuilder();
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
		this.field.initialize(fieldSize);
	}

	@Override
	public void startNewGame() {
		//startTurn();
		this.currentPlayer = players.currentPlayer();
		notifyObservers(0);
	}

	@Override
	public void startTurn() {
		//this.currentPlayer = players.currentPlayer();
		if (currentPlayer.isInPrison()) {
			currentPlayer.incrementPrisonRound();
		} else {
			Dice.throwDice();
			if (field.movePlayer(currentPlayer, (Dice.resultDice % field.getfieldSize()))) {
				receiveGoMoney();
				message.append("Sie erhalten mehr Geld wegen dem Los-Feld :)");
			}
			currentField = field.getCurrentField(currentPlayer);
			if (currentField.getType() == 's') {
				Street street = (Street) currentField;
				if (street.getOwner() == null) {
					message.append(
							"Diese Straße ist frei. Wollen sie die Straße für ")
							.append(street.getPriceForStreet())
							.append(" kaufen\n");
				} else if (street.getOwner().equals(currentPlayer)) {
					
				} else {
					message.append("Diese Straße gehört ")
							.append(street.getOwner())
							.append(".\nSie müssen ihm jetzt ")
							.append(street.getRent()).append(" Miete zahlen.\n");
				}
			}
		}
		// überprüfen auf was fürn feldobjek
		// dementsprechend notify
		notifyObservers(1);
		//notifyObservers();
	}

	@Override
	public void rollDice() {

	}

	@Override
	public void endTurn() {
		message.delete(0, message.length());
		this.currentPlayer = players.getNextPlayer();
	}

	@Override
	public void exitGame() {

	}

	@Override
	public boolean buyStreet() {
		Street currentStreet = (Street) field.getCurrentField(currentPlayer);
		if (currentStreet.getPriceForStreet() < currentPlayer.getBudget()) {
			currentPlayer.setBudget(currentPlayer.getBudget()
					- currentStreet.getPriceForStreet());
			currentStreet.setOwner(currentPlayer);
			currentPlayer.setOwnership(currentStreet);
			return true;
		}
		return false;

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

	@Override
	public List<String> getOptions(int chooseOption) {

		List<String> options = new ArrayList<String>();

		switch (chooseOption) {
		case 1:
			options.add("d - Würfeln");
			break;
		case 2:
			options.add("y - kaufen");
			options.add("b - Zug Beenden");
		default:
			break;
		}
		options.add("x - Spiel Beenden");
		/**
		 * checkt optionen: - case im Gefängnis - case NICHT im Gefägnis
		 */
		return options;
	}

	public String getMessage() {
		return this.message.toString();
	}

}
