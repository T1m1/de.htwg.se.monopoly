package de.htwg.monopoly.controller.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.IMonopolyUtil;

/**
 * 
 * @author RuprechtT, GorenfloS
 * 
 */
public class Controller extends Observable implements IController {
	private PlayerController players;
	private Playfield field;
	private Player currentPlayer;
	private IFieldObject currentField;
	private Dice dice;
	private GameStatusController status;

	private StringBuilder message;
	private int lastChooseOption;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	/**
	 * public constructor for a new controller create the players, the field and
	 * the dice
	 * 
	 * @param fieldSize
	 */
	@Inject
	public Controller(@Named("FieldSize") int fieldSize) {
		this.field = new Playfield(fieldSize);
		this.message = new StringBuilder();
		this.dice = new Dice(fieldSize);
	}

	/**
	 * function to call at start of a new game TODO map statt integer und array
	 * �bergeben
	 */
	@Override
	public void startNewGame(int numberOfPlayer, String[] nameOfPlayers) {

		// initialize player controller
		this.players = new PlayerController(numberOfPlayer, nameOfPlayers);

		// set current player to first player, notify observers and get ready to
		// play
		this.currentPlayer = players.getFirstPlayer();
		notifyObservers(0);
	}

	/**
	 * function to call at begin of turn.
	 */
	@Override
	public void startTurn() {
		// this currentPlayer players.currentPlayer
		if (currentPlayer.isInPrison()) {
			currentPlayer.incrementPrisonRound();
			message.append(bundle.getString("contr_bsys") + "\n");
		} else {
			turn();
		}
		// �berpr�fen auf was f�rn feldobjek
		// dementsprechend notify
		notifyObservers(1);
		// notifyObservers
	}

	/**
	 * function which moves player and perform action depending
	 */
	private void turn() {
		rollDice();

		/* move player -> max number to dice is fieldSize */
		field.movePlayer(currentPlayer, dice.getResultDice());

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
			message.append(field.performActionAndAppendInfo(currentField,
					currentPlayer));
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

	/**
	 * function to roll dice
	 */
	@Override
	public void rollDice() {
		/* throw dice */
		dice.throwDice();
	}

	/**
	 * call this function if turn ends
	 */
	@Override
	public void endTurn() {
		this.message.delete(0, this.message.length());
		this.currentPlayer = players.getNextPlayer();

		notifyObservers(0);
	}

	/**
	 * End of game function
	 */
	@Override
	public void exitGame() {
		this.players = null;
		// TODO: notify observers and set status finished
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean buyStreet() {

		IFieldObject currentStreet = field.getCurrentField(currentPlayer);

		if (!(currentStreet instanceof Street)) {
			throw new AssertionError(
					"Current player is not standing on a street!");
		}

		return field.buyStreet(currentPlayer, (Street) currentStreet);
	}

	/**
	 * function to pay rent for the current field
	 */
	@Override
	public void payRent() {
		Bank.payRent(currentPlayer, field.getCurrentField(currentPlayer));
		notifyObservers();

	}

	/**
	 * check if money in "Frei Parken" and return them
	 */
	@Override
	public void receiveGoMoney() {
		Bank.receiveMoney(currentPlayer, IMonopolyUtil.LOS_MONEY);
		notifyObservers();
	}

	/**
	 * return object with all players
	 */
	public IPlayerController getPlayers() {
		return players;
	}

	/**
	 * return playfield object
	 */
	public Playfield getField() {
		return field;
	}

	/**
	 * return current player object
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * for TESTCASES - set current field
	 */
	public void setCurrentField(IFieldObject currentField) {
		this.currentField = currentField;
	}

	/**
	 * get string with possible options
	 */
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

	/**
	 * return string with options to choose when user stay on street
	 * 
	 * @return
	 */
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

	/**
	 * return a string with options, if player in prison
	 * 
	 * @return
	 */
	private List<String> getOptionPrison() {
		List<String> options = new ArrayList<String>();
		/* check if current player in prison */
		if (currentPlayer.isInPrison()) {
			/* add option to leave prison */
			options.add("(f) " + bundle.getString("contr_free") + " ("
					+ IMonopolyUtil.FREIKAUFEN + ")");
			options.add("(3) " + bundle.getString("contr_threeDice"));
			if (currentPlayer.hasPrisonFreeCard()) {
				options.add("(c) " + bundle.getString("contr_freeCard"));
			}
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

	@Override
	public Dice getDice() {
		return dice;
	}

}
