package de.htwg.monopoly.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ChanceCardTest {

	ChanceCard card;

	@Before
	public void setUp() throws Exception {
		card = new ChanceCard("Gehe in das Gefängnis", "move");
	}
	

	@Test
	public void testCommunityCard() {
		ChanceCard card2 = new ChanceCard("Gehe in das Gefängnis", "move");
		assertEquals("Gehe in das Gefängnis",card2.getDescription());
	}

}
