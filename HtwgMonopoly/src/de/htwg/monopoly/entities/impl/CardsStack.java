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
		String typ;

		if (cardStack.equals(CommunityCardsStack.class)) {
			typ = "comm_";
		} else {
			typ = "chance_";
		}

		for (int i = 1; i < IMonopolyUtil.NUMBER_OF_CARDS_IN_STACK; i++) {
			opt1.delete(0, opt1.length());
			opt1.append(typ).append(i);

			String[] opt2 = bundle.getString(opt1.toString()).split("\n");

			boolean isPark = false;
			if (opt2.length > 2 && opt2[2].equalsIgnoreCase("park")) {
				isPark = true;
			}

			if (typ.equals("comm_")) {
				cards.push(new CommunityCard(opt2[0], opt2[1], isPark));
			} else {
				cards.push(new ChanceCard(opt2[0], opt2[1], isPark));
			}

		}
		this.shuffle();

	}
}
