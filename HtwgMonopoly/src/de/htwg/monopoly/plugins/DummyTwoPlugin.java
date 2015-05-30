package de.htwg.monopoly.plugins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

public class DummyTwoPlugin implements MonopolyPlugin {

	private IController controller;
	private final Logger logger = LogManager.getLogger("DummyTwo");

	@Override
	public void update(GameStatus e) {
		logger.info("DummyTwo got status" + e);
	}

	@Override
	public String getName() {
		return "DummyPlugin 2";
	}

	@Override
	public void enable(IController controller) {
		this.controller = controller;
		logger.info("DummyTwo got enabled");

	}

	@Override
	public void disable() {
		logger.info("DummyTwo got disabled");
	}

}
