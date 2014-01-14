package de.htwg.monopoly.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.entities.impl.FieldObject;

public class FieldObjectTest {
	
	FieldObject testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new FieldObject("foo", 'x', 100);
	}
	
	@Test
	public void testToString() {
		assertEquals("foo", testObject.toString());
	}

	@Test
	public void testGetPriceToPay() {
		assertEquals(100, testObject.getPriceToPay());
	}

	@Test
	public void testGetType() {
		assertEquals('x', testObject.getType());;
	}

}
