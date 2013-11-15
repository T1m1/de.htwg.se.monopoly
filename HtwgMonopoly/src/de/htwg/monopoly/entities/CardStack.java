package de.htwg.monopoly.entities;

public interface CardStack {
	
	/**
	 * Return the first Card off a cardstack and put it back under the
	 * stack. Not yet implemented: "Du kommst aus dem Gefängnis frei"-Karte 
	 * 
	 * @return Card
	 */
	Card getNextCard();

}
