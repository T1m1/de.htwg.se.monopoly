package de.htwg.monopoly.entities;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Playfield {
	
	private IFieldObject[] playfield;
	
	public void initialize() {
		//initialize the playfield. set the size, fill it with streets and card stacks etc.
		//TODO actual initializing
		this.playfield = new IFieldObject[IMonopolyUtil.TEST_PLAYFIELD_SIZE];
		
	}
	
	/**
	 * Move the Player to the new Field.
	 * 
	 * @param currentPlayer
	 * @param diceResult
	 * @return true if Player moved over or stays on "Los" otherwise return false 
	 */
	public boolean movePlayer(Player currentPlayer, int diceResult) {
		// calculate the new position of the player within the playfield range and save its old position
		int position = (currentPlayer.getPosition() + diceResult) % playfield.length;
		int oldPosition = currentPlayer.getPosition();
		
		// Move the player 
		currentPlayer.setPosition(position);
		
		// return true, if the Player went over or stays on "Los"
		return (position < oldPosition);		
	}
	
	/**
	 * Get the current Field where the Player is standing on
	 * 
	 * @param currentPosition
	 * @return An Object of Type IFieldObject
	 */
	public IFieldObject getCurrentField(Player currentPlayer) {
		return playfield[currentPlayer.getPosition()];
	}
}
