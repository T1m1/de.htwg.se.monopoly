package de.htwg.monopoly.entities;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.util.MonopolyUtil;

public class StreetTest {
	Street street1;

	@Before
	public void setUp() throws Exception {
		street1 = new Street();
	}

	@Test
	public void testStreet() {
		assertEquals("empty", street1.getName());
		assertEquals(MonopolyUtil.NULL, street1.getPriceForStreet());
		assertEquals(Color.black, street1.getColor());
		assertEquals(MonopolyUtil.NULL, street1.getPricePerHotel());
		assertEquals(MonopolyUtil.NULL, street1.getNumberOfHotels());
		assertEquals(MonopolyUtil.NULL, street1.getRent());
	}

	@Test
	public void testStreetConstructorAll() {
		Street street2 = new Street( "Bart", MonopolyUtil.TEST_PRICE_ONE, Color.red, MonopolyUtil.TEST_PRICE_TWO,
				MonopolyUtil.TEST_PRICE_THREE, MonopolyUtil.NULL);
		assertEquals("Bart", street2.getName());
		assertEquals(MonopolyUtil.TEST_PRICE_ONE, street2.getPriceForStreet());
		assertEquals(Color.red, street2.getColor());
		assertEquals(MonopolyUtil.TEST_PRICE_TWO, street2.getRent());
		assertEquals(MonopolyUtil.TEST_PRICE_THREE, street2.getPricePerHotel());
		assertEquals(MonopolyUtil.NULL, street2.getNumberOfHotels());
		
	}
	@Test
	public void testGetName() {
		assertEquals("empty", street1.getName());
		street1.setName("Rudolf");
		assertEquals("Rudolf", street1.getName());
	}

	@Test
	public void testGetPriceForStreet() {
		assertEquals(MonopolyUtil.NULL, street1.getPriceForStreet());
		street1.setPriceForStreet(MonopolyUtil.TEST_PRICE_ONE);
		assertEquals(MonopolyUtil.TEST_PRICE_ONE, street1.getPriceForStreet());
	}

	@Test
	public void testGetColor() {
		assertEquals(Color.black, street1.getColor());
		street1.setColor(Color.blue);
		assertEquals(Color.blue, street1.getColor());
	}

	@Test
	public void testGetRent() {
		assertEquals(MonopolyUtil.NULL, street1.getRent());
		street1.setRent(MonopolyUtil.TEST_PRICE_ONE);
		assertEquals(MonopolyUtil.TEST_PRICE_ONE, street1.getRent());
	}

	@Test
	public void testGetPricePerHotel() {
		assertEquals(MonopolyUtil.NULL, street1.getPricePerHotel());
		street1.setPricePerHotel(MonopolyUtil.TEST_PRICE_TWO);
		assertEquals(MonopolyUtil.TEST_PRICE_TWO, street1.getPricePerHotel());
	}

	@Test
	public void testGetNumberOfHotels() {
		assertEquals(MonopolyUtil.NULL, street1.getNumberOfHotels());
		street1.setNumberOfHotels(MonopolyUtil.TEST_PRICE_TWO);
		assertEquals(MonopolyUtil.TEST_PRICE_TWO, street1.getNumberOfHotels());
	}

}
