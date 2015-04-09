/**
 * 
 */
package de.htwg.monopoly.factory.impl;

import java.util.Map;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.controller.impl.MockPlayerController;
import de.htwg.monopoly.controller.impl.MockPlayfield;
import de.htwg.monopoly.controller.impl.UserOptionsController;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.factory.IControllerFactory;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * @author Steffen
 *
 */
public class TestMonopolyFactory implements IControllerFactory {

	@Override
	public IPlayfield createPlayfield(int fieldSize) {
		return new MockPlayfield(fieldSize);
	}

	@Override
	public IPlayerController createPlayerController(
			Map<String, PlayerIcon> players) {
		return new MockPlayerController(players);
	}

	@Override
	public PrisonQuestion createPrisonQuestions() {
		return new PrisonQuestion();
	}

	@Override
	public UserOptionsController createUserController(Controller controller) {
		return new UserOptionsController(controller);
	}

	@Override
	public Dice createDice() {
		return new Dice();
	}

}
