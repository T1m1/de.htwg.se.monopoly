package de.htwg.monopoly.entities.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommunityCardTest {

	@Before
	public void setUp() throws Exception {
		new CommunityCard("Gehe in das Gef&auml;ngnis", null, false);
	}

	@Test
	public void testCommunityCard() {
		CommunityCard card2 = new CommunityCard("Gehe in das Gef&auml;ngnis",
				"prison", false);
		assertEquals("Gehe in das Gef&auml;ngnis", card2.getDescription());
	}

}
