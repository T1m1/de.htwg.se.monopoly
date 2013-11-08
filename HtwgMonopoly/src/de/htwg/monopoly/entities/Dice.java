package de.htwg.monopoly.entities;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Dice {
	
	private int dice = 0;

	public Dice() {
		this.setDice(1, IMonopolyUtil.DICE);
	}
	
	public int getDice() {
		return dice;
	}

	/**
	 * Set the dice with a random number between lowerbound and upperbound (inclusive)
	 * @param lowerBound
	 * @param upperBound
	 */
	public final void setDice(int lowerBound, int upperBound) {
		assert lowerBound < upperBound : "Wrong values for Dice";
		this.dice = (int) (Math.random() * ((upperBound++) - lowerBound) + lowerBound );
	}
}
