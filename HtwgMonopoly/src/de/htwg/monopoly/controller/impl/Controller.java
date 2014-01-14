package de.htwg.monopoly.controller.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.IMonopolyUtil;


public class Controller extends Observable implements IController {
	private PlayerController players;
	private Playfield field;
	private Player currentPlayer;
	private IFieldObject currentField;
	@Inject @Named("FieldSize") private int fieldSize;

	private StringBuilder message;
	private int lastChooseOption;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	@Inject
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
	public void startNewGame() {
		// TODO ZufallsSpieler auswählen
		this.currentPlayer = players.getNextPlayer();
		notifyObservers(0);
	}

	@Override
	public void startTurn() {
		// this currentPlayer players.currentPlayer
		if (currentPlayer.isInPrison()) {
			currentPlayer.incrementPrisonRound();
			message.append(bundle.getString("contr_bsys") + "\n");
		} else {
			turn();
		}
		// überprüfen auf was fürn feldobjek
		// dementsprechend notify
		notifyObservers(1);
		// notifyObservers
	}

	private void turn() {
		rollDice();

		/* move player -> max number to dice is fieldSize */
		field.movePlayer(currentPlayer,
				(Dice.getResultDice() % field.getfieldSize() + 1));

		/* set current field */
		this.currentField = field.getCurrentField(currentPlayer);

		/*
		 * perform action depending on the field, the current player is standing
		 * on
		 */
		if (fieldIsACommStack()) {
			message.append(performCommCardAction());
		} else if (fieldIsAChanceStack()) {
			message.append(performChanceCardAction());
		} else {
			message.append(field.appendInfo(currentField, currentPlayer));
		}
		
	}

	/**
	 * Perform the action according to the text on the card. It depends if the
	 * player has to move or money is transferred.
	 * 
	 * @return
	 */
	private String performCommCardAction() {
		ICards currentCommCard = field.getCommStack().getNextCard();
		StringBuilder sb = new StringBuilder();

		sb.append(MessageFormat.format(bundle.getString("play_card"),
				currentCommCard.getDescription()));

		if (isMoveAction(currentCommCard)) {
			sb.append(field.movePlayerTo(currentPlayer,
					currentCommCard.getTarget()));
		} else {
			players.transferMoney(currentPlayer, currentCommCard);
		}
		return sb.toString();
	}

	/**
	 * Does the same as performCommCardAction() except, for a Chance Card
	 * 
	 * @return
	 */
	private String performChanceCardAction() {
		ICards currentChanceCard = field.getChanStack().getNextCard();
		StringBuilder sb = new StringBuilder();

		sb.append(MessageFormat.format(bundle.getString("play_card"),
				currentChanceCard.getDescription()));

		if (isMoveAction(currentChanceCard)) {
			sb.append(field.movePlayerTo(currentPlayer,
					currentChanceCard.getTarget()));
		} else {
			players.transferMoney(currentPlayer, currentChanceCard);
		}
		return sb.toString();
	}

	/**
	 * Checks if the current Field is a Stack of Community Cards
	 * 
	 * @return
	 */
	private boolean fieldIsACommStack() {
		return (currentField.getType() == 'g');
	}

	/**
	 * Checks if the current Field is a Stack of Chance Cards
	 * 
	 * @return
	 */
	private boolean fieldIsAChanceStack() {
		return (currentField.getType() == 'e');
	}
	
	/**
	 * Check if the Card is a "player move Card" or a "money transfer card" 
	 * 
	 * @param card
	 * @return boolean
	 */
	private boolean isMoveAction(ICards card) {
		int test;
		try {
			test = Integer.parseInt(card.getTarget());
			if (Math.abs(test) < IMonopolyUtil.MAX_NUMBER_OF_STEPS) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	@Override
	public void rollDice() {
		/* throw dice */
		Dice.throwDice();
	}

	@Override
	public void endTurn() {
		this.message.delete(0, this.message.length());
		this.currentPlayer = players.getNextPlayer();
		
		notifyObservers(0);
	}

	@Override
	public void exitGame() {
		// TODO
	}

	@Override
	public boolean buyStreet() {
		/* get current street */
		Street currentStreet = (Street) field.getCurrentField(currentPlayer);
		/* check if enough money */
		if (currentStreet.getPriceForStreet() < currentPlayer.getBudget()) {
			/* decrement money of current player */
			currentPlayer.setBudget(currentPlayer.getBudget()
					- currentStreet.getPriceForStreet());
			currentStreet.setOwner(currentPlayer);
			/* add street to ownership of current player */
			currentPlayer.addOwnership(currentStreet);
			return true;
		}
		/* TODO: not enough money!! -> end of game? */
		return false;

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

	/* for junit test */
	public void setCurrentField(IFieldObject currentField) {
		this.currentField = currentField;
	}

	@Override
	public List<String> getOptions(int chooseOption) {

		/* TODO INfos selber suchen und zusammenbauen */

		List<String> options = new ArrayList<String>();

		switch (chooseOption) {
		case 1:
			/* add options if user in prison */
			options.addAll(getOptionPrison());
			/* add option to dice */
			options.add("(d) " + bundle.getString("dice"));
			break;
		case 2:
			/* add options if user on a street object */
			options.addAll(getOptionOnStreet());
			// NO BREAK
		case IMonopolyUtil.OPTION_FINISH:
			options.add("(b) " + bundle.getString("contr_finish"));
		default:
			break;
		}
		/*
		 * set information witch option is choose, to check correct user input
		 * in function isCorrectOption
		 */
		this.lastChooseOption = chooseOption;
		/* add option to quit the game (without loosing) */
		options.add("(x) " + bundle.getString("contr_quit"));
		/* return a list of options (strings) */
		return options;
	}

	private List<String> getOptionOnStreet() {
		List<String> options = new ArrayList<String>();
		/* if current field a steet */
		if (currentField.getType() == 's') {
			Street s = (Street) currentField;
			/* check if street have a owner */
			if (s.getOwner() == null) {
				/* if not -> add option to buy street */
				options.add("(y) " + bundle.getString("contr_buy"));
			}
		}
		return options;
	}

	private List<String> getOptionPrison() {
		List<String> options = new ArrayList<String>();
		/* check if current player in prison */
		if (currentPlayer.isInPrison()) {
			/* add option to leave prison */
			options.add("(f) " + bundle.getString("contr_free") + " ("
					+ IMonopolyUtil.FREIKAUFEN + ")");
			options.add("(3) " + bundle.getString("contr_threeDice"));
			// TODO check if contains free park card
		}
		/* returns a list with options */
		return options;

	}

	/**
	 * function to check if input of user correct
	 */
	public boolean isCorrectOption(String chooseOption) {
		/* get the last options for current user */
		List<String> options = new ArrayList<String>();
		options = getOptions(this.lastChooseOption);
		/* create string for search */
		String strChooseOptions = "(" + chooseOption + ")";
		/* check if choosen option containing in option list for user */
		for (String tmp : options) {
			if (tmp.contains(strChooseOptions)) {
				/* correct option */
				return true;
			}
		}
		/* not correct options */
		return false;
	}

	/**
	 * return a String with information about the current turn
	 */
	public String getMessage() {
		return this.message.toString();
	}

	@Override
	public int getNumberOfPlayer() {
		return players.getNumberOfPlayer();
	}

	@Override
	public Player getPlayer(int i) {
		return players.getPlayer(i);
	}

}
