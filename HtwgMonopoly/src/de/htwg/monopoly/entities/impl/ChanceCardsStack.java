package de.htwg.monopoly.entities.impl;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import de.htwg.monopoly.entities.ICards;

public class ChanceCardsStack extends CardsStack {

	private Deque<Integer> position;
	
	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

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
		pushOnTop(new ChanceCard(bundle.getString("chance_1"),
				bundle.getString("chance_1.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_2"),
				bundle.getString("chance_2.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_3"),
				bundle.getString("chance_3.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_4"),
				bundle.getString("chance_4.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_5"),
				bundle.getString("chance_5.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_6"),
				bundle.getString("chance_6.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_7"),
				bundle.getString("chance_7.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_8"),
				bundle.getString("chance_8.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_9"),
				bundle.getString("chance_9.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_10"),
				bundle.getString("chance_10.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_11"),
				bundle.getString("chance_11.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_12"),
				bundle.getString("chance_12.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_13"),
				bundle.getString("chance_13.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_14"),
				bundle.getString("chance_14.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_15"),
				bundle.getString("chance_15.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_16"),
				bundle.getString("chance_16.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_17"),
				bundle.getString("chance_17.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_18"),
				bundle.getString("chance_18.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_19"),
				bundle.getString("chance_19.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_20"),
				bundle.getString("chance_20.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_21"),
				bundle.getString("chance_21.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_22"),
				bundle.getString("chance_22.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_23"),
				bundle.getString("chance_23.1"), false));
		pushOnTop(new ChanceCard(bundle.getString("chance_24"),
				bundle.getString("chance_24.1"), false));

		this.shuffle();
	}

	public ChanceCardsStack(int guiPosition) {
		this();
		this.position.push(guiPosition);
	}

	// @Override
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
