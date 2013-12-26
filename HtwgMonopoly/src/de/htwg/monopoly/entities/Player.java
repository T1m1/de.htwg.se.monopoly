package de.htwg.monopoly.entities;

import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Player {

	/* class variable for default name of player */
	private static int number = 0;
	/* variables representing a player object */
	private String name;
	private char figure;
	private int budget;
	// ownership ?
	private int position = 0;
	private int prisonRound = 0;
	private boolean inPrison = false;
	private List<IFieldObject> ownership;

	/**
	 * default player constructor
	 */
	public Player() {
		/* increment number of player */
		number++;
		/* generate default player */
		this.name = "player" + number;
		this.figure = (char) number;
		this.budget = IMonopolyUtil.INITIAL_MONEY;
		ownership = new LinkedList<IFieldObject>();
	}

	/**
	 * constructor for player
	 * 
	 * @param name
	 * @param figure
	 * @param budget
	 * @param position
	 */
	public Player(String name, char figure, int budget, int position) {
		this.name = name;
		this.figure = figure;
		this.budget = budget;
		this.position = position;
	}

	/**
	 * constructor for player
	 * 
	 * @param name
	 * @param figure
	 * @param budget
	 */
	public Player(String name, char figure, int budget) {
		this(name, figure, budget, 1);
	}

	/**
	 * get name of player
	 * 
	 * @return
	 */
	public String getName() {
		return this.toString();
	}

	/**
	 * set name of player
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get figure of player
	 * 
	 * @return
	 */
	public char getFigure() {
		return figure;
	}

	/**
	 * set figure for player
	 * 
	 * @param figure
	 */
	public void setFigure(char figure) {
		this.figure = figure;
	}

	/**
	 * get current budget for player
	 * 
	 * @return
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * set new budget for player
	 * 
	 * @param budget
	 */
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	 * get current position of player
	 * 
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * set position
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * get the current number of rounds in prison of this player. If the
	 * returning number is 0, the current player is not in prison.
	 * 
	 * @return integer;
	 */
	public int getPrisonRound() {
		return prisonRound;
	}

	/**
	 * Increment the prisonRound Number. If three is incremented, the value is
	 * set to 0 and the inPrison status is set to false. 0 means the Player is
	 * not in prison. 1-3 means the Player is in prison and number indicate the
	 * round.
	 * 
	 * @param prisonRound
	 */
	public void incrementPrisonRound() {
		this.prisonRound = (prisonRound + 1) % IMonopolyUtil.MAX_PRISON_ROUND;
		this.inPrison = (prisonRound != 0);
	}

	/**
	 * Set the Number of Rounds in prison of this player; 0 means the Player is
	 * not in prison. 1-3 means the Player is in prison and number indicate the
	 * round
	 * 
	 * @param roundNumber
	 */
	public void setPrisonRound(int roundNumber) {
		this.prisonRound = roundNumber;
	}

	/**
	 * If the Player is in prison this method returns true, otherwise false.
	 * 
	 * @return
	 */
	public boolean isInPrison() {
		return inPrison;
	}

	/**
	 * Put the Player in prison if true.
	 * 
	 * @param inPrison
	 */
	public void setInPrison(boolean inPrison) {
		this.inPrison = inPrison;
	}

	/**
	 * override to string method to return the name of the player
	 */
	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * 
	 * @return a list with the ownership of player
	 */
	public List<IFieldObject> getOwnership() {
		return ownership;
	}

	/**
	 * add a new ownership for player e.g. a street
	 * @param street
	 */
	public void addOwnership(IFieldObject street) {
		ownership.add(street);
	}

	/**
	 * decremt money of player e.g. to buy a street 
	 * @param freikaufen
	 */
	public void decrementMoney(int freikaufen) {
		this.budget -= freikaufen;
	}

}