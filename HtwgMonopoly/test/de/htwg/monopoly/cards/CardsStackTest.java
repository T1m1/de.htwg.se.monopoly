package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.CommunityCard;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;

public class CardsStackTest {

	CommunityCardsStack stack;

	@Before
	public void setUp() throws Exception {
		stack = new CommunityCardsStack();
		stack.pushOnTop(new CommunityCard("Gehe in das Gefängnis", "move",
				null, 0, 0, false));
	}

	@Test
	public void testGetNextCard() {
		assertEquals("Gehe in das Gefängnis", stack.getNextCard()
				.getDescription());
	}
}
