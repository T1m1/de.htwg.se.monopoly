/**
 * 
 */
package de.htwg.monopoly.factory.impl;

import java.util.Map;

import com.google.inject.Inject;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.controller.impl.Playfield;
import de.htwg.monopoly.controller.impl.UserOptionsController;
import de.htwg.monopoly.entities.IDice;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.factory.IControllerFactory;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * @author Steffen
 *
 */
public class MonopolyFactory implements IControllerFactory {

	private IDice dice;

	@Inject
	public MonopolyFactory(IDice dice) {
		this.dice = dice;
	}

	@Override
	public Playfield createPlayfield(int fieldSize) {
		return new Playfield(fieldSize);
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
	public IDice createDice() {
		return dice;
	}

	@Override
	public IPlayerController createPlayerController(
			Map<String, PlayerIcon> players) {
		return new PlayerController(players);
	}

}
