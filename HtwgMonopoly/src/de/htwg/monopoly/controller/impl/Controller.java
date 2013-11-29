package de.htwg.monopoly.controller.impl;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.entities.Playfield;
import de.htwg.monopoly.util.IMonopolyUtil;

public class Controller implements IController{
	private PlayerController players;
	private Playfield field;
	
	@Override
	public void initGame(int numberOfPlayer) {
		players = new PlayerController(numberOfPlayer);
		
		
		
		//notifyObserver
	}

	@Override
	public void startNewGame() {
		startTurn();
		//notifyObserver
	}

	@Override
	public void startTurn() {
		Player currentplayer = players.getNextPlayer();
		if (currentplayer.isInPrison()) {
			currentplayer.incrementPrisonRound();
		} else {
			Dice.throwDice();
			field.movePlayer(currentplayer, (Dice.dice1 + Dice.dice2));
		}
		//notifyObserver
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
	public void buyStreet() {

	}

	@Override
	public void addPlayer(String Name) {
		// Frage: wird wirklich jeder player erreicht, oder etwa der erste übersprungen?
		Player currentPlayer = players.getNextPlayer();
		currentPlayer.setName(Name);
		currentPlayer.setBudget(IMonopolyUtil.INITIAL_MONEY);
		//TODO Character checking 
		//notifyObserver
	}

	@Override
	public void checkFieldType() {
		field.getCurrentField(null);
		//pseudo
		/*switsch fieldobject
		case street
		case ereignis
		case gemeinschaft
		case los
		case frei parken
		case gehe ins gefängnis
		case zu besuch im gefängnis
		case bahnhof
		case elektrowerk
		case wasserwerk
		case steuerfeld
		notifyObserver
		*/
	}

	@Override
	public void payRent() {
		
	}

	@Override
	public void receiveLosMoney() {
		// TODO Auto-generated method stub
		
	}

	public PlayerController getPlayers() {
		return players;
	}

	public Playfield getField() {
		return field;
	}
	
	



}
