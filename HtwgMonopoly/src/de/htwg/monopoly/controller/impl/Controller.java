package de.htwg.monopoly.controller.impl;

import com.google.inject.Inject;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.context.impl.MonopolyGame;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.persistence.IMonopolyDAO;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Bank;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.entities.impl.Street;
import de.htwg.monopoly.factory.IControllerFactory;
import de.htwg.monopoly.observer.impl.Observable;
import de.htwg.monopoly.util.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

/**
 * 
 * @author RuprechtT, GorenfloS
 * 
 */
public class Controller extends Observable implements IController {
	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	// Essential for the game context
	private IPlayerController players;
	private IPlayfield field;
	private GameStatus phase;
	private PrisonQuestion questions;

	private int diceFlag;
	private boolean drawCardFlag;
	private StringBuilder message = new StringBuilder();
	private Dice dice;

	// Not Essential (or can retrieved from the essential ones)
	private Player currentPlayer;
	private IFieldObject currentField;
	private UserOptionsController userOptions;

	// only used before a game is started
	private IControllerFactory factory;

	// Database
	private IMonopolyDAO database;

	/**
	 * public constructor for a new controller create the players, the field and
	 * the dice
	 * 
	 */
	@Inject
	public Controller(IControllerFactory controllerFactory,
			IMonopolyDAO database) {
		phase = GameStatus.NOT_STARTED;

		// set dependencies
		this.factory = controllerFactory;
		this.database = database;

		// create Dice and user controller with factory
		this.dice = factory.createDice();
		this.userOptions = new UserOptionsController(this);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNewGame(List<String> players) {

		clearMessage();

		// verify number
		if (!MonopolyUtils.verifyPlayerNumber(players.size())) {
			message.append("Wrong number of players!!");
			updateGameStatus(GameStatus.NOT_STARTED);
			return;
		}
		// create map with players and random player icons
		Map<String, PlayerIcon> playerMap = MonopolyUtils
				.getPlayersWithIcons(players);

		// start Game
		startNewGame(playerMap);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startNewGame(Map<String, PlayerIcon> players) {
		clearMessage();

		// verify the number of players
		if (!MonopolyUtils.verifyPlayerNumber(players.size())) {
			message.append("Wrong number of players!!");
			updateGameStatus(GameStatus.NOT_STARTED);
			return;
		}

		// create new field, player and prisonquestions with factory
		this.field = factory.createPlayfield();
		this.players = factory.createPlayerController(players);
		this.questions = factory.createPrisonQuestions();

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

		boolean success = currentPlayer.buyStreet((Street) currentStreet);

		// add result of street buying to message.
		if (success) {
			message.append(bundle.getString("tui_buy"));
		} else {
			message.append(bundle.getString("tui_no_money"));
		}

		updateGameStatus(GameStatus.DURING_TURN);
		return success;
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
			message.append("Du bist im Knast");
			updateGameStatus(GameStatus.BEFORE_TURN_IN_PRISON);
		} else {
			updateGameStatus(GameStatus.BEFORE_TURN);
		}

	}

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
			// TODO: maybe end turn of player, so he doesn't need to do it
			// himself
			message.append("Leider 3 mal kein Pasch gew&uuml;rfelt. Der N&auml;chste ist dran.");
		} else {
			message.append("Leider kein Pasch gew&uuml;rfelt. Noch ").append(diceFlag).append(" Versuch(e).");
		}
		updateGameStatus(GameStatus.DICE_ROLL_FOR_PRISON);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkPlayerAnswer(boolean answer) {

		clearMessage();

		if (questions.isTrue(questions.getCurrentQuestion(), answer)) {

			questions.drawNextQuestion();

			currentPlayer.setInPrison(false);
			message.append("Frage korrekt beantwortet.");
			updateGameStatus(GameStatus.BEFORE_TURN);
			return true;
		} else {
			questions.drawNextQuestion();
			endTurn();
			return false;
		}

	}

	/**
	 * {@inheritDoc}
	 */
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
		} else {
			throw new AssertionError(
					"The current field is neither a community card stack nor a chance card stack");
		}

		updateGameStatus(GameStatus.DURING_TURN);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void exitGame() {
		// "delete" players and playfield
		this.players = null;
		this.field = null;
		this.currentField = null;
		this.currentPlayer = null;
		this.questions = null;

		clearMessage();

		updateGameStatus(GameStatus.STOPPED);

	}

	/**
	 * Perform the action according to the text on the card. It depends if the
	 * player has to move or if money is transferred.
	 * 
	 * @return
	 */
	private String performCardAction(ICards currentCard) {
		StringBuilder sb = new StringBuilder();
		sb.append(MessageFormat.format(bundle.getString("play_card"),
				currentCard.getDescription()));

		if (isMoveAction(currentCard)) {
			sb.append(field.movePlayerTo(currentPlayer, currentCard.getTarget()));
			this.currentField = field.getFieldOfPlayer(currentPlayer);
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
			return Math.abs(test) < IMonopolyUtil.MAX_NUMBER_OF_STEPS;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dice getDice() {
		return dice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return this.message.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GameStatus getPhase() {
		return phase;
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFieldObject getFieldAtIndex(int i) {
		return field.getFieldAtIndex(i);
	}

	private void clearMessage() {
		message.delete(0, message.length());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPrisonQuestion() {
		return questions.getCurrentQuestion();
	}

	/**
	 * Package protected method is used for the user options controller.
	 * 
	 * @return
	 */
	boolean hasDrawnCard() {
		return drawCardFlag;
	}

	/**
	 * Package protected method is used for the user options controller.
	 * 
	 * @return
	 */
	boolean isDiceFlagSet() {
		return diceFlag != 0;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalAccessException
	 */
	@Override
	public void saveGameToDB(String name) throws IllegalAccessException {

		// don't save the game if its just not possible
		if (phase.equals(GameStatus.NOT_STARTED)
				|| phase.equals(GameStatus.STOPPED)) {
			throw new IllegalAccessException(
					"Its not possible to save game at this phase of the game.");
		}

		// retrieve and save money from bank
		int parkingMoney = Bank.getParkingMoney();
		Bank.setParkingMoney(parkingMoney);

		// create an instance with all context information of the current game
		IMonopolyGame context = new MonopolyGame(players, field, questions,
				phase, name, parkingMoney, getMessage(), diceFlag,
				drawCardFlag, dice);

		context.makeReady(false);

		// save the game to database
		database.saveGame(context);
		
		context.makeReady(true);

		updateGameStatus(phase);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void loadGameFromDB(String id) {
		IMonopolyGame gameById = database.getGameById(id);

		gameById.makeReady(true);

		// re-initialize all instance values
		this.field = gameById.getPlayfield();
		this.players = gameById.getPlayerController();
		this.phase = gameById.getCurrentGamePhase();
		this.questions = gameById.getPrisonQuestions();
		Bank.setParkingMoney(gameById.getParkingMoney());

		// re-initialize all other values
		this.currentPlayer = players.getCurrentPlayer();
		this.currentField = field.getFieldOfPlayer(currentPlayer);
		this.message = new StringBuilder(gameById.getMessage());
		this.diceFlag = gameById.getDiceFlag();
		this.drawCardFlag = gameById.getDrawCardFlag();
		this.dice = gameById.getDice();

		// restart game
		updateGameStatus(phase);

	}

	@Override
	public Map<String, String> getSavedGames() {
		Map<String, String> savedGames = new TreeMap<String, String>();

		for (IMonopolyGame current : database.getAllGames()) {
			savedGames.put(current.getId(), current.getName());
		}
		return savedGames;
	}

	@Override
	public void deleteGame(String id) {
		assert database.containsGameById(id) : "There is no such game saved in the database";
		database.deleteGameById(id);

		updateGameStatus(phase);
	}

	@Override
	public void cheatAndGetMoney(int money) {
		getCurrentPlayer().incrementMoney(money);
		updateGameStatus(phase);
		
	}

	@Override
	public String[][] getAllSavedGames() {
		List<IMonopolyGame> allGames = database.getAllGames();
		String[][] data = new String[allGames.size()][2];
		
		for (int i = 0; i < allGames.size(); i++) {
			IMonopolyGame current = allGames.get(i);
			data[i][0] = current.getName();
			data[i][1] = current.getId();
		}
		return data;
	}
}
