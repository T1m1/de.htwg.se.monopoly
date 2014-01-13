package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.ChanceCard;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;

public class ChanceCardsStackTest {

	ChanceCardsStack stack;

	@Before
	public void setUp() throws Exception {
		stack = new ChanceCardsStack();
		stack.pushOnTop(new ChanceCard("Gehe in das Gefängnis", "move", null,
				0, 0, false));
	}

	@Test
	public void testChanceCardsStack() {
		assertEquals("Gehe in das Gefängnis", stack.getNextCard()
				.getDescription());
		assertEquals('e', stack.getType());
	}

	@Test
	public void testToString() {
		assertEquals("Ereignisfeld", stack.toString());
	}

}
