package de.htwg.monopoly.util;

import java.awt.Color;

public interface IMonopolyFields {

	/**
	 * l : los s : straﬂe g : gemeinschaftsfeld z : zusatzsteuer b : Bahnhof e :
	 * ereignisfeld n : nur zu besuch im gef‰ngnis p : gehe ins gef‰ngnis f :
	 * frei parken
	 * 
	 */
	char[] typ = { 'l', 's', 'g', 'e', 'z', 'p', 'b', 'e', 's', 's', 'n', 's',
			'w', 's', 's', 'b', 's', 'g', 's', 's', 'f', 's', 'e', 's', 's',
			'b', 's', 's', 'w', 's', 'p', 's', 's', 'g', 's', 'b', 'e', 's',
			'z', 's' };
	//Anmerkung zu typ: die Reihenfolge stimmt nicht mehr. muss am schluss wieder in richtige reiheinfolge gebracht werden
	Integer[] prizeForStreet = { 0, 60, 0, 60, 100, 200, 100, 0, 100, 120, 0,
			140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240,
			200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 100, 400 };
	
	String[] names = new String[IMonopolyUtil.FIELD_SIZE];
	Color[] color = new Color[IMonopolyUtil.FIELD_SIZE];
	Integer[] rentForStreet = new Integer[IMonopolyUtil.FIELD_SIZE];
	Integer[] pricePerHotel = new Integer[IMonopolyUtil.FIELD_SIZE];
	Integer[] pricePerHouse = new Integer[IMonopolyUtil.FIELD_SIZE];
	
	/** Test Cases **/
	/**
	 * Test 1: Contains only 2 Object: Go field and a street.
	 * 
	 */
	String[] name = { "Go", "Firststreet" };
	Integer[] rent = { 0, 100 };
	Integer[] hotel = { 0, 100 };
	Color[] coulor = { null, Color.black };

	
}
