package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.ChanceCard;
import de.htwg.monopoly.entities.impl.CommunityCardsStack;

public class CommunityCardsStackTest {

	CommunityCardsStack stack;
	CommunityCardsStack stack1;

	@Before
	public void setUp() throws Exception {
		stack = new CommunityCardsStack();
		stack.pushOnTop(new ChanceCard("Gehe in das Gefängnis", null,
				 false));
		stack1 = new CommunityCardsStack(1);
	}

	@Test
	public void testCommunityCardsStack() {
		assertEquals("Gehe in das Gefängnis", stack.getNextCard()
				.getDescription());
		assertEquals('g', stack.getType());
	}

	@Test
	public void testToString() {
		assertEquals("Gemeinschaftsfeld", stack.toString());
	}
	
	@Test
	public void testGetPostition() {
		assertEquals(1, stack1.getPosition());
	}

}
