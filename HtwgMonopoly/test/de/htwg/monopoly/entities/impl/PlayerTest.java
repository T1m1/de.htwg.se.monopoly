package de.htwg.monopoly.entities.impl;

import java.awt.Color;

import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
	private Player player1;
	private Player player;

	@Before
	public void setUp() throws Exception {
		player1 =  new Player("TestName", PlayerIcon.BITTEL);
		Player player2 =  new Player("TestName", PlayerIcon.BITTEL);
		player2.decrementMoney(1);

		player = new Player("TestName", PlayerIcon.BITTEL);
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
		assertTrue("player should be in prison", player1.isInPrison());

		player1.incrementPrisonRound();

		assertFalse(player1.isInPrison());
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
				IMonopolyUtil.INITIAL_MONEY, player.getBudget());
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getIcon()}.
	 */
	@Test
	public void testGetIcon() {
		assertEquals(PlayerIcon.BITTEL, player.getIcon());
	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.entities.impl.Player#addOwnership(de.htwg.monopoly.entities.IFieldObject)}
	 * .
	 */
	@Test
	public void testgetOwnership() {
		assertTrue(player.getOwnership().isEmpty());
		// add street to player
		Street testStreet = new Street("test", 50, Color.BLACK, 5, 10);
		player.addOwnership(testStreet);

		assertTrue(player.getOwnership().contains(testStreet));
		assertEquals(player.getOwnership().size(), 1);
	}

}
