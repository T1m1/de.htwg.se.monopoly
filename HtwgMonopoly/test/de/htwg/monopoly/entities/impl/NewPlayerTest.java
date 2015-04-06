/**
 * 
 */
package de.htwg.monopoly.entities.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;

/**
 * @author Steffen
 *
 */
public class NewPlayerTest {

	private Player player;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		player = new Player("TestName", PlayerIcon.BITTEL);
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getName()}.
	 */
	@Test
	public void testGetName() {
		assertEquals("Name of Player is not the expected","TestName", player.getName());
	}


	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getBudget()}.
	 */
	@Test
	public void testGetBudget() {
		assertEquals("Money of player was not expected",IMonopolyUtil.INITIAL_MONEY, player.getBudget());
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#setBudget(int)}.
	 */
	@Test
	public void testSetBudget() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getPosition()}.
	 */
	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#setPosition(int)}.
	 */
	@Test
	public void testSetPosition() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getPrisonRound()}.
	 */
	@Test
	public void testGetPrisonRound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#incrementPrisonRound()}.
	 */
	@Test
	public void testIncrementPrisonRound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#setPrisonRound(int)}.
	 */
	@Test
	public void testSetPrisonRound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#isInPrison()}.
	 */
	@Test
	public void testIsInPrison() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#setInPrison(boolean)}.
	 */
	@Test
	public void testSetInPrison() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#toString()}.
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getOwnership()}.
	 */
	@Test
	public void testGetOwnership() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#addOwnership(de.htwg.monopoly.entities.IFieldObject)}.
	 */
	@Test
	public void testAddOwnership() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#decrementMoney(int)}.
	 */
	@Test
	public void testDecrementMoney() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#incrementPrisonFreeCard()}.
	 */
	@Test
	public void testIncrementPrisonFreeCard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#hasPrisonFreeCard()}.
	 */
	@Test
	public void testHasPrisonFreeCard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#usePrisonFreeCard()}.
	 */
	@Test
	public void testUsePrisonFreeCard() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getIcon()}.
	 */
	@Test
	public void testGetIcon() {
		fail("Not yet implemented");
	}

}
