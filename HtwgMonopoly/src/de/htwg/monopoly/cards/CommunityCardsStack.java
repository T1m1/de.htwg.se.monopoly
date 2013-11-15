package de.htwg.monopoly.cards;

import java.util.Deque;
import java.util.LinkedList;

public class CommunityCardsStack implements ICardStack {
	
	private Deque<ICards> Cards = new LinkedList<ICards>();
	
	public CommunityCardsStack() {
		// Cards.push(new Card()); //TODO elemente und Inhalte (Texte)
		// hinzufügen
	}

	@Override
	public ICards getNextCard() {
		ICards tmp = Cards.pollFirst();
		Cards.offerLast(tmp);
		return tmp;
	}

}
