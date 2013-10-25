
package de.htwg.monopoly.entities;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.util.IMonopolyUtil;

public class PlayerTest {
	Player player1;

	@Before
	public void setUp() throws Exception {
		player1 = new Player("lala", 'U', 0);
	}

	@Test
	public void testPlayerStringCharDoubleStringArrayInt() {
		Player player3 = new Player("Kenny", 'K', IMonopolyUtil.TEST_PRICE_ONE);
		assertEquals("Kenny", player3.getName());
		assertEquals('K', player3.getFigure());
		assertEquals(IMonopolyUtil.TEST_PRICE_ONE, player3.getBudget());
	}

	@Test
	public void testPlayerStringCharDouble() {
		Player player2 = new Player("Manny", 'M', IMonopolyUtil.TEST_PRICE_TWO);
		assertEquals("Manny", player2.getName());
		assertEquals('M', player2.getFigure());
		assertEquals(IMonopolyUtil.TEST_PRICE_TWO, player2.getBudget());
	}

	@Test
	public void testGetName() {
		player1.setName("Udo");
		assertEquals("Udo", player1.getName());
	}

	@Test
	public void testGetFigure() {
		player1.setFigure('x');
		assertEquals('x', player1.getFigure());
	}

	@Test
	public void testGetBudget() {
		player1.setBudget(12345);
		/*
		 * ueberprueft ob die differenz 0 ist wird benutzt da asserEquals double
		 * depracated ist
		 */
		assertEquals(0, Double.compare(12345, player1.getBudget()));
	}

//	@Test
//	public void testGetOwnership() {
//		String[] steets = { "Haus", "Mensa" };
//		player1.setOwnership(steets);
//		assertEquals(steets[0], player1.getOwnership()[0]);
//		assertEquals(steets[1], player1.getOwnership()[1]);
//		assertNotSame(steets[1], player1.getOwnership()[0]);
//	}

//	@Test
//	public void testSetOwnership() {
//		String[] steets = { "Haus", "Mensa" };
//		player1.setOwnership(steets);
//		assertEquals(steets[0], player1.getOwnership()[0]);
//		assertEquals(steets[1], player1.getOwnership()[1]);
//		assertNotSame(steets[1], player1.getOwnership()[0]);
//	}

	@Test
	public void testGetPosition() {
		player1.setPosition(5);
		assertEquals(5, player1.getPosition());
	}

}
