package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommunityCardsStackTest {
	
	CommunityCardsStack stack;

	@Before
	public void setUp() throws Exception {
		stack = new CommunityCardsStack();
		stack.pushOnTop(new CommunityCard("Gehe in das Gefängnis" , "move"));
	}

	@Test
	public void testGetNextCard() {
		//assertThat(new CommunityCard("Gehe in das Gefängnis" , "move").getClass(), stack.getNextCard().getClass());
		assertEquals("Gehe in das Gefängnis", stack.getNextCard().getDescription());
	}
	
	@Test
	public void testShuffle() {
		stack.shuffle();
		assertEquals("Gehe in das Gefängnis", stack.getNextCard().getDescription());
	
	}
}
