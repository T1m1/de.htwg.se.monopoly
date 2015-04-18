package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player1;
	private Player player2;

	@Before
	public void setUp() throws Exception {
		player1 =  new Player("TestName", PlayerIcon.BITTEL);
		player2 = new Player("TestName", PlayerIcon.BITTEL);
	}

	@Test
	public void testPlayerStringCharDoubleStringArrayInt() {
		Player player3 =  new Player("Kenny", PlayerIcon.BITTEL);
		assertEquals("Kenny", player3.getName());
		assertEquals(1500, player3.getBudget());

	}

	@Test
	public void testPlayerStringCharDouble() {
		Player player2 =  new Player("Manny", PlayerIcon.BITTEL);
		assertEquals("Manny", player2.getName());
		assertEquals(1500, player2.getBudget());
	}


	@Test
	public void testGetBudget() {
		player1.decrementMoney(1500);
		player1.incrementMoney(12345);
		assertEquals(12345, player1.getBudget());
	}

	@Test
	public void testGetPosition() {
		player1.setPosition(5);
		assertEquals(5, player1.getPosition());
	}


	@Test
	public void testIsInPrison() {
		player1.setInPrison(true);
		assertTrue(player1.isInPrison());
		assertEquals(0, player1.getPrisonRound());

		player1.incrementPrisonRound();
		assertTrue(player1.isInPrison());
		assertEquals(1, player1.getPrisonRound());

		player1.incrementPrisonRound();
		assertTrue(player1.isInPrison());
		assertEquals(2, player1.getPrisonRound());

		player1.incrementPrisonRound();
		assertTrue("player should be in prison", player1.isInPrison());
		assertEquals(3, player1.getPrisonRound());

		player1.incrementPrisonRound();
		assertFalse(player1.isInPrison());
		assertEquals(0, player1.getPrisonRound());
	}

	@Test(expected = AssertionError.class)
	public void testMoney() {
		player1.decrementMoney(2000);
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

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getBudget()}
	 * .
	 */
	@Test
	public void testGetBudget2() {
		assertEquals("Money of player was not expected",
				IMonopolyUtil.INITIAL_MONEY, player2.getBudget());
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getIcon()}.
	 */
	@Test
	public void testGetIcon() {
		assertEquals(PlayerIcon.BITTEL, player2.getIcon());
	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.entities.impl.Player#addOwnership(de.htwg.monopoly.entities.IFieldObject)}
	 * .
	 */
	@Test
	public void testgetOwnership() {
		assertTrue(player2.getOwnership().isEmpty());
		// add street to player
		Street testStreet = new Street("test", 50, Color.BLACK, 5, 10);
		player2.addOwnership(testStreet);

		assertTrue(player2.getOwnership().contains(testStreet));
		assertEquals(player2.getOwnership().size(), 1);
	}
	
	@Test
	public void buyStreet() {
		// set money to zero
		player1.decrementMoney(IMonopolyUtil.INITIAL_MONEY);
		Street testStreet = new Street("Test", 50, Color.BLACK, 20, 20);
		assertFalse(player1.buyStreet(testStreet));

		player1.incrementMoney(50);
		assertTrue(player1.buyStreet(testStreet));
		assertTrue(testStreet.isSold());
		assertEquals(0, player1.getBudget());
		assertTrue(player1.getOwnership().contains(testStreet));
	}

	@Test(expected = AssertionError.class)
	public void buyStreetAssert() {
		Street testStreet = new Street("Test", 50, Color.BLACK, 20, 20);
		testStreet.setSold(true);
		player1.buyStreet(testStreet);
	}

}
