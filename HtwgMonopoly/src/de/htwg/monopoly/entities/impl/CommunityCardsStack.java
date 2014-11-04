package de.htwg.monopoly.entities.impl;

import java.util.Deque;
import java.util.LinkedList;

import de.htwg.monopoly.util.FieldType;

public class CommunityCardsStack extends CardsStack {

	private Deque<Integer> position;
	private FieldType fieldType = FieldType.COMMUNITY_STACK;


	public CommunityCardsStack() {
		super();
		position = new LinkedList<Integer>();
		init(this.getClass());
	}

	public CommunityCardsStack(int position) {
		this();
		this.position.push(position);
	}

	@Override
	public FieldType getType() {
		// g wie Gemeinschaftsfeld.
		return fieldType;
	}

	@Override
	public String toString() {
		return "Gemeinschaftsfeld";
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
