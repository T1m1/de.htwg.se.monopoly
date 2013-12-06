package de.htwg.monopoly.view;

import java.util.logging.Logger;

import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUI {
	private Logger logger = Logger.getLogger("de.htwg.monopoly.view.tui");

	Controller controller;

	public TextUI() {
		controller = new Controller();
		printInitialisation();
		logger.info(IMonopolyUtil.start);
		controller.startNewGame();
	}

	public void printInitialisation() {
		logger.info(IMonopolyUtil.gameName);
		readNumberOfPlayer();
		controller = new Controller(controller.getPlayers().getNumberOfPlayer());
		readNameOfPlayer();
	}

	private void readNumberOfPlayer() {
		logger.info(IMonopolyUtil.qNumberOfPlayer);
		while (true) {
			if (!controller.getPlayers().readNumberOfPlayer()) {
				logger.info(IMonopolyUtil.errNumberOfPlayer);
				continue;
			}
			break;
		}
	}

	private void readNameOfPlayer() {
		for (int i = 0; i < controller.getPlayers().getNumberOfPlayer(); i++) {
			logger.info("Player " + (i + 1) + " " + IMonopolyUtil.qNamePlayer);
			if (!controller.getPlayers().readNameOfPlayer(i)) {
				logger.info(IMonopolyUtil.errNameOfPlayer);
				continue;
			}
		}
	}

}
