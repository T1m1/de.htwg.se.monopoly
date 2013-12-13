package de.htwg.monopoly.cards;

import de.htwg.monopoly.util.IMonopolyCards;


public class CommunityCardsStack extends CardsStack  {

	
	public CommunityCardsStack() {
		super();
		
		for (String descr: IMonopolyCards.descrCommu) {
			pushOnTop(new ChanceCard(descr, null));
		}
	}

	@Override
	public char getType() {
		// g wie Gemeinschaftsfeld.
		return 'g';
	}

	

}
