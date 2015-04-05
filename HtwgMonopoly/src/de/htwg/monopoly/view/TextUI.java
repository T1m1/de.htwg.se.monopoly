package de.htwg.monopoly.view;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.MonopolyUtils;
import de.htwg.monopoly.util.UserAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TextUI implements IObserver {

	/* logger */
	private final Logger logger = LogManager.getLogger("htwgMonopoly");
	private Scanner in;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	/* THE controller */
	private IController controller;
	
	private boolean debug = true;

	// Bidirectional map for user input and enum actions
	private final static BiMap<String, UserAction> ENUM_USER_OPTION = HashBiMap
			.create();
	private final static BiMap<UserAction, String> CHAR_USER_OPTION;

	static {
		ENUM_USER_OPTION.put("d", UserAction.START_TURN);
		ENUM_USER_OPTION.put("x", UserAction.SURRENDER);
		ENUM_USER_OPTION.put("b", UserAction.END_TURN);
		ENUM_USER_OPTION.put("y", UserAction.BUY_STREET);
		ENUM_USER_OPTION.put("f", UserAction.REDEEM_WITH_MONEY);
		ENUM_USER_OPTION.put("r", UserAction.ROLL_DICE);
		ENUM_USER_OPTION.put("c", UserAction.REDEEM_WITH_CARD);
		ENUM_USER_OPTION.put("w", UserAction.REDEEM_WITH_DICE);

		CHAR_USER_OPTION = ENUM_USER_OPTION.inverse();
	}

	public TextUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	public void startGame() {

		// print Hello screen
		printInitialisation();

		// read number and name of players
		String[] playerArray = null;
		in = new Scanner(System.in);
		if (!in.next().isEmpty()) {
			int number = setNumberOfPlayer();
			playerArray = setNameOfPlayers(number);
		}

		logger.info(IMonopolyUtil.START);

		// start actual game
		controller.startNewGame(Arrays.asList(playerArray));
	}

	public void printInitialisation() {
		logger.info(IMonopolyUtil.GAME_NAME);
		logger.info("Herzlich Willkommen zu Monopoly!");
		logger.info("Um das Spiel zu starten, beliebigen Wert eingeben und bestätigen.");
	}

	@Override
	public void update(GameStatus phase) {
		switch (phase) {
		case STOPPED:
			logger.info("Game is stopped.");
			break;
		case STARTED:
			printTUI();
			logger.info("Spieler " + controller.getCurrentPlayer()
					+ ". Sie sind an der Reihe.");
			printOptions();
			break;
		case BEFORE_TURN:
		case BEFORE_TURN_IN_PRISON:
			printMessage();
			printOptions();
			break;
		case DURING_TURN:
			printMessage();
			onField();
			printOptions();
			break;
		case AFTER_TURN:
			printTUI();
			logger.info("Spieler " + controller.getCurrentPlayer()
					+ ". Sie sind an der Reihe.");
			break;
		case DICE_RESULT:
			printRoll();
			break;
		case DICE_ROLL_FOR_PRISON:
			printMessage();
			printOptions();
			break;
		}

	}

	/**
	 * print information about dice
	 */
	private void printRoll() {
		String out = MessageFormat.format(bundle.getString("tui_dice"),
				controller.getDice().getDice1(), controller.getDice()
						.getDice2());
		logger.info(out);
	}

	/**
	 * Print information, where the player is currently standing on.
	 */
	public void onField() {
		String currentFile = controller.getCurrentField().toString();
		String out = MessageFormat.format(bundle.getString("tui_playfield"),
				currentFile);
		logger.info(out);
	}

	/**
	 * Print the options, which are available for the current player.
	 */
	private void printOptions() {
		StringBuilder sb = new StringBuilder();
		sb.append(bundle.getString("tui_options"));

		for (UserAction currentOption : controller.getOptions()) {
			sb.append("\n");
			sb.append("(");
			sb.append(CHAR_USER_OPTION.get(currentOption));
			sb.append(") - ");
			sb.append(currentOption.getDescription());

		}
		logger.info(sb.toString());
	}

	/**
	 * Print the event message from the controller, which describes the current
	 * state of the game.
	 */
	public void printMessage() {
		logger.info(controller.getMessage());
	}

	/**
	 * print the game field and its properties
	 */
	private void printTUI() {
		StringBuilder sb = new StringBuilder();
		StringBuilder streets = new StringBuilder();

		sb.append("\n_________________________________\n");
		sb.append(bundle.getString("player") + "\t|Budget\t|"
				+ bundle.getString("ownership") + "\n");
		sb.append("-------\t|------\t|--------------\n");
		for (int i = 0; i < controller.getNumberOfPlayers(); i++) {

			Player player = controller.getPlayer(i);
			sb.append(player.getName() + "\t|" + player.getBudget() + "\t|"
					+ player.getOwnership() + "\n");
		}

		int z = IMonopolyUtil.TUI_HIGH;
		String[] zeichen = new String[z];
		z = 0;
		zeichen[z] = "|-------";
		zeichen[++z] = "|___x___";
		zeichen[++z] = "|       ";
		zeichen[++z] = "|_______";

		String x = "x";
		for (int zeile = 0; zeile < zeichen.length - 1; zeile++) {
			sb.append("\n");
			for (int i = 0; i < controller.getFieldSize(); i++) {
				if (zeile == 1) {
					zeichen[1] = zeichen[1].replace(x, "" + i);
					x = "" + i;
				}
				sb.append(zeichen[zeile]);
			}
			sb.append("|");
		}
		for (int i = 0; i < controller.getFieldSize(); i++) {
			streets.append(i).append("=")
					.append(controller.getFieldAtIndex(i).toString())
					.append("\n");
		}

		sb.append("\n").append(streets);
		logger.info(sb.toString());

	}

	/**
	 * handle user input
	 *
	 * @param line
	 *            a char indicating the option 
	 * @return false, if the player has ended the game ('x'), true otherwise.
	 */
	public boolean processInputLine(String line) {

		UserAction choosedOption = ENUM_USER_OPTION.get(line);

		if (choosedOption == null) {
			// wrong input, option not mapped.
			logger.info(bundle.getString("tui_wrong_input"));
			return true;
		}

		if (!controller.isCorrectOption(choosedOption)) {
			// wrong input, option not available
			logger.info(bundle.getString("tui_wrong_input"));
			return true;
		}

		// controller.performAction(choosedOption);

		switch (choosedOption) {
		case START_TURN:
			controller.startTurn();
			break;
		case END_TURN:
			controller.endTurn();
			break;
		case BUY_STREET:
			controller.buyStreet();
			break;
		case REDEEM_WITH_MONEY:
			controller.redeemWithMoney();
			break;
		case REDEEM_WITH_CARD:
			controller.redeemWithCard();
			break;
		case REDEEM_WITH_DICE:
			controller.redeemWithDice();
			break;
		case ROLL_DICE:
			controller.rollDiceToRedeem();
			break;

		// for now the game finishes completely
		case SURRENDER:
			logger.info("Spiel beendet!");
			controller.exitGame();
			return false;
		case DRAW_CARD:
			//TODO: implement functionality
			throw new UnsupportedOperationException("not implemented yet");
		case REDEEM_WITH_QUESTION:
			//TODO: implement functionality
			throw new UnsupportedOperationException("not implemented yet");
		default:
			break;
		}
		return true;
	}

	/**
	 * function to read number of player
	 */
	private int readNumberOfPlayer() {

		int tmpNumberOfPlayer = 0;

		if (in.hasNext()) {
			/* check if input an integer and in right interval */
			if (in.hasNextInt()) {
				tmpNumberOfPlayer = in.nextInt();
				in.nextLine();
			} else {
				in.nextLine();
				return 0;
			}
		}

		/* check if input smaller as maximum of player and bigger as minimum */
		if (MonopolyUtils.verifyPlayerNumber(tmpNumberOfPlayer) == false) {
			return 0;
		}

		/* if scanned number correct, save it */
		return tmpNumberOfPlayer;
	}

	private int setNumberOfPlayer() {
		logger.info(IMonopolyUtil.Q_NUMBER_OF_PLAYER);
		int readNumber = readNumberOfPlayer();
		while (readNumber == 0) {
			logger.info(IMonopolyUtil.ERR_NUMBER_OF_PLAYER);
			readNumber = readNumberOfPlayer();
		}
		return readNumber;
	}

	private String[] setNameOfPlayers(int numberOfPlayer) {
		String[] configNameOfPlayer = new String[numberOfPlayer];
		for (int i = 0; i < numberOfPlayer; i++) {
			logger.info("Player " + (i + 1) + " " + IMonopolyUtil.Q_NAME_PLAYER);
			if (in.hasNext()) {
				configNameOfPlayer[i] = in.nextLine();
			}

		}
		return configNameOfPlayer;
	}

	@Override
	public void update(int e) {
		throw new UnsupportedOperationException("not supported!!");
	}
	
	private void debug(String info) {
		if (debug) {
			logger.info(info);
		}
		
	}
}
