package de.htwg.monopoly.cards;


public interface ICardStack {
	
	/**
	 * Return the first Card off a cardstack and put it back under the
	 * stack. Not yet implemented: "Du kommst aus dem Gefängnis frei"-Karte 
	 * 
	 * @return Card
	 */
	ICards getNextCard();
	
	/**
	 * Shuffles the Stack.
	 */
	void shuffle();
	
	/**
	 * Push a new card on top of the Stack.
	 * @param newCard
	 */
	void pushOnTop(ICards newCard);

}
