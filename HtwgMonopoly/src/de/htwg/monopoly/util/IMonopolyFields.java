package de.htwg.monopoly.util;

import java.awt.Color;

public interface IMonopolyFields {

	/**
	 * l : los s : straße g : gemeinschaftsfeld z : zusatzsteuer b : Bahnhof e :
	 * ereignisfeld n : nur zu besuch im gefängnis p : gehe ins gefängnis f :
	 * frei parken. Reihenfolge stimmt mit original Monopoly überein. Werke sind
	 * durch ereignis/gemeinschaftskarten ersetzt.
	 * 
	 */
	char[] TYP = { 'l', 's', 'g', 's', 'z', 'b', 's', 'g', 's', 's', 'n', 's',
			'g', 's', 's', 'b', 's', 'e', 's', 's', 'f', 's', 'e', 's', 's',
			'b', 's', 's', 'g', 's', 'p', 's', 's', 'g', 's', 'b', 'e', 's',
			'z', 's' };
	/**
	 * Preise für die Straßen von dem original Monopoly Spiel und in richtiger
	 * Reihenfolge.
	 * 
	 * 
	 */
	Integer[] PRICE_FOR_STREET = { 0, 60, 0, 60, 100, 200, 100, 0, 100, 120, 0,
			140, 150, 140, 160, 200, 180, 0, 180, 200, 0, 220, 0, 220, 240,
			200, 260, 260, 150, 280, 0, 300, 300, 0, 320, 200, 0, 350, 100, 400 };

	/**
	 * Vorläufige Namen von Feldern. Insgesamt bis jetzt nur 22 Felder.
	 * 
	 * 
	 */
	String[] NAME = { "Bafög-Amt", "Stochastik", "Gemeinschaftsfeld", "SM2",
			"Einkommensteuer", "Digital-\ntechnik", "SysMo", "Ereignisfeld",
			"SysProg", "ProgTech", "Bsys Labor", "Mathe 1",
			"Gemeinschaftsfeld", "Prog-\nTech 2", "TI", "ALDA", "DB",
			"Ereignisfeld", "Mathe 2", "ReAr", "Frei parken", "SE" };
	/**
	 * Farben in richtiger Reihenfolge. Wenn keine Straße, dann ist Farbe null.
	 * 
	 * 
	 */
	Color[] COLOUR = { null, Color.magenta, null, Color.magenta, null, null,
			Color.cyan, null, Color.cyan, Color.cyan, null, Color.pink, null,
			Color.pink, Color.pink, null, Color.orange, null, Color.orange,
			Color.orange, null, Color.red, null, Color.red, Color.red, null,
			Color.yellow, Color.yellow, null, Color.yellow, null, Color.green,
			Color.green, null, Color.green, null, null, Color.blue, null,
			Color.blue };

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
	String[] TESTNAMES = { "Bafög-Amt", "Gemeinschaftsfeld"};
	Integer[] TESTRENT = { 0, 0};
	Integer[] TESTHOTEL = { 0, 0};
	Color[] TESTCOLOUR = { null, null};

	Integer[] POSITION = { 0, 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 15, 16, 18 };

}
