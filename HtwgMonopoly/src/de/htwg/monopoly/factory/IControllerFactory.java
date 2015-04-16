package de.htwg.monopoly.factory;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.util.PlayerIcon;

import java.util.Map;

/**
 * 
 * @author Steffen
 *
 */
public interface IControllerFactory {

	/**
	 * Creates an instance of a playfield controller.
	 * 
	 * @param fieldSize
	 * @return
	 */
	IPlayfield createPlayfield(int fieldSize);

	/**
	 * Creates an instance of a player controller, which holds all players of a
	 * game.
	 * 
	 * @param players
	 * @return
	 */
	IPlayerController createPlayerController(Map<String, PlayerIcon> players);

	/**
	 * Creates an instance of questions for redeeming from prison. It holds a
	 * number of question, which will be asked, if a player wants to set himself
	 * free from prison.
	 * 
	 * @return
	 */
	PrisonQuestion createPrisonQuestions();

	/**
	 * Creates a dice.
	 * 
	 * @return
	 */
	Dice createDice();

}
