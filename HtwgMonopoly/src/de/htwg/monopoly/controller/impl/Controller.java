package de.htwg.monopoly.controller.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.FieldType;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.MonopolyUtils;
import de.htwg.monopoly.util.PlayerIcon;
import de.htwg.monopoly.util.UserAction;

/**
 * 
 * @author RuprechtT, GorenfloS
 * 
 */
public class Controller extends Observable implements IController {
	private IPlayerController players;
	private Playfield field;
	private Player currentPlayer;
	private IFieldObject currentField;
	private Dice dice;
	private int fieldSize;

	private GameStatus phase;
	private UserOptionsController userOptions;

	private StringBuilder message;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);
	private int diceFlag;
	private String currentPrisonQuestion;
	private PrisonQuestion questions;
	private boolean drawCardFlag;

	/**
	 * public constructor for a new controller create the players, the field and
	 * the dice
	 * 
	 * @param fieldSize
	 */
	@Inject
	public Controller(@Named("FieldSize") int fieldSize) {
		phase = GameStatus.NOT_STARTED;
		this.fieldSize = fieldSize;

		this.message = new StringBuilder();
		this.dice = new Dice();
		this.userOptions = new UserOptionsController(this);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNewGame(List<String> players) {

		if (MonopolyUtils.verifyPlayerNumber(players.size()) == false) {
			message.append("Wrong number of players!!");
			updateGameStatus(GameStatus.NOT_STARTED);
			return;
		}

		// create new field and player
		this.field = new Playfield(fieldSize);
		this.players = new PlayerController(players);
		this.questions = new PrisonQuestion();
		currentPrisonQuestion = questions.getNextQuestion();

		// set current player to first player, notify observers and get ready to
		// play
		this.currentPlayer = this.players.getFirstPlayer();

		updateGameStatus(GameStatus.STARTED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNewGame(Map<String, PlayerIcon> players) {
		// verify the number of players
		if (MonopolyUtils.verifyPlayerNumber(players.size()) == false) {
			message.append("Wrong number of players!!");
			updateGameStatus(GameStatus.NOT_STARTED);
			return;
		}

		// create new field and player
		this.field = new Playfield(fieldSize);
		this.players = new PlayerController(players);
		this.questions = new PrisonQuestion();
		currentPrisonQuestion = questions.getNextQuestion();

		// set current player to first player, notify observers and start
		// playing
		this.currentPlayer = this.players.getFirstPlayer();
		
		clearMessage();
		message.append("Spiel gestartet!");
		updateGameStatus(GameStatus.STARTED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startTurn() {
		clearMessage();

		dice.throwDice();
		notifyObservers(GameStatus.DICE_RESULT);

		/* move player -> max number to dice is fieldSize */
		field.movePlayer(currentPlayer, dice.getResultDice());

		// set the current field
		this.currentField = field.getFieldOfPlayer(currentPlayer);

		// perform the action, depending on the field.
		if (currentField.getType() == FieldType.COMMUNITY_STACK) {
			message.append("Du bist auf einem Gemeinschaftsfeld gelandet. Ziehe eine Karte");
			drawCardFlag = false;
		} else if (currentField.getType() == FieldType.CHANCE_STACK) {
			message.append("Du bist auf einem Ereignisfeld gelandet. Ziehe eine Karte");
			drawCardFlag = false;
		} else {
			message.append(field.performActionAndAppendInfo(currentField,
					currentPlayer));
		}

		updateGameStatus(GameStatus.DURING_TURN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean buyStreet() {
		clearMessage();
		IFieldObject currentStreet = field.getFieldOfPlayer(currentPlayer);

		if (!(currentStreet.getType() == FieldType.STREET)) {
			throw new AssertionError(
					"Current player is not standing on a street!");
		}

		boolean status = field.buyStreet(currentPlayer, (Street) currentStreet);

		// add result of street buying to message.
		if (status == true) {
			message.append(bundle.getString("tui_buy"));
		} else {
			message.append(bundle.getString("tui_no_money"));
		}

		updateGameStatus(GameStatus.DURING_TURN);
		return status;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endTurn() {
		clearMessage();

		updateGameStatus(GameStatus.AFTER_TURN);

		this.currentPlayer = players.getNextPlayer();

		if (this.currentPlayer.isInPrison()) {
			currentPlayer.incrementPrisonRound();
		}

		if (currentPlayer.isInPrison()) {
			message.append("Du bist im Gefängnis");
			updateGameStatus(GameStatus.BEFORE_TURN_IN_PRISON);
		} else {
			updateGameStatus(GameStatus.BEFORE_TURN);
		}

	}

	/**
	 * Tries to redeem the current player with a prison free card.
	 * 
	 * @return true if the player had a card and was set free, false otherwise
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean redeemWithCard() {
		clearMessage();
		if (currentPlayer.hasPrisonFreeCard()) {
			currentPlayer.usePrisonFreeCard();
			currentPlayer.setInPrison(false);

			message.append("Erfolgreich frei gekommen.");
			updateGameStatus(GameStatus.BEFORE_TURN);
			return true;
		}

		message.append("Keine Karte im Besitz");
		updateGameStatus(GameStatus.BEFORE_TURN_IN_PRISON);
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
		clearMessage();
		if (currentPlayer.getBudget() < IMonopolyUtil.FREIKAUFEN) {

			message.append("Kein Geld zum Freikaufen!");

			updateGameStatus(GameStatus.BEFORE_TURN_IN_PRISON);
			return false;
		}
		currentPlayer.decrementMoney(IMonopolyUtil.FREIKAUFEN);
		currentPlayer.setInPrison(false);

		message.append("Erfolgreich freigekauft!");
		updateGameStatus(GameStatus.BEFORE_TURN);
		return true;
	}

	@Override
	public boolean checkPlayerAnswer(boolean answer) {
		
		clearMessage();
		
		if (questions.isTrue(currentPrisonQuestion, answer)) {

			currentPrisonQuestion = questions.getNextQuestion();
			
			currentPlayer.setInPrison(false);
			message.append("Frage korrekt beantwortet.");
			updateGameStatus(GameStatus.BEFORE_TURN);
			return true;
		} else {
			currentPrisonQuestion = questions.getNextQuestion();
			endTurn();
			return false;
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean redeemWithDice() {
		clearMessage();
		diceFlag = IMonopolyUtil.TIMES_TO_ROLL_DICE;
		message.append("Versuchen sie bei 3 W&uuml;rfen einen Pasch zu w&uuml;rfeln.");
		updateGameStatus(GameStatus.DICE_ROLL_FOR_PRISON);
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollDiceToRedeem() {
		clearMessage();

		dice.throwDice();
		diceFlag--;

		notifyObservers(GameStatus.DICE_RESULT);

		if (dice.isPasch()) {
			currentPlayer.setInPrison(false);
			message.append("Erfolgreich frei gekommen");
			updateGameStatus(GameStatus.BEFORE_TURN);
			return;
		}

		if (diceFlag < 1) {
			// TODO: maybe end turn of player, so he doesn't need to do it himself
			message.append("Leider 3 mal kein Pasch gew&uuml;rfelt. Der N&auml;chste ist dran.");
		} else {
			message.append("Leider kein Pasch gew&uuml;rfelt. Noch " + diceFlag
					+ " Versuch(e).");
		}
		updateGameStatus(GameStatus.DICE_ROLL_FOR_PRISON);
	}
	
	@Override
	public void drawCard() {
		clearMessage();
		drawCardFlag = true;
		if (currentField.getType() == FieldType.COMMUNITY_STACK) {
			ICards currentChanceCard = field.getCommStack().getNextCard();
			message.append(performCardAction(currentChanceCard));
		} else if (currentField.getType() == FieldType.CHANCE_STACK) {
			ICards currentChanceCard = field.getChanStack().getNextCard();
			message.append(performCardAction(currentChanceCard));
		}
		
		updateGameStatus(GameStatus.DURING_TURN);
		
	}

	/**
	 * End of game function
	 */
	@Override
	public void exitGame() {
		// "delete" players and playfield
		this.players = null;
		this.field = null;

		updateGameStatus(GameStatus.STOPPED);
	}

	/**
	 * Perform the action according to the text on the card. It depends if the
	 * player has to move or money is transferred.
	 * 
	 * @return
	 */
	private String performCardAction(ICards currentCard) {
		StringBuilder sb = new StringBuilder();
		sb.append(MessageFormat.format(bundle.getString("play_card"),
				currentCard.getDescription()));

		if (isMoveAction(currentCard)) {
			sb.append(field.movePlayerTo(currentPlayer, currentCard.getTarget()));
		} else {
			players.transferMoney(currentPlayer, currentCard);
		}
		return sb.toString();
	}

	/**
	 * Check if the Card is a "player move Card" or a "money transfer card". In
	 * particular: Is the target a string or an integer
	 * 
	 * @param card
	 * @return false if the target (of the card) is an integer, true otherwise.
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
		notifyObservers(phase);
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
	public int getNumberOfPlayers() {
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
		throw new UnsupportedOperationException("not implemented yet!");

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

	private void clearMessage() {
		message.delete(0, message.length());
	}

	boolean isDiceFlagSet() {
		return diceFlag != 0;
	}

	@Override
	public IPlayfield getField() {
		return this.field;
	}

	@Override
	public String getPrisonQuestion() {
		return currentPrisonQuestion;
	}


	public boolean hasDrawnCard() {
		return drawCardFlag;
	}
}
