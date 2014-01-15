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
		pushOnTop(new CommunityCard(bundle.getString("comm_1"),
				bundle.getString("comm_1.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_2"),
				bundle.getString("comm_2.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_3"),
				bundle.getString("comm_3.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_4"),
				bundle.getString("comm_4.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_5"),
				bundle.getString("comm_5.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_6"),
				bundle.getString("comm_6.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_7"),
				bundle.getString("comm_7.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_8"),
				bundle.getString("comm_8.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_9"),
				bundle.getString("comm_9.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_10"),
				bundle.getString("comm_10.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_11"),
				bundle.getString("comm_11.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_12"),
				bundle.getString("comm_12.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_13"),
				bundle.getString("comm_13.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_14"),
				bundle.getString("comm_14.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_15"),
				bundle.getString("comm_15.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_16"),
				bundle.getString("comm_16.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_17"),
				bundle.getString("comm_17.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_18"),
				bundle.getString("comm_18.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_19"),
				bundle.getString("comm_19.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_20"),
				bundle.getString("comm_20.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_21"),
				bundle.getString("comm_21.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_22"),
				bundle.getString("comm_22.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_23"),
				bundle.getString("comm_23.1"), false));
		pushOnTop(new CommunityCard(bundle.getString("comm_24"),
				bundle.getString("comm_24.1"), false));

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
