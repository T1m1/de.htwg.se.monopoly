package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.entities.impl.Dice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DiceTest {

	Dice dice;

	@Before
	public void setUp() throws Exception {
		dice = new Dice();
	}

	/**
	 * only needed if the getDice method is public and not private
	 */
    // @Test
    // public void testGetDice() {
    // dice.setDice(1, 1);
    // assertEquals(1, dice.setDice(1,1));
    // dice.setDice(6, 6);
    // assertEquals(6, dice.setDice(6,6));
    // }

	@Test
	public void testThrowDice() {
		for (int i = 0; i < 100; i++) {
			dice.throwDice();
			assertTrue(dice.getResultDice() <= 12);
			assertFalse(dice.getResultDice() > 12);
			assertTrue(dice.getResultDice() >= 1);
			assertFalse(dice.getResultDice() < 1);
			assertTrue(dice.getDice1() <= 6);
			assertFalse(dice.getDice1() > 6);
			assertFalse(dice.getDice2() > 6);
			assertTrue(dice.getDice2() <= 6);
			assertTrue(dice.getDice1() > 0);
			assertFalse(dice.getDice1() <= 0);
		}

	}
	
	@Test
	public void paschTest() {
		for (int i = 0; i < 100; i++) {
			dice.throwDice();
			if (dice.getDice1() == dice.getDice2()) {
				assertTrue(dice.isPasch());
			} else {
				assertFalse(dice.isPasch());
			}
		}
	}

}
