/**
 * 
 */
package de.htwg.monopoly.context.impl;

import java.util.UUID;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.PrisonQuestion;
import de.htwg.monopoly.util.GameStatus;

/**
 * @author Steffen
 *
 */
public class MonopolyGame implements IMonopolyGame {

	private IPlayfield field;
	private IPlayerController players;
	private PrisonQuestion questions;
	private GameStatus phase;
	private String id;
	private String name;
	private int parkingMoney;
	private String message;
	private int diceFlag;
	private boolean drawCardFlag;
	private Dice dice;

	public MonopolyGame(IPlayerController players, IPlayfield field,
			PrisonQuestion questions, GameStatus currentPhase, String name,
			int parkingMoney, String string, int diceFlag,
			boolean drawCardFlag, Dice dice) {
		this.field = field;
		this.phase = currentPhase;
		this.players = players;
		this.questions = questions;
		this.name = name;
		this.parkingMoney = parkingMoney;
		this.message = string;
		this.diceFlag = diceFlag;
		this.drawCardFlag = drawCardFlag;
		this.dice = dice;

		// Set a unique ID for this game context
		this.id = UUID.randomUUID().toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayfield getPlayfield() {
		return field;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPlayerController getPlayerController() {
		return players;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PrisonQuestion getPrisonQuestions() {
		return questions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GameStatus getCurrentGamePhase() {
		return phase;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getParkingMoney() {
		return parkingMoney;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Dice getDice() {
		return this.dice;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean getDrawCardFlag() {
		return this.drawCardFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getDiceFlag() {
		return this.diceFlag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeReady() {
		// set the Resource bundle to null or other way round
		this.field.switchResourceBundle();
	}

}
