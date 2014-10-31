/**
 * 
 */
package de.htwg.monopoly.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author stgorenf
 *
 */
public class MonopolyUtilsTest {

	private int minPlayer;
	private int maxPlayer;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		minPlayer = IMonopolyUtil.MIN_NUMBER_OF_PLAYER;
		maxPlayer = IMonopolyUtil.MAX_NUMBER_OF_PLAYER;
	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.util.MonopolyUtils#verifyPlayerNumber(int)}.
	 */
	@Test
	public void testVerifyPlayerNumber() {
		assertTrue(MonopolyUtils.verifyPlayerNumber(minPlayer));
		
		assertTrue(MonopolyUtils.verifyPlayerNumber(minPlayer + 1));
		
		assertFalse(MonopolyUtils.verifyPlayerNumber(minPlayer - 1));

		assertTrue(MonopolyUtils.verifyPlayerNumber(maxPlayer));

		assertTrue(MonopolyUtils.verifyPlayerNumber(maxPlayer - 1));

		assertFalse(MonopolyUtils.verifyPlayerNumber(maxPlayer + 1));
	}

}