/**
 * 
 */
package de.htwg.monopoly.plugins;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

/**
 * @author Steffen
 *
 */
public class DummyOnePlugin implements MonopolyPlugin {

	private IController controller;
	private final Logger logger = LogManager.getLogger("DummyOne");


	@Override
	public void update(GameStatus e) {
		logger.info("DummyOne got status: " + e);
	}

	@Override
	public String getName() {
		return "DummyPlugin 1";
	}

	@Override
	public void enable(IController controller) {
		this.controller = controller;
		logger.info("DummyOne got enabled");

	}

	@Override
	public void disable() {
		logger.info("DummyOne got disabled");
	}

}
