package de.htwg.monopoly.entities.impl;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import de.htwg.monopoly.entities.ICards;

public class CommunityCardsStack extends CardsStack {

	private Deque<Integer> position;

	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	public CommunityCardsStack(int position) {
		this();
		this.position.push(position);
	}

	/**
	 * Constructor for testing
	 * 
	 * @param card
	 */
	public CommunityCardsStack(ICards card) {
		super();
		pushOnTop(card);
		position = new LinkedList<Integer>();
	}

	public CommunityCardsStack() {
		super();
		position = new LinkedList<Integer>();

		StringBuilder opt1 = new StringBuilder();

		for (int i = 1; i < 24; i++) {
			opt1.delete(0, opt1.length());
			opt1.append("comm_").append(i);
			
			String[] opt2 = bundle.getString(opt1.toString()).split("\n");
			
			boolean isPark = false;
			if (opt2.length > 2 && opt2[2].equalsIgnoreCase("park")) {
				isPark = true;
			}
			
			pushOnTop(new CommunityCard(opt2[0],
					opt2[1], isPark));
		}

		this.shuffle();
	}

	// @Override
	public char getType() {
		// g wie Gemeinschaftsfeld.
		return 'g';
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

	/* TODO EVTL DEPENDENCY INJECTION */
	public void setPosition(int position) {
		this.position.push(position);
	}

}
