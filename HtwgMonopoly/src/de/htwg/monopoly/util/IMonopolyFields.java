package de.htwg.monopoly.util;

import java.awt.Color;
import de.htwg.monopoly.util.FieldType;

public interface IMonopolyFields {

	String GEMEINSCHAFTSFELD = "Gemeinschaftsfeld";

	/**
	 * l : los s : straße g : gemeinschaftsfeld z : zusatzsteuer e :
	 * ereignisfeld n : nur zu besuch im gefängnis p : gehe ins gefängnis f :
	 * frei parken.
	 * 
	 */
	FieldType[] TYP = { FieldType.GO, FieldType.STREET, FieldType.TAX,
			FieldType.STREET, FieldType.COMMUNITY_STACK, FieldType.STREET,
			FieldType.STREET, FieldType.PRISON_VISIT_ONLY, FieldType.STREET,
			FieldType.CHANCE_STACK, FieldType.STREET, FieldType.STREET,
			FieldType.TAX, FieldType.STREET, FieldType.FREE_PARKING,
			FieldType.STREET, FieldType.COMMUNITY_STACK, FieldType.STREET,
			FieldType.STREET, FieldType.CHANCE_STACK, FieldType.STREET,
			FieldType.GO_TO_PRISON, FieldType.STREET, FieldType.COMMUNITY_STACK,
			FieldType.STREET, FieldType.STREET, FieldType.TAX, FieldType.STREET };
	/**
	 * Preise für die Straßen
	 * 
	 * 
	 */
	Integer[] PRICE_FOR_STREET = { 0, 100, 0, 110, 0, 150, 160, 0, 200, 0, 220,
			320, 0, 350, 0, 450, 0, 480, 580, 0, 600, 0, 700, 0, 750, 900, 0,
			1000 };

	/**
	 * Miete für die einzelnen "Straßen"
	 * 
	 */
	Integer[] RENT = { 0, 10, 0, 11, 0, 15, 16, 0, 20, 0, 22, 32, 0, 35, 0, 45,
			0, 48, 58, 0, 60, 0, 70, 0, 75, 90, 0, 100 };

	/**
	 * Vorläufige Namen von Feldern. Insgesamt bis jetzt 28 Felder.
	 * 
	 * 
	 */
	String[] NAME = { "Bafög-Amt", "Stochastik", "Zusatzsteuer", "SM2",
			GEMEINSCHAFTSFELD, "Konso", "SysMo", "Bsys Labor, nur zu Besuch",
			"Digital-\ntechnik", "Ereignisfeld", "ProgTech", "Mathe 1",
			"Zusatzsteuer", "TI", "Frei parken", "Sekretariat",
			GEMEINSCHAFTSFELD, "SysProg", "Mathe 2", "Ereignisfeld",
			"Prog-\nTech  2", "Gehe in das Bsys Labor", "ReAr",
			GEMEINSCHAFTSFELD, "ALDA", "DB", "Zusatzsteuer", "SE" };
	/**
	 * Farben in richtiger Reihenfolge. Wenn keine Straße, dann ist Farbe null.
	 * 
	 * 
	 */
	Color[] COLOUR = { null, Color.magenta, null, Color.magenta, null,
			Color.cyan, Color.cyan, null, Color.pink, null, Color.pink,
			Color.orange, null, Color.orange, null, Color.red, null, Color.red,
			Color.yellow, null, Color.yellow, null, Color.green, null,
			Color.green, Color.blue, null, Color.blue };

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
