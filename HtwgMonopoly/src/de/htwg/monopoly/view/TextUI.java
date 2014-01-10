package de.htwg.monopoly.view;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUI implements IObserver {

	/* logger */
	private final Logger logger = LogManager.getLogger("htwgMonopoly");

	/* internationalization */ 
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	private IController controller;

	public void startGame() {

		/*TODO ist glaub nicht gut die anzahl der spieler hier einzulesen... 
		 * namen etc. müssen ja auch in GUI änderbar sein.
		 * 
		 * Lösung: in der GUI verlange, dass Spieleranzahl in den Optionen angegeben werden muss oder so
		 * 
		 * */
		printInitialisation();
		logger.info(IMonopolyUtil.START);
//		controller.initGame(IMonopolyUtil.TUI_FIELD_SIZE);
		// TODO start -> random player
		controller.startNewGame();
	}

	public TextUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}

	@Override
	public void update(Event e) {
		printTUI();
		startTurn();
	}

	@Override
	public void update(int e) {
		if (e == 1) {
			printRoll();
			onField();
			printAction();
			printOptions(2);

		}
		if (e == 0) {
			printTUI();
			startTurn();
		}
	}

	/**
	 * print information about dice
	 */
	private void printRoll() {
		int diceResult = Dice.getResultDice()
				% controller.getField().getfieldSize() + 1;
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

	private void printOptions(int choose) {
		StringBuilder sb = new StringBuilder();
		sb.append(bundle.getString("tui_options"));
		for (String option : controller.getOptions(choose)) {
			sb.append("\n");
			sb.append(option);
		}
		logger.info(sb.toString());
	}

	public void printAction() {
		logger.info(controller.getMessage());
	}

	public void printInitialisation() {
		logger.info(IMonopolyUtil.GAME_NAME);
		setNumberOfPlayer();
		setNameOfPlayers();
	}

	public void startTurn() {
		StringBuilder sb = new StringBuilder();
		String currentPlayer = controller.getCurrentPlayer().getName();

		sb.append(MessageFormat.format(bundle.getString("tui_player"),
				currentPlayer));

		sb.append(bundle.getString("tui_options"));
		for (String option : controller.getOptions(1)) {
			sb.append("\n");
			sb.append(option);

		}
		logger.info(sb.toString());

	}

	private void setNumberOfPlayer() {
		logger.info(IMonopolyUtil.Q_NUMBER_OF_PLAYER);
		while (!controller.setNumberofPlayer()) {
			logger.info(IMonopolyUtil.ERR_NUMBER_OF_PLAYER);
		}
	}

	private void setNameOfPlayers() {
		for (int i = 0; i < controller.getNumberOfPlayer(); i++) {
			logger.info("Player " + (i + 1) + " " + IMonopolyUtil.Q_NAME_PLAYER);
			while (!controller.setNameofPlayer(i)) {
				logger.info(IMonopolyUtil.ERR_NAME_OF_PLAYER);
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
	 * @param line
	 * @return
	 */
	public boolean processInputLine(String line) {
		boolean status = true;
		char[] l = line.toCharArray();
		if (!controller.isCorrectOption(line)) {
			logger.info(bundle.getString("tui_wrong_input"));
			return status;
		}
		switch (l[0]) {
		case 'd':
			// roll dice
			controller.startTurn();
			break;
		case 'b':
			// zug beenden
			controller.endTurn();
			printTUI();
			startTurn();
			break;
		case 'x':
			status = false;
			break;
		case 'y':
			if (controller.buyStreet()) {
				logger.info(bundle.getString("tui_buy"));
			} else {
				logger.info(bundle.getString("tui_no_money"));
			}
			controller.endTurn();
			printTUI();
			startTurn();
			break;
		case 'n':
			controller.endTurn();
			printTUI();
			startTurn();
			break;
		case 'f':
			/* temporary */
			/* TODO check if enough money */
			controller.getCurrentPlayer().decrementMoney(
					IMonopolyUtil.FREIKAUFEN);
			controller.getCurrentPlayer().setInPrison(false);
			// zug beenden
			controller.endTurn();
			printTUI();
			startTurn();
			break;
		default:
			logger.info(bundle.getString("tui_wrong_input"));
		}
		return status;
	}
}
