package de.htwg.monopoly.cards;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class CommunityCardsStack implements ICardStack {
	
	private Deque<ICards> Cards = new LinkedList<ICards>();
	
	public CommunityCardsStack() {
		// Idee: For-Schleife über ein bestimmtes FILE und dann pushOnTop();
		pushOnTop(new CommunityCard("Gehe in das Gefängnis" , "move")); //TODO elemente und Inhalte (Texte)
		//TODO generelle Frage: Werden die Karteninhalte am Anfang eingelesen, oder hardcodiert von vornerein drin?
		// denn dann muss evtl die Fehlerbehandlung erweitert werden.
		// Meine Empfehlung ist hardcodiert einbauen, dann muss keine abartige Fehlerbehandlung gemacht werden.
		// Wird wohl aber notwendig sein um gescheite Tests zu bauen und um shuffle gescheit auszuführen.
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
	public void shuffle() { //TODO Randomseed übergeben und überhaupt mal blicken was der seed macht...
		Collections.shuffle((LinkedList<ICards>) this.Cards);
	}
	
	

}
