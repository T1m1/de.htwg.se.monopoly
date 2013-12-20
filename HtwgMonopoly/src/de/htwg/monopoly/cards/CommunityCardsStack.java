package de.htwg.monopoly.cards;

import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.IMonopolyCards;


public class CommunityCardsStack extends CardsStack implements IFieldObject {

	
	public CommunityCardsStack() {
		super();
		
		for (String descr: IMonopolyCards.DESCR_COMMU) {
			pushOnTop(new ChanceCard(descr, null));
		}
	}

	@Override
	public char getType() {
		// g wie Gemeinschaftsfeld.
		return 'g';
	}
	
	@Override
	public String toString() {
		return "Gemeinschaftsfeld";
	}

	

}
