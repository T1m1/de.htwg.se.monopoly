package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.CommunityCard;

public class CommunityCardTest {

	CommunityCard card;

	@Before
	public void setUp() throws Exception {
		card = new CommunityCard("Gehe in das Gefängnis", "move", null,
				false);
	}

	@Test
	public void testCommunityCard() {
		CommunityCard card2 = new CommunityCard("Gehe in das Gefängnis",
				"move", "prison", false);
		assertEquals("Gehe in das Gefängnis", card2.getDescription());
	}

}
