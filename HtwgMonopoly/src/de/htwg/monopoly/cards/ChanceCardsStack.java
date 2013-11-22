package de.htwg.monopoly.cards;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class ChanceCardsStack implements ICardStack {

	private Deque<ICards> cards = new LinkedList<ICards>();

	public ChanceCardsStack() {
		cards.push(new CommunityCard("Gehe in das Gefängnis" , "move"));
		//TODO elemente und Inhalte (Texte) siehe CommunityCardsStack
		// hinzufügen
	}

	@Override
	public ICards getNextCard() {
		ICards tmp = cards.pollFirst();
		cards.offerLast(tmp);
		return tmp;
	}

	@Override
	public void pushOnTop(ICards newCard)
	{
		cards.push(newCard);
	}
	

	@Override
	public void shuffle() { 
		Collections.shuffle((LinkedList<ICards>) this.cards);
	}
}
