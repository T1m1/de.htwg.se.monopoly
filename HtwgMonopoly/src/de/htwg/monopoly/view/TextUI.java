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

	private IController controller;

	public void startGame() {
		printInitialisation();
		logger.info(IMonopolyUtil.start);
		controller.initGame(4); // <-- noch ist das Feld nur 2 groß!!
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
			onField();
			printAction();
			printOptions(2);
			
		} else {
			printTUI();
			startTurn();
		}
		
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
			sb.append(option);
			sb.append("\n");

		}
		logger.info(sb.toString());
	}
	public void printAction() {
		logger.info(controller.getMessage());
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
		for (String option : controller.getOptions(1)) {
			sb.append(option);
			sb.append("\n");

		}
		logger.info(sb.toString());

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
					+ player.getOwnership()+"\n");
		}

		sb.append("\nSpielfeld [--------------------]");
		for (int i = 0; i < controller.getField().getfieldSize(); i++) {
			// TODO GUI zeichnen
		}

		logger.info(sb.toString());

	}

	/**
	 * TODO: evtl. auf Java 6 umbauen? Nötig?
	 * @param line
	 * @return
	 */
	public boolean processInputLine(String line) {
		boolean status = true;
		switch (line) {
		case "d":
			// roll dice
			controller.startTurn();
			break;
		case "b":
			// zug beenden
			controller.endTurn();
			printTUI();
			startTurn();
			break;
		case "x":
			status = false;
			break;
		case "y":
			if(controller.buyStreet()){
				System.out.println("Erfolgreich gekauft!");
			} else {
				System.out.println("Du hast nicht genug Geld :P");
			}
			controller.endTurn();
			printTUI();
			startTurn();
			break;
		case "n":
			break;
		default:
			System.out.println("Wrong Input!");
		}
		return status;

	}



}
