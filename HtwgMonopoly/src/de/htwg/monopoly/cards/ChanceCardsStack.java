package de.htwg.monopoly.cards;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.IMonopolyCards;



public class ChanceCardsStack extends CardsStack implements IFieldObject {

	public ChanceCardsStack() {
		super();
		
		for (String descr: IMonopolyCards.DESCR_CHANCE) {
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
