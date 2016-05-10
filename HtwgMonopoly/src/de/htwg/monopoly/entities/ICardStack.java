package de.htwg.monopoly.entities;

import java.util.ResourceBundle;


public interface ICardStack {
	
	/**
	 * Return the first Card off a cardstack and put it back under the
	 * stack. Not yet implemented: "Du kommst aus dem Gefängnis frei"-Karte
	 * 
	 * @return Card
	 */
	ICards getNextCard();

	/**
	 * shuffles this Cardstack.
	 * 
	 */
	void shuffle();

	void setResourceBundle(ResourceBundle bundle);

}
