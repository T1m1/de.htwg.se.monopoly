package de.htwg.monopoly.entities;

public class Playfield {
	
	private IFieldObject[] playfield;
	private IFieldObject currentFieldObject;
	
	public void initialize() {
		//initialize the playfield. set the size, fill it with streets and card stacks etc.
	}
	
	public boolean performAction(Player currentPlayer, int diceResult) {
		// calculate the new position of the player within the playfield range
		int position = (currentPlayer.getPosition() + diceResult) % playfield.length;
		
		// set the current FieldObject, where the Player is right now
		this.currentFieldObject = this.playfield[position];
		
		// return true, if the Player went over or stays on "Los"
		return (position < currentPlayer.getPosition());		
	}
	
}
