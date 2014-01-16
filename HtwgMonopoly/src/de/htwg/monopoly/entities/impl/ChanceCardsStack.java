package de.htwg.monopoly.entities.impl;

import java.util.Deque;
import java.util.LinkedList;

import de.htwg.monopoly.entities.ICards;

public class ChanceCardsStack extends CardsStack {

	private Deque<Integer> position;

	/**
	 * Constructor for testing
	 * 
	 * @param card
	 */
	public ChanceCardsStack(ICards card) {
		super();
		pushOnTop(card);
		position = new LinkedList<Integer>();
	}
	
	public ChanceCardsStack() {
		super();
		position = new LinkedList<Integer>();
		
		init(this.getClass());
	}

	public ChanceCardsStack(int guiPosition) {
		this();
		this.position.push(guiPosition);
	}

	@Override
	public char getType() {
		// e wie ereignisfeld.
		return 'e';
	}

	public String toString() {
		return "Ereignisfeld";
	}
	
	/**
	 * Using a deque, because we need all position of the singleton stack.p
	 * after we poll the last value we push it back to the top of the list. 
	 * Because the gui call a lot of time the same function.. !?
	 * 
	 */
	@Override
	public int getPosition() {
		int ret = this.position.pollLast();
		this.position.push(ret);
		return ret;
	}

	public void setPosition(Integer position) {
		this.position.push(position);
		
	}
}
