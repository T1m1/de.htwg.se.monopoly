package de.htwg.monopoly.entities;

import java.util.Deque;
import java.util.LinkedList;

public class CommunityCardsStack implements CardStack {
	
	private Deque<Card> Cards = new LinkedList<Card>();
	
	public CommunityCardsStack() {
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
