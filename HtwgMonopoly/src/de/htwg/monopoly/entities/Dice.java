package de.htwg.monopoly.entities;

public class Dice {
	private int dice = 0;

	public Dice() {
		setDice(1, 6);
	}
	public int getDice() {
		return dice;
	}

	/**
	 * Set the dice with a random number between lowerbound and upperbound (inclusive)
	 * @param lowerBound
	 * @param upperBound
	 */
	public void setDice(int lowerBound, int upperBound) {
		/* if lowerBound is greater the upperBound, than switch the values */
		if (lowerBound > upperBound) {
			int a = lowerBound;
			lowerBound = upperBound;
			upperBound = a;
		}			
		upperBound++;
		this.dice = (int) (Math.random() * (upperBound - lowerBound) + lowerBound );
	}
}
