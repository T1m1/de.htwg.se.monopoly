package de.htwg.monopoly.entities;

import java.util.Deque;
import java.util.LinkedList;

public class CardsContainer {

	private Deque<Card> chanceCards = new LinkedList<Card>();
	private Deque<Card> commuCards = new LinkedList<Card>();
	
	public CardsContainer() {
		//chanceCards.push(new Card()); //TODO elemente und Inhalte (Texte) hinzufügen
	}
}
