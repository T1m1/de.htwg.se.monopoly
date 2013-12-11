package de.htwg.monopoly.entities;

import java.awt.Color;

import de.htwg.monopoly.util.IMonopolyUtil;

public class Playfield {
	
	private IFieldObject[] playfield;
	
	public Playfield() {
		initialize();
	}
	
	public final void initialize() {
		//initialize the playfield. set the size, fill it with streets and card stacks etc.
		//TODO actual initializing, dabei muss evtl beachtet werden, dass es alles irgendwie variabel sein sollte. Stichwort: skalierbarkeit
		this.playfield = new IFieldObject[IMonopolyUtil.TEST_PLAYFIELD_SIZE];
		playfield[1] = new Street("Test", IMonopolyUtil.TEST_PRICE_ONE, Color.black, IMonopolyUtil.TEST_PRICE_ONE, IMonopolyUtil.TEST_PRICE_ONE, IMonopolyUtil.TEST_PRICE_ONE);
		
	}
	
	/**
	 * Move the Player to the new Field according to the result of the dice roll
	 * 
	 * @param currentPlayer which will be moved
	 * @param diceResult: a Number between 2 and 12;
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
	 * Get the current Field where the Player is standing on.
	 * 
	 * @param currentPlayer
	 * @return An Object of Type IFieldObject
	 */
	public IFieldObject getCurrentField(Player currentPlayer) {
		return playfield[currentPlayer.getPosition()];
	}
}
