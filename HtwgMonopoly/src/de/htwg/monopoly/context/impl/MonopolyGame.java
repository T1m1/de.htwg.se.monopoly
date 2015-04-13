/**
 * 
 */
package de.htwg.monopoly.context.impl;

import java.util.UUID;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
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

	public MonopolyGame(IPlayerController players, IPlayfield field,
			PrisonQuestion questions, GameStatus currentPhase, String name) {
		this.field = field;
		this.phase = currentPhase;
		this.players = players;
		this.questions = questions;
		this.name = name;

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
	public void setPlayfield(IPlayfield field) {
		this.field = field;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPlayerController(IPlayerController players) {
		this.players = players;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPrisonQuestions(PrisonQuestion questions) {
		this.questions = questions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCurrentGamePhase(GameStatus currentPhase) {
		this.phase = currentPhase;
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
	public void setName(String name) {
		this.name = name;
	}

}
