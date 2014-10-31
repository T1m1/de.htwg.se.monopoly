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
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.FieldType;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.UserAction;

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

	private GameStatus phase;
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
		this.status = new GameStatusController(this);
		phase = GameStatus.STOPPED;
	}

	/**
	 * function to call at start of a new game TODO map statt integer und array
	 * �bergeben
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNewGame(int numberOfPlayer, String[] nameOfPlayers) {

		// initialize player controller
		this.players = new PlayerController(numberOfPlayer, nameOfPlayers);

		// set current player to first player, notify observers and get ready to
		// play
		this.currentPlayer = players.getFirstPlayer();

		phase = GameStatus.STARTED;
		status.update();
		notifyObservers(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startTurn() {

		rollDice();

		/* move player -> max number to dice is fieldSize */
		field.movePlayer(currentPlayer, dice.getResultDice());

		// set the current field
		this.currentField = field.getCurrentField(currentPlayer);

		// perform the action, depending on the field.
		if (currentField.getType() == FieldType.COMMUNITY_STACK) {
			message.append(performCommCardAction());
		} else if (currentField.getType() == FieldType.CHANCE_STACK) {
			message.append(performChanceCardAction());
		} else {
			message.append(field.performActionAndAppendInfo(currentField,
					currentPlayer));
		}

		// �berpr�fen auf was f�rn feldobjek
		// dementsprechend notify
		phase = GameStatus.DURING_TURN;
		status.update();
		notifyObservers(1);
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

		phase = GameStatus.DURING_TURN;
		status.update();
		return field.buyStreet(currentPlayer, (Street) currentStreet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endTurn() {
		this.message.delete(0, this.message.length());
		this.currentPlayer = players.getNextPlayer();

		phase = GameStatus.BEFORE_TURN;
		status.update();
		notifyObservers(0);
	}

	/**
	 * Tries to redeem the current player with a prison free card.
	 * 
	 * @return true if the player had a card and was set free.
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean redeemWithCard() {
		if (currentPlayer.hasPrisonFreeCard()) {
			currentPlayer.usePrisonFreeCard();
			currentPlayer.setInPrison(false);
			phase = GameStatus.BEFORE_TURN;
			status.update();
			return true;
		}
		return false;
	}

	/**
	 * Tries to redeem the player with money.
	 * 
	 * @return true if the player had enough money and was set free from prison,
	 *         false otherwise.
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean redeemWithMoney() {
		if (currentPlayer.getBudget() < IMonopolyUtil.FREIKAUFEN) {
			return false;
		}
		currentPlayer.decrementMoney(IMonopolyUtil.FREIKAUFEN);
		currentPlayer.setInPrison(false);
		status.update();
		return true;
	}

	/**
	 * For now there is no option to redeem with dice.The player has to wait.
	 * 
	 * @return
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean redeemWithDice() {

		currentPlayer.incrementPrisonRound();
		message.append(bundle.getString("contr_bsys") + "\n");

		return currentPlayer.isInPrison();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollDice() {
		/* throw dice */
		dice.throwDice();
	}

	/**
	 * End of game function
	 */
	@Override
	public void exitGame() {
		this.players = null;

		phase = GameStatus.STOPPED;
		status.update();
		notifyObservers();
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
	public Dice getDice() {
		return dice;
	}

	/**
	 * return a String with information about the current turn
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return this.message.toString();
	}

	/**
	 * @return the phase
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public GameStatus getPhase() {
		return phase;
	}

	/**
	 * return object with all players
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerController getPlayers() {
		return players;
	}

	/**
	 * return playfield object
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Playfield getField() {
		return field;
	}

	/**
	 * return current player object
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Returns a list with available options for the current player.
	 * 
	 * @return
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserAction> getOptions() {
		return status.getOptions();
	}

	/**
	 * Checks if the given option is valid.
	 * 
	 * @param action
	 * @return true if the valid options contains the given options, false
	 *         otherwise.
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCorrectOption(UserAction userOption) {
		return status.getOptions().contains(userOption);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfPlayer() {
		return players.getNumberOfPlayer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getPlayer(int i) {
		return players.getPlayer(i);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFieldObject getCurrentField() {

		return field.getCurrentField(currentPlayer);
	}

	/**
	 * for TESTCASES - set current field
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCurrentField(IFieldObject currentField) {
		this.currentField = currentField;
	}

	/**
	 * return string with options to choose when user stay on street
	 * 
	 * @return
	 */
	private List<String> getOptionOnStreet() {
		List<String> options = new ArrayList<String>();
		/* if current field a steet */
		if (currentField.getType() == FieldType.STREET) {
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
	@Deprecated
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
	 * get string with possible options
	 */
	@Override
	@Deprecated
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

	// private void notify(GameStatus inStatus) {
	// status = inStatus;
	// event.setStatus(inStatus);
	// notifyObservers(event);
	//
	// }
}
