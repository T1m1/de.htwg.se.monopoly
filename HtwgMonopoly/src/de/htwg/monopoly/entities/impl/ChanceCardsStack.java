package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.IMonopolyCards;

public class ChanceCardsStack extends CardsStack {

	private int position;

	public ChanceCardsStack() {
		super();

		for (String descr : IMonopolyCards.DESCR_CHANCE) {
			pushOnTop(new ChanceCard(descr, null, false));
		}
	}

	public ChanceCardsStack(int guiPosition) {
		super();
		this.position = guiPosition;
		for (String descr : IMonopolyCards.DESCR_CHANCE) {
			pushOnTop(new ChanceCard(descr, null, false));
		}
	}

	// @Override
	public char getType() {
		// e wie ereignisfeld.
		return 'e';
	}

	public String toString() {
		return "Ereignisfeld";
	}

	@Override
	public int getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
		
	}

}
