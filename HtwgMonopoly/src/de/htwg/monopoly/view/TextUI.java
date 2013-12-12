package de.htwg.monopoly.view;

import java.util.logging.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUI implements IObserver {
	
	private Logger logger = Logger.getLogger("de.htwg.monopoly.view.tui");

	private IController controller;
	
	public void startGame() {
		printInitialisation();
		logger.info(IMonopolyUtil.start);
		controller.startNewGame();
	}
	
	
	public TextUI(IController controller) {
		this.controller = controller;
		controller.addObserver(this);
	}
	
	@Override
	public void update(Event e) {
		printTUI();		
	}	

	public void printInitialisation() {
		logger.info(IMonopolyUtil.gameName);
		readNumberOfPlayer();
		controller = new Controller(controller.getPlayers().getNumberOfPlayer()); // geht nicht, da es immer das gleiche controller object für gui und tui sein muss. Deshalb kann man nciht auf halber strecke ein neuen controller erstellen
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

	private void printTUI() {

		System.out.println("NOTIFYYYYYY!!!!");
		
	}

}
