/**
 * 
 */
package de.htwg.monopoly.factory.impl;

import de.htwg.monopoly.util.PlayerIcon;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.Assert.assertTrue;

/**
 * @author Steffen
 *
 */
public class MonopolyFactoryTest {

	private MonopolyFactory testFactory;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testFactory = new MonopolyFactory();
	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.factory.impl.MonopolyFactory#createPlayfield(int)}
	 * .
	 */
	@Test
	public void testCreatePlayfield() {
		assertTrue((testFactory.createPlayfield(28)) instanceof de.htwg.monopoly.controller.impl.Playfield);

	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.factory.impl.MonopolyFactory#createPrisonQuestions()}
	 * .
	 */
	@Test
	public void testCreatePrisonQuestions() {
		assertTrue((testFactory.createPrisonQuestions()) instanceof de.htwg.monopoly.entities.impl.PrisonQuestion);
	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.factory.impl.MonopolyFactory#createDice()}.
	 */
	@Test
	public void testCreateDice() {
		assertTrue((testFactory.createDice()) instanceof de.htwg.monopoly.entities.impl.Dice);
	}

	/**
	 * Test method for
	 * {@link de.htwg.monopoly.factory.impl.MonopolyFactory#createPlayerController(java.util.Map)}
	 * .
	 */
	@Test
	public void testCreatePlayerController() {
		Map<String, PlayerIcon> players = new TreeMap<String, PlayerIcon>();
		players.put("Player 1", PlayerIcon.BITTEL);
		players.put("Player 2", PlayerIcon.BOGER);
		assertTrue((testFactory.createPlayerController(players)) instanceof de.htwg.monopoly.controller.impl.PlayerController);
	}

}
