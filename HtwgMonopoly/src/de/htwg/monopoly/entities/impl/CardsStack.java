package de.htwg.monopoly.entities.impl;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import de.htwg.monopoly.entities.ICardStack;
import de.htwg.monopoly.entities.ICards;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.util.IMonopolyUtil;

public abstract class CardsStack implements ICardStack, IFieldObject {

	private Deque<ICards> cards = new LinkedList<ICards>();

	private ResourceBundle bundle = ResourceBundle.getBundle("Messages",
			Locale.GERMAN);

	public CardsStack() { }

	@Override
	public ICards getNextCard() {
		ICards tmp = cards.pollFirst();
		cards.offerLast(tmp);
		return tmp;
	}

	public void pushOnTop(ICards newCard) {
		cards.push(newCard);
	}

	@Override
	public void shuffle() {
		Collections.shuffle((LinkedList<ICards>) this.cards);
	}

	protected void init(Class<? extends CardsStack> cardStack) {

		StringBuilder opt1 = new StringBuilder();
		String temptyp;

		if (cardStack.equals(CommunityCardsStack.class)) {
			temptyp = "comm_";
		} else {
			temptyp = "chance_";
		}

		for (int i = 1; i < IMonopolyUtil.NUMBER_OF_CARDS_IN_STACK; i++) {
			// empty the string builder
			opt1.delete(0, opt1.length());
			
			// create string for looking up resource
			opt1.append(temptyp).append(i);

			// retrieve the resource and split around a newline statement 
			String[] opt2 = bundle.getString(opt1.toString()).split("\n");

			// check for the park option
			boolean isPark = false;
			if (opt2.length > 2 && opt2[2].equalsIgnoreCase("park")) {
				isPark = true;
			}

			// create the actual card depending on the resource text
			if (temptyp.equals("comm_")) {
				cards.push(new CommunityCard(opt2[0], opt2[1], isPark));
			} else {
				cards.push(new ChanceCard(opt2[0], opt2[1], isPark));
			}

		}
		this.shuffle();

	}
}
