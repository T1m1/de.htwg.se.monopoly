package de.htwg.monopoly.entities.impl;

import de.htwg.monopoly.util.FieldType;
import de.htwg.monopoly.util.IMonopolyUtil;
import de.htwg.monopoly.util.PlayerIcon;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StreetTest {
	private Street street1;

	@Before
	public void setUp() throws Exception {
		street1 = new Street();
	}

	@Test
	public void testStreet() {
		assertEquals("empty", street1.getName());
		assertEquals("empty", street1.toString());
		assertEquals(IMonopolyUtil.NULL, street1.getPriceForStreet());
		assertEquals(Color.black, street1.getColor());
		assertEquals(IMonopolyUtil.NULL, street1.getPricePerHotel());
		assertEquals(IMonopolyUtil.NULL, street1.getNumberOfHotels());
		assertEquals(IMonopolyUtil.NULL, street1.getRent());
	}

	@Test
	public void testStreetConstructorAll() {
		Street street2 = new Street( "Bart", IMonopolyUtil.TEST_PRICE_ONE, Color.red, IMonopolyUtil.TEST_PRICE_TWO,
				IMonopolyUtil.TEST_PRICE_THREE);
		assertEquals("Bart", street2.getName());
		assertEquals(IMonopolyUtil.TEST_PRICE_ONE, street2.getPriceForStreet());
		assertEquals(Color.red, street2.getColor());
		assertEquals(IMonopolyUtil.TEST_PRICE_TWO, street2.getRent());
		assertEquals(IMonopolyUtil.TEST_PRICE_THREE, street2.getPricePerHotel());
		assertEquals(IMonopolyUtil.NULL, street2.getNumberOfHotels());
		
	}
	@Test
	public void testGetName() {
		assertEquals("empty", street1.getName());
		street1.setName("Rudolf");
		assertEquals("Rudolf", street1.getName());
		street1.getPosition();
	}

	@Test
	public void testGetPriceForStreet() {
		assertEquals(IMonopolyUtil.NULL, street1.getPriceForStreet());
		street1.setPriceForStreet(IMonopolyUtil.TEST_PRICE_ONE);
		assertEquals(IMonopolyUtil.TEST_PRICE_ONE, street1.getPriceForStreet());
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.black, street1.getColor());
		street1.setColor(Color.blue);
		assertEquals(Color.blue, street1.getColor());
	}

	@Test
	public void testGetRent() {
		assertEquals(IMonopolyUtil.NULL, street1.getRent());
		street1.setRent(IMonopolyUtil.TEST_PRICE_ONE);
		assertEquals(IMonopolyUtil.TEST_PRICE_ONE, street1.getRent());
	}

	@Test
	public void testGetPricePerHotel() {
		assertEquals(IMonopolyUtil.NULL, street1.getPricePerHotel());
		street1.setPricePerHotel(IMonopolyUtil.TEST_PRICE_TWO);
		assertEquals(IMonopolyUtil.TEST_PRICE_TWO, street1.getPricePerHotel());
	}

	@Test
	public void testGetNumberOfHotels() {
		assertEquals(IMonopolyUtil.NULL, street1.getNumberOfHotels());
		street1.setNumberOfHotels(IMonopolyUtil.TEST_PRICE_TWO);
		assertEquals(IMonopolyUtil.TEST_PRICE_TWO, street1.getNumberOfHotels());
	}
	
	@Test
	public void testIsSold() {
		street1.setSold(true);
		assertTrue(street1.isSold());
		assertEquals(FieldType.STREET, street1.getType());
	}
	
	@Test
	public void testStreetOwner() {
		Player newOwner =  new Player("TestName", PlayerIcon.BITTEL);
		street1.setOwner(newOwner);
		assertEquals("TestName", street1.getOwner().getName());
	}

}
