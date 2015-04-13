/**
 * 
 */
package de.htwg.monopoly.factory.impl;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.impl.PlayerController;
import de.htwg.monopoly.controller.impl.Playfield;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.factory.IControllerFactory;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * @author Steffen
 *
 */
public class MonopolyFactory implements IControllerFactory {

	private int fieldSize;

	@Inject
	public MonopolyFactory(@Named("FieldSize") int fieldsize) {
		this.fieldSize = fieldsize;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Playfield createPlayfield() {
		return new Playfield(fieldSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrisonQuestion createPrisonQuestions() {
		return new PrisonQuestion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dice createDice() {
		return new Dice();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerController createPlayerController(
			Map<String, PlayerIcon> players) {
		return new PlayerController(players);
	}

}
