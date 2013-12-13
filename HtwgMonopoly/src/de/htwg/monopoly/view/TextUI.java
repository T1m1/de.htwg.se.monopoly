package de.htwg.monopoly.view;

import java.util.Scanner;
import java.util.logging.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Player;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUI implements IObserver {

	private Logger logger = Logger.getLogger("de.htwg.monopoly.view.tui");
	private Scanner in;

	private IController controller;

	public void startGame() {
		printInitialisation();
		in = new Scanner(System.in);
		logger.info(IMonopolyUtil.start);
		controller.initGame(2); // <-- noch ist das Feld nur 2 groß!!
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

	public void onField() {
		logger.info("Sie sind auf dem Spielfeld: "
				+ controller.getField().getCurrentField(
						controller.getCurrentPlayer()) + " gelandet.");
		
	}

	public void printInitialisation() {
		logger.info(IMonopolyUtil.gameName);
		setNumberOfPlayer();
		setNameOfPlayers();
	}

	public void startTurn() {
		StringBuilder sb = new StringBuilder();
		sb.append("Spieler " + controller.getCurrentPlayer().getName()
				+ " sie sind dran.\n");
		sb.append("Sie haben folgende Optionen:\n");
		for (String option : controller.getOptions()) {
			sb.append(option);
			sb.append("\n");

		}
		logger.info(sb.toString());

//		String line;
//		boolean status = true;
//		while (status) {
//			System.out.println("huhu");
//			try {
//				if (in.hasNext()) {
//					line = in.nextLine();
//					char[] choose = line.toCharArray();
//					char c = choose[0];
//					switch (c) {
//					case 'd':
//						controller.startTurn();
//						status = false;
//						break;
//					case 'x':
//						return;
//					default:
//						System.out.println("wrong input: try again\n");
//					}
//				}
//
//			} catch (Exception e) {
//
//			}
//		}
//		System.out.println("hallo");

	}

	private void setNumberOfPlayer() {
		logger.info(IMonopolyUtil.qNumberOfPlayer);
		while (!controller.setNumberofPlayer()) {
			logger.info(IMonopolyUtil.errNumberOfPlayer);
		}
	}

	private void setNameOfPlayers() {
		for (int i = 0; i < controller.getPlayers().getNumberOfPlayer(); i++) {
			logger.info("Player " + (i + 1) + " " + IMonopolyUtil.qNamePlayer);
			while (!controller.setNameofPlayer(i)) {
				logger.info(IMonopolyUtil.errNameOfPlayer);
			}
		}

	}

	private void printTUI() {
		/* TODO: Ausgabe formatieren */
		StringBuilder sb = new StringBuilder();
		sb.append("\n_________________________________\n");
		sb.append("Spieler\t|Budget\t|Besitz\n");
		sb.append("-------\t|------\t|--------------\n");
		for (int i = 0; i < controller.getPlayers().getNumberOfPlayer(); i++) {
			Player player = controller.getPlayers().getPlayer(i);
			sb.append(player.getName() + "\t|" + player.getBudget() + "\t|"
					+ "[Strassen...]\n");
		}
		
//		for(int i; i < controller.getField())
//		sb.append("\nSpielfeld [--------------------]");
//		logger.info(sb.toString());

	}

}
