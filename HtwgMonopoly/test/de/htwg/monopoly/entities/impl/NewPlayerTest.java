/**
 * 
 */
package de.htwg.monopoly.entities.impl;

import static org.junit.Assert.*;

import java.awt.Color;

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
		assertEquals("Name of Player is not the expected", "TestName",
				player.getName());
	}

	/**
	 * Test method for {@link de.htwg.monopoly.entities.impl.Player#getBudget()}
	 * .
	 */
	@Test
	public void testGetBudget() {
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
		assertTrue(player.getOwnership().size() == 1);
	}

}
