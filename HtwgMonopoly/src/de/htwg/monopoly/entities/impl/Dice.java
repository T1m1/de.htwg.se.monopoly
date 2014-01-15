package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.IMonopolyUtil;

public final class Dice {

	public Dice() {
	}

	private int dice1 = 0;
	private int dice2 = 0;
	private int resultDice = 0;

	/**
	 * for now, this method only returns a new value, when the metho22d setDice is
	 * called before. Going to fix that
	 * 
	 * @return
	 */
	public void throwDice() {
		dice1 = setDice(1, IMonopolyUtil.DICE);
		dice2 = setDice(1, IMonopolyUtil.DICE);
		resultDice = dice1 + dice2;
	}

	/**
	 * Set the dice with a random number between lowerbound (standard 1) and
	 * upperbound (standard 6) (inclusive)
	 * 
	 * @param lowerBound
	 * @param upperBound
	 */
	private int setDice(int lowerBound, int upperBound) {
		return (int) (Math.random() * ((upperBound + 1) - lowerBound) + lowerBound);
	}

	public int getDice1() {
		return dice1;
	}

	public int getDice2() {
		return dice2;
	}

	public int getResultDice() {
		return resultDice;
	}

}
