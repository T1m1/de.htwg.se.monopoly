package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CardTest {

	Card card;

	@Before
	public void setUp() throws Exception {
		card = new Card("description");
	}

	@Test
	public void testGetDescription() {
		/* added for Findbugs: check if object exists */
		if (card == null) {
			card = new Card("description");
		}
		card.setDescription("Hello World");
		assertEquals("Hello World", card.getDescription());
		card.setDescription("");
		assertEquals("", card.getDescription());
	}

}