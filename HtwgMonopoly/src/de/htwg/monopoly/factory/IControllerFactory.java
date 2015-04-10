package de.htwg.monopoly.factory;

import java.util.Map;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.controller.impl.UserOptionsController;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.util.PlayerIcon;

public interface IControllerFactory {

	IPlayfield createPlayfield(int fieldSize);

	IPlayerController createPlayerController(Map<String, PlayerIcon> players);

	PrisonQuestion createPrisonQuestions();

	Dice createDice();

}
