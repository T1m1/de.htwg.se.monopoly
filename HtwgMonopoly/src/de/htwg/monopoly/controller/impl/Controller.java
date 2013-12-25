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
	private int lastChooseOption;
	
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
		// TODO ZufallsSpieler auswählen
		this.currentPlayer = players.getNextPlayer();
		notifyObservers(0);
	}

	@Override
	public void startTurn() {
		//this currentPlayer   players.currentPlayer  
		if (currentPlayer.isInPrison()) {
			currentPlayer.incrementPrisonRound();
			message.append("Sie stecken im Bsys Labor fest\n");
		} else {
			Dice.throwDice();
			field.movePlayer(currentPlayer, (Dice.getResultDice() % field.getfieldSize() +1));
			
			this.currentField = field.getCurrentField(currentPlayer);
			
			message.append(field.appendInfo(currentField, currentPlayer));
		}
		// überprüfen auf was fürn feldobjek
		// dementsprechend notify
		notifyObservers(1);
		//notifyObservers
	}

	@Override
	public void rollDice() {

	}

	@Override
	public void endTurn() {
		this.message.delete(0, this.message.length());
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

		/* TODO INfos selber suchen und zusammenbauen */
		
		
		List<String> options = new ArrayList<String>();

		switch (chooseOption) {
		case 1:
			if (currentPlayer.isInPrison()) {
				options.add("(f) Freikaufen (" + IMonopolyUtil.FREIKAUFEN +")");
				options.add("(3) Drei Versuche für Pasch");
				// TODO check if contains free park card
			} else {
				options.add("(d) Würfeln");
			}
			
			
			break;
		case 2:
			/* if current field a steet */
			if (currentField.getType() == 's') {
				Street s = (Street) currentField;
				if (s.getOwner() == null) {
					options.add("(y) Kaufen & Zug beenden");
				}
			}
			// NO BREAK
		case 3:
			options.add("(b) Zug Beenden");
		default:
			break;
		}
		this.lastChooseOption = chooseOption;
		options.add("(x) Aufgeben");
		return options;
	}
	
	public boolean isCorrectOption(String chooseOption) {
		List<String> options = new ArrayList<String>();
		options = getOptions(this.lastChooseOption);
		String strChooseOptions = "(" + chooseOption + ")";
		for (String tmp : options) {
			if(tmp.contains(strChooseOptions)) {
				return true;
			}
		}
		return false;
	}

	public String getMessage() {
		return this.message.toString();
	}

}
