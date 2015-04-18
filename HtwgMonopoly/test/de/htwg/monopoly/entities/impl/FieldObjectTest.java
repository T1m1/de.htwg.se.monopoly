package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.FieldType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldObjectTest {

	FieldObject testObject;

	@Before
	public void setUp() throws Exception {
		testObject = new FieldObject("foo", FieldType.STREET, 100);
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
		assertEquals(FieldType.STREET, testObject.getType());
	}

}
