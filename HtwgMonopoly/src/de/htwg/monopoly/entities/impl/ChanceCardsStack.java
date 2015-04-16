package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.FieldType;

import java.util.Deque;
import java.util.LinkedList;

public class ChanceCardsStack extends CardsStack {

	private Deque<Integer> position;

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
	public FieldType getType() {
		
		return FieldType.CHANCE_STACK;
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
