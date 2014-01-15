package de.htwg.monopoly.util;

import java.awt.Color;

public interface IMonopolyFields {

	String GEMEINSCHAFTSFELD = "Gemeinschaftsfeld";

	/**
	 * l : los s : straße g : gemeinschaftsfeld z : zusatzsteuer e :
	 * ereignisfeld n : nur zu besuch im gefängnis p : gehe ins gefängnis f :
	 * frei parken.
	 * 
	 */
	char[] TYP = { 'l', 's', 'z', 's', 'g', 's', 's', 'n', 's', 'e', 's', 's',
			'z', 's', 'f', 's', 'g', 's', 's', 'e', 's', 'p', 's', 'g', 's',
			's', 'z', 's' };
	/**
	 * Preise für die Straßen
	 * 
	 * 
	 */
	Integer[] PRICE_FOR_STREET = { 0, 60, 0, 60, 100, 200, 100, 0, 100, 120, 0,
			140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240,
			200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 100, 400 };

	/**
	 * Vorläufige Namen von Feldern. Insgesamt bis jetzt 28 Felder.
	 * 
	 * 
	 */
	String[] NAME = { "Bafög-Amt", "Stochastik", "Zusatzsteuer", "SM2",
			GEMEINSCHAFTSFELD, "Digital-\ntechnik", "SysMo",
			"Bsys Labor, nur zu Besuch", "SysProg", "Ereignisfeld", "ProgTech",
			"Mathe 1", "Zusatzsteuer", "Prog-\nTech 2", "Frei parken", "TI",
			GEMEINSCHAFTSFELD, "ALDA", "DB", "Ereignisfeld", "Mathe 2",
			"Bsys Labor", "ReAr", GEMEINSCHAFTSFELD, "SE", "Konso",
			"Zusatzsteuer", "Sekretariat" };
	/**
	 * Farben in richtiger Reihenfolge. Wenn keine Straße, dann ist Farbe null.
	 * 
	 * 
	 */
	Color[] COLOUR = { null, Color.magenta, null, Color.magenta, null,
			Color.cyan, Color.cyan, null, Color.pink, null, Color.pink,
			Color.orange, null, Color.orange, null, Color.red, null, Color.red,
			Color.yellow, null, Color.yellow, null, Color.green, null, Color.green,
			Color.blue, null, Color.blue };

	/**
	 * Die Miete stimmt ebenfalls mit dem Original Spiel überein. Alle Felder
	 * die keine Straße sind werden mit 0 initialisiert.
	 * 
	 */
	Integer[] RENT = { 0, 2, 0, 4, 0, 6, 0, 6, 8, 0, 10, 0, 10, 12, 0, 14, 0,
			14, 16, 0, 18, 0, 18, 20, 0, 22, 22, 0, 24, 0, 26, 26, 0, 28, 0,
			35, 0, 50 };

	/**
	 * Stimmt nicht, bis jetzt sind es die gleichen Zahlen wie bei Miete
	 * 
	 * 
	 */
	Integer[] HOTEL = { 0, 2, 0, 4, 0, 6, 0, 6, 8, 0, 10, 0, 10, 12, 0, 14, 0,
			14, 16, 0, 18, 0, 18, 20, 0, 22, 22, 0, 24, 0, 26, 26, 0, 28, 0,
			35, 0, 50 };

	String[] NAMES = new String[IMonopolyUtil.FIELD_SIZE];
	Color[] COLOR = new Color[IMonopolyUtil.FIELD_SIZE];
	Integer[] RENT_FOR_STREET = new Integer[IMonopolyUtil.FIELD_SIZE];
	Integer[] PRICE_PER_HOTEL = new Integer[IMonopolyUtil.FIELD_SIZE];
	Integer[] PRICE_PER_HOUSE = new Integer[IMonopolyUtil.FIELD_SIZE];

	/** Test Cases **/
	/**
	 * Test 1: Contains only 2 Object: Go field and a Gemeinschaftsfeld.
	 * 
	 */
	String[] TESTNAMES = { "Bafög-Amt", GEMEINSCHAFTSFELD };
	Integer[] TESTRENT = { 0, 0 };
	Integer[] TESTHOTEL = { 0, 0 };
	Color[] TESTCOLOUR = { null, null };

	Integer[] POSITION = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
			15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27 };

}
