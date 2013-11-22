package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChanceCardsStackTest {
	
	ChanceCardsStack stack;

	@Before
	public void setUp() throws Exception {
		stack = new ChanceCardsStack();
		stack.pushOnTop(new CommunityCard("Gehe in das Gefängnis" , "move"));
	}

	@Test
	public void testGetNextCard() {
		assertEquals("Gehe in das Gefängnis", stack.getNextCard().getDescription());
	}
	
	@Test
	public void testShuffle() {
		stack.shuffle();
		assertEquals("Gehe in das Gefängnis", stack.getNextCard().getDescription());
	
	}

}
