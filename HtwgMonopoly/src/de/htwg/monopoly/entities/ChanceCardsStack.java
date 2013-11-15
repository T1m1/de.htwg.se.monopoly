package de.htwg.monopoly.entities;

import java.util.Deque;
import java.util.LinkedList;

public class ChanceCardsStack implements CardStack {

	private Deque<Card> Cards = new LinkedList<Card>();

	public ChanceCardsStack() {
		// Cards.push(new Card()); //TODO elemente und Inhalte (Texte)
		// hinzufügen
	}

	@Override
	public Card getNextCard() {
		Card tmp = Cards.pollFirst();
		Cards.offerLast(tmp);
		return tmp;
	}
}
