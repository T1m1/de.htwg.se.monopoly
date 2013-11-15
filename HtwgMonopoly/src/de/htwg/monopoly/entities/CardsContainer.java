package de.htwg.monopoly.entities;

import java.util.Deque;
import java.util.LinkedList;

public class CardsContainer {

	private Deque<Card> chanceCards = new LinkedList<Card>();
	private Deque<Card> commuCards = new LinkedList<Card>();

	public CardsContainer() {
		// chanceCards.push(new Card()); //TODO elemente und Inhalte (Texte)
		// hinzufügen
	}

	/**
	 * Return the first Card off the Chance cardstack and put it back under the
	 * stack. Not yet implemented: "Du kommst aus dem Gefängnis frei"-Karte 
	 * 
	 * @return Card
	 */
	public Card getNextChanceCard() {
		Card tmp = chanceCards.pollFirst();
		chanceCards.offerLast(tmp);
		return tmp;
	}

	/**
	 * Return the first Card off the Community Chest cardstack and put it back
	 * under the stack "Du kommst aus dem Gefängnis frei"-Karte 
	 * 
	 * @return Card
	 */
	public Card getNextCommuCard() {
		Card tmp = commuCards.pollFirst();
		commuCards.offerLast(tmp);
		return tmp;
	}
}
