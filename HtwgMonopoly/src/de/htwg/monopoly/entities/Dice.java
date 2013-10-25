package de.htwg.monopoly.entities;

public class Dice {
	private int dice;

	public int getDice() {
		return dice;
	}

	public void setDice(int lowerBound, int upperBound) {
		upperBound++;
		this.dice = (int) (Math.random() * (upperBound - lowerBound) + lowerBound );
	}
}
