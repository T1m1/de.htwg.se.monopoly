package de.htwg.monopoly.cards;

import de.htwg.monopoly.util.IMonopolyCards;



public class ChanceCardsStack extends CardsStack {

	public ChanceCardsStack() {
		super();
		
		for (String descr: IMonopolyCards.descrChance) {
			pushOnTop(new ChanceCard(descr, null));
		}
	}

	@Override
	public char getType() {
		// e wie ereignisfeld.
		return 'e';
	}
	
	public String toString() {
		return "Ereignisfeld";
	}

}
