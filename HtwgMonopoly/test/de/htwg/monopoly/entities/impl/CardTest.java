package de.htwg.monopoly.entities.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {

	private CommunityCard card;

	@Before
	public void setUp() throws Exception {
		card = new CommunityCard("Beschreibung", "prison", false);
	}

	@Test
	public void testGetDescription() {
		card.setDescription("foo");
		assertEquals("foo", card.getDescription());
		card.setDescription("bar");
		assertEquals("bar", card.getDescription());
	}


	@Test
	public void testToString() {
		card.setDescription("bar");
		assertEquals("bar", card.toString());
	}
	
	@Test
	public void testGetTarget() {
		assertEquals("prison", card.getTarget());
		
	}

}
