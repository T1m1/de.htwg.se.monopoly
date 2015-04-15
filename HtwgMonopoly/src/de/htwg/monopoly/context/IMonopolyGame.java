package de.htwg.monopoly.context;

import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.util.GameStatus;

public interface IMonopolyGame {

	IPlayfield getPlayfield();

	IPlayerController getPlayerController();

	PrisonQuestion getPrisonQuestions();

	GameStatus getCurrentGamePhase();

	void setPlayfield(IPlayfield field);

	void setPlayerController(IPlayerController players);

	void setPrisonQuestions(PrisonQuestion questions);

	void setCurrentGamePhase(GameStatus currentPhase);

	String getId();
	
	String getName();
	
	void setName(String name);

	int getParkingMoney();

	void setParkingMoney(int parkingMoney);

}
