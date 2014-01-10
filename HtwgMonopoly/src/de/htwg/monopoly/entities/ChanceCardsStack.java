package de.htwg.monopoly.entities;

import de.htwg.monopoly.util.IMonopolyCards;



public class ChanceCardsStack extends CardsStack {

	public ChanceCardsStack() {
		super();
		
		for (String descr: IMonopolyCards.DESCR_CHANCE) {
			pushOnTop(new ChanceCard(descr, null));
		}
	}

//	@Override
	public char getType() {
		// e wie ereignisfeld.
		return 'e';
	}
	
	public String toString() {
		return "Ereignisfeld";
	}

}
