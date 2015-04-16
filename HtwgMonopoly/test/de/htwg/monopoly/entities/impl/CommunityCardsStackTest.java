package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.FieldType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommunityCardsStackTest {

	private CommunityCardsStack stack;
	private CommunityCardsStack stack1;

	@Before
	public void setUp() throws Exception {
		stack = new CommunityCardsStack();
		stack.pushOnTop(new ChanceCard("Gehe in das Gef&auml;ngnis", null,
				 false));
		stack1 = new CommunityCardsStack(1);
	}

	@Test
	public void testCommunityCardsStack() {
		assertEquals("Gehe in das Gef&auml;ngnis", stack.getNextCard()
				.getDescription());
		assertEquals(FieldType.COMMUNITY_STACK, stack.getType());
	}

	@Test
	public void testToString() {
		assertEquals("Gemeinschafts-Feld", stack.toString());
	}
	
	@Test
	public void testGetPostition() {
		assertEquals(1, stack1.getPosition());
	}

}
