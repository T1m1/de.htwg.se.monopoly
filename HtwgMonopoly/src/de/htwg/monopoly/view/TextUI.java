package de.htwg.monopoly.view;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.MonopolyUtils;
import de.htwg.monopoly.util.UserAction;

public class TextUI implements IObserver {

	/* logger */
	private final Logger logger = LogManager.getLogger("htwgMonopoly");
	private Scanner in;

	/* internationalization */
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	private IController controller;

	private int configNumberOfPlayer;
	private String[] configNameOfPlayer;

	private static Map<String, UserAction> userOption = new HashMap<String, UserAction>();

	static {
		userOption.put("d", UserAction.START_TURN);
		userOption.put("x", UserAction.SURRENDER);
		userOption.put("b", UserAction.END_TURN);
		userOption.put("y", UserAction.BUY_STREET);
		userOption.put("f", UserAction.REDEEM_WITH_MONEY);
		userOption.put("r", UserAction.ROLL_DICE);
		userOption.put("c", UserAction.REDEEM_WITH_CARD);
		userOption.put("w", UserAction.REDEEM_WITH_DICE);
	}
	
	public TextUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	public void startGame() {

		// print Hello screen
		printInitialisation();

		// read number and name of players
		in = new Scanner(System.in);
		if (!in.next().isEmpty()) {
			setNumberOfPlayer();
			setNameOfPlayers();
		}

		logger.info(IMonopolyUtil.START);

		// start actual game
		controller.startNewGame(configNumberOfPlayer, configNameOfPlayer);
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
			
			break;
		case STARTED:
			printTUI();
			printOptions();
			break;
		case BEFORE_TURN:
			printMessage();
			printOptions();
			break;
		case BEFORE_TURN_IN_PRISON:
			printMessage();
			printOptions();
			break;
		case DURING_TURN:
			onField();
			printMessage();
			printOptions();
			break;
		case AFTER_TURN:
			printTUI();
			break;
		case DICE_RESULT:
			printRoll();
			break;
		}

	}

	@Override
	public void update(int e) {
		throw new UnsupportedOperationException("not supported!!");
	}



	/**
	 * print information about dice
	 */
	private void printRoll() {
		int diceResult = controller.getDice().getResultDice()
				% (controller.getField().getfieldSize() + 1);
		String out = MessageFormat.format(bundle.getString("tui_dice"),
				diceResult);
		logger.info(out);
	}

	public void onField() {
		String currentFile = controller.getField()
				.getCurrentField(controller.getCurrentPlayer()).toString();
		String out = MessageFormat.format(bundle.getString("tui_playfield"),
				currentFile);
		logger.info(out);
	}

	private void printOptions() {
		StringBuilder sb = new StringBuilder();
		sb.append(bundle.getString("tui_options"));

		for (UserAction currentOption : controller.getOptions()) {
			sb.append("\n");
			sb.append("(");
			sb.append(getOptionChar(currentOption));
			sb.append(") - ");
			sb.append(currentOption.getDescription());

		}
		logger.info(sb.toString());
	}

	public void printMessage() {
		logger.info(controller.getMessage());
	}

	/**
	 * function to read number of player
	 */
	public int readNumberOfPlayer() {

		int tmpNumberOfPlayer = 0;

		if (in.hasNext()) {
			/* check if input an integer and in right interval */
			if (in.hasNextInt()) {
				tmpNumberOfPlayer = in.nextInt();
				in.nextLine();
			} else {
				/* TODO: alles weg */
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

	private void setNumberOfPlayer() {
		logger.info(IMonopolyUtil.Q_NUMBER_OF_PLAYER);
		int status = readNumberOfPlayer();
		while (status == 0) {
			logger.info(IMonopolyUtil.ERR_NUMBER_OF_PLAYER);
			status = readNumberOfPlayer();
		}
		this.configNumberOfPlayer = status;
	}

	private void setNameOfPlayers() {
		this.configNameOfPlayer = new String[configNumberOfPlayer];
		for (int i = 0; i < this.configNumberOfPlayer; i++) {
			logger.info("Player " + (i + 1) + " " + IMonopolyUtil.Q_NAME_PLAYER);
			if (in.hasNext()) {
				this.configNameOfPlayer[i] = in.nextLine();
			}

		}
	}

	/**
	 * print TUI
	 */
	private void printTUI() {
		/* TODO: Ausgabe formatieren */
		StringBuilder sb = new StringBuilder();
		StringBuilder streets = new StringBuilder();

		sb.append("\n_________________________________\n");
		sb.append(bundle.getString("player") + "\t|Budget\t|"
				+ bundle.getString("ownership") + "\n");
		sb.append("-------\t|------\t|--------------\n");
		for (int i = 0; i < controller.getNumberOfPlayer(); i++) {

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
			for (int i = 0; i < controller.getField().getfieldSize(); i++) {
				if (zeile == 1) {
					zeichen[1] = zeichen[1].replace(x, "" + i);
					x = "" + i;
				}
				sb.append(zeichen[zeile]);
			}
			sb.append("|");
		}
		for (int i = 0; i < controller.getField().getfieldSize(); i++) {
			streets.append(i).append("=")
					.append(controller.getField().getFieldNameAtIndex(i))
					.append("\n");
		}

		sb.append("\n").append(streets);
		logger.info(sb.toString());

	}

	/**
	 * handle user input
	 *
	 * @param line
	 * @return
	 */
	public boolean processInputLine(String line) {
		
		UserAction choosedOption = userOption.get(line);
		
		if (choosedOption == null) {
			// wrong input, option not mapped.
			logger.info(bundle.getString("tui_wrong_input"));
			return true;
		}
		
		if (!controller.isCorrectOption(choosedOption)){
			// wrong input, option not available
			logger.info(bundle.getString("tui_wrong_input"));
			return true;
		}
		
		// TODO: needs to be implemented: controller.performAction(choosedOption);
		
		boolean status = true;
	
		switch (choosedOption) {
		case START_TURN:
			// roll dice
			controller.startTurn();
			break;
		case END_TURN:
			// zug beenden
			controller.endTurn();
			break;
		case SURRENDER:
			// TODO for now the game finishes
			status = false;
			break;
		case BUY_STREET:
			if (controller.buyStreet()) {
				logger.info(bundle.getString("tui_buy"));
			} else {
				logger.info(bundle.getString("tui_no_money"));
			}
			controller.endTurn();
			break;
		case REDEEM_WITH_MONEY:
			if (!controller.redeemWithMoney()) {
				logger.info("Nicht gut Geld zum Freikaufen.");
			}
			break;
		case REDEEM_WITH_CARD:
		case REDEEM_WITH_DICE:
		case ROLL_DICE:
		default:
			
			logger.info("not implemented yet.");
			break;
		
		}
		return status;
	}

	/**
	 * finds the char to the corresponding enum value.
	 * 
	 * TODO : Find solution for finding the key to a specific value, since all
	 * values are unique too.
	 * 
	 * @param Option
	 *            An Option in the map.
	 * @return the key for the value
	 */
	private String getOptionChar(UserAction Option) {
	
		for (String currentChar : userOption.keySet()) {
			if (userOption.get(currentChar) == Option) {
				return currentChar;
			}
		}
	
		throw new AssertionError("Wrong Option");
	}
}
