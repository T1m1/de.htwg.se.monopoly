package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

	CommunityCard card;

	@Before
	public void setUp() throws Exception {
		card = new CommunityCard("Beschreibung", "move");
	}

	@Test
	public void testGetDescription() {
		card.setDescription("foo");
		assertEquals("foo", card.getDescription());
		card.setDescription("bar");
		assertEquals("bar", card.getDescription());
	}

	@Test
	public void testGetActionType() {
		assertEquals("move", card.getActionType());
	}
	
	@Test
	public void testToString() {
		card.setDescription("bar");
		assertEquals("bar", card.toString());
	}

}
