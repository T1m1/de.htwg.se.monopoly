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
	private UserOptionsController userOptions;

	private StringBuilder message;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	/**
	 * public constructor for a new controller create the players, the field and
	 * the dice
	 * 
	 * @param fieldSize
	 */
	// @Inject
	// public Controller(@Named("FieldSize") int fieldSize) {
	// this.field = new Playfield(fieldSize);
	// this.message = new StringBuilder();
	// this.dice = new Dice(fieldSize);
	// this.userOptions = new UserOptionsController(this);
	// phase = GameStatus.STOPPED;
	// }

	public Controller(int fieldSize) {
		phase = GameStatus.STOPPED;
		this.field = new Playfield(fieldSize);
		this.message = new StringBuilder();
		this.dice = new Dice(fieldSize);
		this.userOptions = new UserOptionsController(this);
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

		updateGameStatus(GameStatus.STARTED);
		notifyObservers(GameStatus.STARTED);
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
		this.currentField = field.getFieldOfPlayer(currentPlayer);

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
		updateGameStatus(GameStatus.DURING_TURN);
		notifyObservers(GameStatus.DURING_TURN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean buyStreet() {

		IFieldObject currentStreet = field.getFieldOfPlayer(currentPlayer);

		if (!(currentStreet instanceof Street)) {
			throw new AssertionError(
					"Current player is not standing on a street!");
		}

		updateGameStatus(GameStatus.DURING_TURN);
		// TODO notify observer?
		return field.buyStreet(currentPlayer, (Street) currentStreet);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endTurn() {
		this.message.delete(0, this.message.length());

		updateGameStatus(GameStatus.AFTER_TURN);
		notifyObservers(GameStatus.AFTER_TURN);

		this.currentPlayer = players.getNextPlayer();

		if (currentPlayer.isInPrison()) {
			updateGameStatus(GameStatus.BEFORE_TURN_IN_PRISON);
			notifyObservers(GameStatus.BEFORE_TURN_IN_PRISON);
		} else {
			updateGameStatus(GameStatus.BEFORE_TURN);
			notifyObservers(GameStatus.BEFORE_TURN);
		}

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

			updateGameStatus(GameStatus.BEFORE_TURN);
			return true;
		}
		return false;

		// TODO notify Observers?
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

		updateGameStatus(GameStatus.BEFORE_TURN);
		// TODO notify Observers?
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
		throw new UnsupportedOperationException("not implemented yet");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollDice() {
		/* throw dice */
		dice.throwDice();
		updateGameStatus(GameStatus.DICE_RESULT);
		notifyObservers(GameStatus.DICE_RESULT);
	}

	/**
	 * End of game function
	 */
	@Override
	public void exitGame() {
		this.players = null;
		updateGameStatus(GameStatus.STOPPED);
		notifyObservers(GameStatus.STOPPED);
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

	/**
	 * Updates the phase, in which the game is and updates the user options.
	 * 
	 * @param phaseToSet
	 */
	private void updateGameStatus(GameStatus phaseToSet) {
		phase = phaseToSet;
		userOptions.update();
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

	
	@Override
	public int getFieldSize() {
		return field.getfieldSize();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserAction> getOptions() {
		return userOptions.getCurrentPlayerOptions();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCorrectOption(UserAction userOption) {
		return userOptions.getCurrentPlayerOptions().contains(userOption);
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
		return field.getFieldOfPlayer(currentPlayer);
	}

	@Override
	public void performAction(UserAction choosedOption) {
		// TODO Auto-generated method stub

	}

	@Override
	public IFieldObject getFieldAtIndex(int i) {
		return field.getFieldAtIndex(i);
	}

	/**
	 * for TESTCASES - set current field
	 */
	void setCurrentField(IFieldObject currentField) {
		this.currentField = currentField;
	}

	// private void notify(GameStatus inStatus) {
	// status = inStatus;
	// event.setStatus(inStatus);
	// notifyObservers(event);
	//
	// }
}
