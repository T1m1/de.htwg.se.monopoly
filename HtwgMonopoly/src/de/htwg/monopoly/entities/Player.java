package de.htwg.monopoly.entities;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Player {

	/* class variable for default name of player */
	private static int number = 0;
	/* variables representing a player object */
	private String name;
	private char figure;
	private int budget;
	// ownership ?
	private int position;

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
		return name;
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

	/* ??????????????????????????? -> Controler? */
	/**
	 * get current positin of player
	 * 
	 * @return
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * set postion
	 * 
	 * @param position
	 */
	public void setPosition(int position) {
		this.position = position;
	}

}
