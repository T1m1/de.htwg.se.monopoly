package de.htwg.monopoly.entities;

public class Playfield {
	
	private IFieldObject[] playfield;
	
	public void initialize() {
		//initialize the playfield. set the size, fill it with streets and card stacks etc.
	}
	
	public void performAction(Player currentPlayer, int diceResult) {
		int position = currentPlayer.getPosition();
		position = position + diceResult;
		// if position greater than actual playfield size do magic stuff
		
	}
	
}
