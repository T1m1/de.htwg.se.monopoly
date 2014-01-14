package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.ChanceCard;

public class ChanceCardTest {

	ChanceCard card;

	@Before
	public void setUp() throws Exception {
		card = new ChanceCard("Gehe in das Gefängnis", null,
				false);
	}

	@Test
	public void testCommunityCard() {
		ChanceCard card2 = new ChanceCard("Gehe in das Gefängnis",
				null, false);
		assertEquals("Gehe in das Gefängnis", card2.getDescription());
	}

}
