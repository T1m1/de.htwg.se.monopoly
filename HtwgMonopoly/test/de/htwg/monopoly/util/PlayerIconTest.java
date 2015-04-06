package de.htwg.monopoly.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
