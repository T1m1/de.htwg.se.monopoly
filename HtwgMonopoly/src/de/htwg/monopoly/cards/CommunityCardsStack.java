package de.htwg.monopoly.cards;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class CommunityCardsStack implements ICardStack {
	
	private Deque<ICards> Cards = new LinkedList<ICards>();
	
	public CommunityCardsStack() {
		// Idee: For-Schleife über ein bestimmtes FILE und dann pushOnTop() entweder hier oder im Controller
		//TODO elemente und Inhalte (Texte)
		//TODO generelle Frage: Werden die Karteninhalte am Anfang eingelesen, oder hardcodiert von vornerein drin?
		// denn dann muss evtl die Fehlerbehandlung erweitert werden.
	}

	@Override
	public ICards getNextCard() {
		ICards tmp = Cards.pollFirst();
		Cards.offerLast(tmp);
		return tmp;
	}
	
	@Override
	public void pushOnTop(ICards newCard)
	{
		Cards.push(newCard);
	}
	

	@Override
	public void shuffle() { //TODO Randomseed übergeben und überhaupt mal blicken was der seed macht... Bisher geht der Test nur mit einem Element auf dem Stack
		Collections.shuffle((LinkedList<ICards>) this.Cards);
	}

}
