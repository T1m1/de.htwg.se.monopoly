package de.htwg.monopoly.view;

import java.util.logging.Logger;

import de.htwg.monopoly.controller.IController;
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
		setNumberOfPlayer();
		setNameOfPlayers();
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
			while(!controller.setNameofPlayer(i)){
				logger.info(IMonopolyUtil.errNameOfPlayer);
			}
		}
		
	}

	private void printTUI() {

		System.out.println("NOTIFYYYYYY!!!!");
		
	}
	
	

}
