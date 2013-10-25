package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CommunityCardTest {

	CommunityCard card;
	
	
	@Before
	public void setUp() throws Exception {
		card = new CommunityCard("description");
	}


	@Test
	public void testGetDescription() {
		card.setDescription("foobar");
		assertEquals("foobar", card.getDescription());
		card.setDescription(" ");
		assertEquals(" ", card.getDescription());
	}


}
