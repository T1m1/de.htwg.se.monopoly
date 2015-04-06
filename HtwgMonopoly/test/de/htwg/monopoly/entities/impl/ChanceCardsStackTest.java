package de.htwg.monopoly.entities.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.ChanceCard;
import de.htwg.monopoly.entities.impl.ChanceCardsStack;
import de.htwg.monopoly.util.FieldType;

public class ChanceCardsStackTest {

	private ChanceCardsStack stack;
	private ChanceCardsStack stack1;

	@Before
	public void setUp() throws Exception {
		stack = new ChanceCardsStack();
		stack.pushOnTop(new ChanceCard("Gehe in das Gef&auml;ngnis", null,
				false));
		stack1 = new ChanceCardsStack(1);
	}

	@Test
	public void testChanceCardsStack() {
		assertEquals("Gehe in das Gef&auml;ngnis", stack.getNextCard()
				.getDescription());
		assertEquals(FieldType.CHANCE_STACK, stack.getType());
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
