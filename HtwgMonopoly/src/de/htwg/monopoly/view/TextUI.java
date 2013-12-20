package de.htwg.monopoly.view;

import java.util.logging.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUI implements IObserver {

	private Logger logger = Logger.getLogger("de.htwg.monopoly.view.tui");
	

	private IController controller;

	public void startGame() {

		printInitialisation();
		logger.info(IMonopolyUtil.START);
		controller.initGame(8); // <-- noch ist das Feld nur 2 groß!!
		// print feld? abfragen wer startet? ansonsten gehts los.
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

		} else {
			printTUI();
			startTurn();
		}

	}

	private void printRoll() {
		logger.info("Sie haben " + Dice.resultDice
				% controller.getField().getfieldSize() + " gewürfelt!");

	}

	public void onField() {
		StringBuilder sb = new StringBuilder();
		sb.append("Sie sind auf dem Spielfeld: "
				+ controller.getField().getCurrentField(
						controller.getCurrentPlayer()) + " gelandet.\n");

		logger.info(sb.toString());
	}

	private void printOptions(int choose) {
		StringBuilder sb = new StringBuilder();
		sb.append("Sie haben folgende Optionen:\n");
		for (String option : controller.getOptions(2)) {
			sb.append(option + "\n");
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
		sb.append("Spieler " + controller.getCurrentPlayer().getName()
				+ " sie sind dran.\n");
		sb.append("Sie haben folgende Optionen:\n");
		for (String option : controller.getOptions(1)) {
			sb.append(option);
			sb.append("\n");

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
		for (int i = 0; i < controller.getPlayers().getNumberOfPlayer(); i++) {
			logger.info("Player " + (i + 1) + " " + IMonopolyUtil.Q_NAME_PLAYER);
			while (!controller.setNameofPlayer(i)) {
				logger.info(IMonopolyUtil.ERR_NAME_OF_PLAYER);
			}
		}

	}

	private void printTUI() {
		/* TODO: Ausgabe formatieren */
		StringBuilder sb = new StringBuilder();
		StringBuilder streets = new StringBuilder();
		
		sb.append("\n_________________________________\n");
		sb.append("Spieler\t|Budget\t|Besitz\n");
		sb.append("-------\t|------\t|--------------\n");
		for (int i = 0; i < controller.getPlayers().getNumberOfPlayer(); i++) {
			Player player = controller.getPlayers().getPlayer(i);
			sb.append(player.getName() + "\t|" + player.getBudget() + "\t|"
					+ player.getOwnership() + "\n");
		}

		String[] zeichen = new String[5];
		zeichen[0]="|-------";
		zeichen[1]="|___x___";
		zeichen[2]="|       ";
		zeichen[3]="|_______";
		String x = "x";
		for (int zeile = 0; zeile < zeichen.length-1; zeile++) {
			sb.append("\n");
			for (int i = 0; i < controller.getField().getfieldSize(); i++) {
				if (zeile == 1) {
					zeichen[1] =zeichen[1].replace(x,new Integer(i).toString());
					x =  "" + i; 
				}
					sb.append(zeichen[zeile]);
			}
			sb.append("|");
		}
		for (int i = 0; i < controller.getField().getfieldSize(); i++) {
			streets.append(i).append("=").append(controller.getField().getFieldNameAtIndex(i)).append("\t");
		}
		
		sb.append("\n").append(streets);
		logger.info(sb.toString());

	}

	/**
	 * TODO: evtl. auf Java 6 umbauen? Nötig?
	 * 
	 * @param line
	 * @return
	 */
	public boolean processInputLine(String line) {
		boolean status = true;
		char[] l = line.toCharArray();
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
				System.out.println("Erfolgreich gekauft!");
			} else {
				System.out.println("Du hast nicht genug Geld :P");
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
		default:
			System.out.println("Wrong Input!");
		}
		return status;

	}

}
