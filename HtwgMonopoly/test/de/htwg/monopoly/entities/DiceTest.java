package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiceTest {
	
	Dice dice;

	@Before
	public void setUp() throws Exception {
		dice = new Dice(); 
	}

	@Test
	public void testGetDice() {
		dice.setDice(1, 1);
		assertEquals(1, dice.getDice());
		dice.setDice(6, 6);
		assertEquals(6, dice.getDice());
	}

}
