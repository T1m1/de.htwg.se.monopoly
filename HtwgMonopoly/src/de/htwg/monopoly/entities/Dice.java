package de.htwg.monopoly.entities;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Dice {
	
	public static int dice1 = 0;
	public static int dice2 = 0;

	/**
	 *  calls the setDice method with 1 (lowerbound) and 6 (upperbound)
	 */
	public Dice() {	}
	
	/**
	 * for now, this method only returns a new value, when the method setDice is called before. Going to fix that
	 * 
	 * @return
	 */
	public static void throwDice() {		
		dice1 = setDice(1, IMonopolyUtil.DICE);
		dice2 = setDice(1, IMonopolyUtil.DICE);
	}

	/**
	 * Set the dice with a random number between lowerbound (standard 1) and upperbound (standard 6) (inclusive)
	 * @param lowerBound
	 * @param upperBound
	 */
	private static int setDice(int lowerBound, int upperBound) {
		return  (int) (Math.random() * ((upperBound + 1) - lowerBound) + lowerBound );
	}
}
