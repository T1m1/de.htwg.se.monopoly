
package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player1;

	@Before
	public void setUp() throws Exception {
		player1 = new Player("lala", "U", 0);
		Player player2 = new Player();
		player2.decrementMoney(1);
	}

	@Test
	public void testPlayerStringCharDoubleStringArrayInt() {
		Player player3 = new Player("Kenny", "K", IMonopolyUtil.TEST_PRICE_ONE);
		assertEquals("Kenny", player3.getName());
		assertEquals("K", player3.getFigure());
		assertEquals(IMonopolyUtil.TEST_PRICE_ONE, player3.getBudget());
	
	}

	@Test
	public void testPlayerStringCharDouble() {
		Player player2 = new Player("Manny", "M", IMonopolyUtil.TEST_PRICE_TWO);
		assertEquals("Manny", player2.getName());
		assertEquals("M", player2.getFigure());
		assertEquals(IMonopolyUtil.TEST_PRICE_TWO, player2.getBudget());
	}

	@Test
	public void testGetFigure() {
		player1.setFigure("x");
		assertEquals("x", player1.getFigure());
	}

	@Test
	public void testGetBudget() {
		player1.incrementMoney(12345);
		/*
		 * ueberprueft ob die differenz 0 ist wird benutzt da asserEquals double
		 * depracated ist TODO: @Timi: What???
		 */
		assertEquals(0, Double.compare(12345, player1.getBudget()));
	}

	@Test
	public void testGetPosition() {
		player1.setPosition(5);
		assertEquals(5, player1.getPosition());
	}
	
	@Test
	public void testGetPrisonRound() {
		player1.setPrisonRound(1);
		assertEquals(1, player1.getPrisonRound());
	}
	
	@Test
	public void testIncrementPrisonRound() {
		player1.setPrisonRound(3);
		player1.incrementPrisonRound();
		assertEquals(0, player1.getPrisonRound());
		assertFalse(player1.isInPrison());
		player1.setPrisonRound(2);
		player1.incrementPrisonRound();
		assertTrue(player1.isInPrison());
		
		player1.setPrisonRound(0);
		assertFalse(player1.isInPrison());
		
	}
	
	@Test	
	public void testIsInPrison() {
		player1.setInPrison(true);
		assertTrue(player1.isInPrison());

        player1.incrementPrisonRound();
        assertTrue(player1.isInPrison());

        player1.incrementPrisonRound();
        assertTrue(player1.isInPrison());

        player1.incrementPrisonRound();
        assertTrue("player should be in prison",player1.isInPrison());

        player1.incrementPrisonRound();


        assertFalse(player1.isInPrison());
	}

	@Test(expected=AssertionError.class)
	public void testMoney() {
		player1.decrementMoney(2);
	}
	
	@Test
	public void testPrisonShit() {
		assertFalse(player1.hasPrisonFreeCard());
		player1.incrementPrisonFreeCard();
		assertTrue(player1.hasPrisonFreeCard());
		player1.usePrisonFreeCard();
		assertFalse(player1.hasPrisonFreeCard());
	}
	
	@Test(expected = AssertionError.class)
	public void testPrisonWithError() {
		player1.usePrisonFreeCard();
	}
	
}
