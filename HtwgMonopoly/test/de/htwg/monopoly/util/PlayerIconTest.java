package de.htwg.monopoly.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerIconTest {

	private PlayerIcon testEnum;

	@Before
	public void setUp() throws Exception {
		testEnum = PlayerIcon.BITTEL;
	}

	@Test
	public void testGetDescription() {
		assertEquals(testEnum.getDescription(), "Bittel");
	}
}
