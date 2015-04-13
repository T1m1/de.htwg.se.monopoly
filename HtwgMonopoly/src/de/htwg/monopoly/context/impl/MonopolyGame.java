/**
 * 
 */
package de.htwg.monopoly.context.impl;


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

	public MonopolyGame(IPlayerController players, IPlayfield field,
			PrisonQuestion questions, GameStatus currentPhase) {
		this.field = field;
		this.phase = currentPhase;
		this.players = players;
		this.questions = questions;
		// TODO generate an ID
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

}
