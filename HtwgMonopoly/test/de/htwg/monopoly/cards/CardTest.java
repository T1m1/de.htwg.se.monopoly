package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.CommunityCard;

public class CardTest {

	CommunityCard card;

	@Before
	public void setUp() throws Exception {
		card = new CommunityCard("Beschreibung", "move", "prison", false);
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
	
	@Test
	public void testGetTarget() {
		assertEquals("prison", card.getTarget());
		
	}

}
