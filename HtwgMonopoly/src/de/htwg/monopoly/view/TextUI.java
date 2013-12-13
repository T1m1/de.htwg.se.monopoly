package de.htwg.monopoly.view;

import java.util.logging.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.Dice;
import de.htwg.monopoly.observer.Event;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUI implements IObserver {
	
	private Logger logger = Logger.getLogger("de.htwg.monopoly.view.tui");

	private IController controller;
	
	public void startGame() {
		printInitialisation();
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
		System.out.println("<Ich repräsentiere das Feld>");
		System.out.printf("%s hat %d gewürfelt und steht nun auf Feld %d\n", controller.getCurrentPlayer(), Dice.dice1+Dice.dice2, controller.getCurrentPlayer().getPosition());
		System.out.println("Deswegen musst du jetz dieses oder jenes machen....");
		System.out.printf("nachdem du jetzt fertig bist, willste noch was machen? \n");
		
	}
	
	

}
