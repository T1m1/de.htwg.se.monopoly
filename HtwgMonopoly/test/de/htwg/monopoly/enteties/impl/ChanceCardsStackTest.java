package de.htwg.monopoly.enteties.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.ChanceCard;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;

public class ChanceCardsStackTest {

	ChanceCardsStack stack;
	ChanceCardsStack stack1;

	@Before
	public void setUp() throws Exception {
		stack = new ChanceCardsStack();
		stack.pushOnTop(new ChanceCard("Gehe in das Gefängnis", null,
				false));
		stack1 = new ChanceCardsStack(1);
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
	
	
	@Test
	public void testGetPostition() {
		assertEquals(1, stack1.getPosition());
	}

}
