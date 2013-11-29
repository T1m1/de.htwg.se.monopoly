package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiceTest {

	Dice dice;

	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * only needed if the getDice method is public and not private
	 */
//	@Test
//	public void testGetDice() {
//		dice.setDice(1, 1);
//		assertEquals(1, dice.setDice(1,1));
//		dice.setDice(6, 6);
//		assertEquals(6, dice.setDice(6,6));
//	}
	
	@Test
	public void testThrowDice() {
		Dice.throwDice();
		assertTrue(Dice.dice1<=6);
		assertFalse(Dice.dice1>6);
		
	}

}
